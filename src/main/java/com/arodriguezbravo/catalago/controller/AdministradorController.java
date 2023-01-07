package com.arodriguezbravo.catalago.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.arodriguezbravo.catalago.model.entity.Producto;
import com.arodriguezbravo.catalago.service.IClienteService;
import com.arodriguezbravo.catalago.service.IFacturaService;
import com.arodriguezbravo.catalago.service.IProductoService;
import com.arodriguezbravo.catalago.service.UploadFileServiceImpl;

/**
 * Endpoint de administrador
 * @author bravo
 * @version 01/04/2022 1.0.0
 */
@Controller
@RequestMapping("/admin")
public class AdministradorController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IFacturaService facturasService;

	@Autowired
	private UploadFileServiceImpl uploadFileService;
	// private Logger logg= LoggerFactory.getLogger(AdministradorController.class);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("")
	public String home(Model model) {

		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);

		return "admin/clientes";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/cliente")
	public String usuarios(Model model) {
		model.addAttribute("cliente", clienteService.findAll());
		return "admin/clientes";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/factura")
	public String ordenes(Model model) {
		model.addAttribute("factura", facturasService.findAll());
		return "admin/factura";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Cliente cliente = new Cliente();
		Optional<Cliente> optionalCliente = clienteService.findOne(id);
		cliente = optionalCliente.get();

		model.addAttribute("cliente", cliente);

		return "admin/editar";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/actualizar")
	public String update(Cliente cliente, @RequestParam("file") MultipartFile foto) throws IOException {
		Cliente c = new Cliente();
		c = clienteService.findOne(cliente.getId()).get();

		if (foto.isEmpty()) { // editamos el producto pero no cambiamos la imagem

			cliente.setFoto(c.getFoto());
		} else {// cuando se edita tbn la imagen
				// eliminar cuando no sea la imagen por defecto
			
			String nombreImagen = uploadFileService.saveImage(foto);
			cliente.setFoto(nombreImagen);
		}
		clienteService.update(cliente);
		return "redirect:/admin";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Long id) {
		Factura detalle = facturasService.findOne(id).get();

		model.addAttribute("detalles", detalle.getDetalles());

		return "admin/items";
	}

}
