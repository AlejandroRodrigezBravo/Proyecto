
package com.arodriguezbravo.catalago.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.arodriguezbravo.catalago.model.entity.ItemFactura;
import com.arodriguezbravo.catalago.model.entity.Producto;
import com.arodriguezbravo.catalago.service.IClienteService;
import com.arodriguezbravo.catalago.service.IFacturaItemsService;
import com.arodriguezbravo.catalago.service.IFacturaService;
import com.arodriguezbravo.catalago.service.IProductoService;
import com.lowagie.text.DocumentException;

/**
 * Endpoint de carro de compras
 * @author bravo
 * @version 08/05/2022
 */
@Controller
@RequestMapping("/")
public class CarritoController {

	private final Logger log = LoggerFactory.getLogger(CarritoController.class);

	
	
	@Autowired
	private IProductoService productoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IFacturaService facturaService;

	@Autowired
	private IFacturaItemsService itemsFacturaService;

	// para los items de la factura
	List<ItemFactura> itemsLista = new ArrayList<>();

	// Datos de la factura
	Factura factura = new Factura();

	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
		
		model.addAttribute("productos", productoService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/index";
	}
	

	@GetMapping("userproducto/{id}")
	public String productoHome(@PathVariable Long id, Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = Optional.of(productoService.findOne(id));
		producto = productoOptional.get();

		model.addAttribute("producto", producto);

		return "usuario/userproducto";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Long id, @RequestParam Integer cantidad, Model model) {
		ItemFactura items = new ItemFactura();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		items.setCantidad(cantidad);
		items.setPrecio(producto.getPrecio());
		items.setNombre(producto.getNombre());
		items.setTotal(producto.getPrecio() * cantidad);
		items.setProducto(producto);

		// validar que le producto no se añada 2 veces
		Long idProducto = producto.getId();
		boolean ingresado = itemsLista.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

		if (!ingresado) {
			itemsLista.add(items);
		}

		sumaTotal = itemsLista.stream().mapToDouble(dt -> dt.getTotal()).sum();

		factura.setTotal(sumaTotal);
		model.addAttribute("cart", itemsLista);
		model.addAttribute("facturas", factura);

		return "usuario/carrito";
	}

	// quitar un producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Long id, Model model) {

		// lista nueva de prodcutos
		List<ItemFactura> itemsNueva = new ArrayList<>();

		for (ItemFactura detalle : itemsLista) {
			if (detalle.getProducto().getId() != id) {
				itemsNueva.add(detalle);
			}
		}

		// poner la nueva lista con los productos restantes
		itemsLista = itemsNueva;

		double sumaTotal = 0;
		sumaTotal = itemsLista.stream().mapToDouble(ItemFactura::getTotal).sum();

		factura.setTotal(sumaTotal);
		model.addAttribute("cart", itemsLista);
		model.addAttribute("facturas", factura);

		return "usuario/carrito";
	}

	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {

		model.addAttribute("cart", itemsLista);
		model.addAttribute("facturas", factura);

		// sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/carrito";
	}

	@GetMapping("/order")
	public String order(Model model, HttpSession session) {

		Cliente cliente = clienteService.findOne((long) Integer.parseInt(session.getAttribute("idusuario").toString())).get();

		model.addAttribute("cart", itemsLista);
		model.addAttribute("facturas", factura);
		model.addAttribute("cliente", cliente);

		return "usuario/itemsfactura";
	}

	// guardar la orden
		@GetMapping("/saveOrder")
		public String saveOrder(HttpServletResponse response, HttpSession session) throws MessagingException, DocumentException, IOException {
			Date fechaCreacion = new Date();
			factura.setFechaCreacion(fechaCreacion);
			factura.setNumero(facturaService.generarNumero());

			// usuario
			Cliente usuario = clienteService.findOne((long) Integer.parseInt(session.getAttribute("idusuario").toString())).get();

			factura.setCliente(usuario);
			facturaService.save(factura);

			// guardar detalles
			for (ItemFactura dt : itemsLista) {
				dt.setFactura(factura);
				itemsFacturaService.save(dt);
			}

			/// limpiar lista y orden
			factura = new Factura();
			itemsLista.clear();
			
			response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "inline; filename=factura.pdf");
			
			facturaService.enviarFacturaPorCorreo(response,usuario.getEmail());
			
			return "redirect:/";
		}
	


}
