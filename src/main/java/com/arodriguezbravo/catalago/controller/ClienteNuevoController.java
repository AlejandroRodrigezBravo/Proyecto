package com.arodriguezbravo.catalago.controller;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.arodriguezbravo.catalago.enums.RolNombre;
import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.arodriguezbravo.catalago.model.entity.Rol;
import com.arodriguezbravo.catalago.service.IClienteService;
import com.arodriguezbravo.catalago.service.IFacturaService;
import com.arodriguezbravo.catalago.service.IRolService;
/**
 * Endpoint de cliente web
 * 
 * @author bravo
 * @version 05/05/2022
 */

@Controller
@RequestMapping("/usuario")
public class ClienteNuevoController {

	private final Logger logger = LoggerFactory.getLogger(ClienteNuevoController.class);

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IFacturaService facturaService;

	

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IRolService rolService;

	@GetMapping("/registro")
	public String registro() {
		return "/usuario/registro";
	}

	@GetMapping(value = "/index")
	public String index() {
		return "usuario/index";
	}

	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}

	@PostMapping("/registrar")
	public String save(Cliente cliente) {
		cliente.setNombre(cliente.getNombre());
		cliente.setApellido(cliente.getApellido());
		/*
		 * cliente.setDireccion(cliente.getDireccion());
		 * cliente.setTelefono(cliente.getTelefono());
		 */
		cliente.setEmail(cliente.getEmail());
		cliente.setCreateAt(new Date());
		cliente.setNombreUsuario(cliente.getNombreUsuario());
		cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
		Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
		Set<Rol> roles = new HashSet<>();
		roles.add(rolUser);
		cliente.setRoles(roles);
		clienteService.save(cliente);
		return "redirect:/";
	}

	@PostMapping("/signin")
	public String acceder(Cliente cliente, HttpSession session) {
		logger.info("Accesos : {}", cliente);

		Optional<Cliente> user = clienteService.getByNombreUsuario(cliente.getNombreUsuario());
		logger.info("Usuario de db: {}", user.get());

		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());

			if (hasRole("ROLE_ADMIN")) {
				return "redirect:/admin";
			} else {
				return "redirect:/";
			}
		} else {
			logger.info("Usuario no existe");
		}

		return "redirect:/";
	}

	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		Cliente usuario = clienteService.findOne((long) Integer.parseInt(session.getAttribute("idusuario").toString()))
				.get();
		List<Factura> facturas = facturaService.findByCliente(usuario);
		logger.info("factura {}", facturas);

		model.addAttribute("facturas", facturas);

		return "usuario/compras";
	}

	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Long id, HttpSession session, Model model) {
		logger.info("Id de la orden: {}", id);
		Factura detalle = facturaService.findOne(id).get();

		model.addAttribute("detalles", detalle.getDetalles());

		// session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/detallecompra";
	}

	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}

	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority(role));

	}

	
}
