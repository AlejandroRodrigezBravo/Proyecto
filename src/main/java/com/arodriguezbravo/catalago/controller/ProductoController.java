package com.arodriguezbravo.catalago.controller;

import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Producto;
import com.arodriguezbravo.catalago.service.IClienteService;
import com.arodriguezbravo.catalago.service.IProductoService;
import com.arodriguezbravo.catalago.service.UploadFileServiceImpl;

/**
 * Endpoint de producto
 * @author bravo
 * @version 05/05/2022
 */
@Controller
@RequestMapping("/producto")
public class ProductoController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IProductoService productoService;

	@Autowired
	private UploadFileServiceImpl uploadFileService;
	 

	@Autowired
	private IClienteService clienteService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "producto/catalogo";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/nuevo")
	public String create() {
		return "producto/nuevo";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/guardar")
	public String save(Producto producto, @RequestParam("file") MultipartFile foto, HttpSession session)
			throws IOException {

		Cliente u = clienteService.findOne((long) Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		producto.setCliente(u);

		// imagen
		if (producto.getId() == null) { // cuando se crea un producto
			String nombreImagen = uploadFileService.saveImage(foto);
			producto.setFoto(nombreImagen);
		} else {

		}
		productoService.save(producto);
		return "redirect:/producto";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Producto producto= new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		producto = optionalProducto.get();
		
		model.addAttribute("producto", producto);

		return "producto/editar";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/actualizar")
	public String update(Producto producto, @RequestParam("file") MultipartFile foto) throws IOException {
		Producto p = new Producto();
		p = productoService.get(producto.getId()).get();

		if (foto.isEmpty()) { // editamos el producto pero no cambiamos la imagem

			producto.setFoto(p.getFoto());
		} else {// cuando se edita tbn la imagen
				// eliminar cuando no sea la imagen por defecto
			if (!p.getFoto().equals("default.jpg")) {
				uploadFileService.deleteImage(p.getFoto());
			}
			String nombreImagen =  uploadFileService.saveImage(foto);
			producto.setFoto(nombreImagen);
		}
		producto.setCliente(p.getCliente());
		productoService.update(producto);
		return "redirect:/producto";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable Long id) {

		Producto p = new Producto();
		p = productoService.get(id).get();

		// eliminar cuando no sea la imagen por defecto
		if (!p.getFoto().equals("default.jpg")) {
			uploadFileService.deleteImage(p.getFoto());
		}

		productoService.delete(id);
		return "redirect:/producto";
	}

	
}
