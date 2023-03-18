package com.arodriguezbravo.catalago.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arodriguezbravo.catalago.service.IClienteService;
import com.arodriguezbravo.catalago.service.IRolService;

/**
 * implementación para registrar cliente Admin
 * 
 * @author bravo
 * @version 04/05/2022
 */
@Service
public class CreateAdmin implements CommandLineRunner {

	@Autowired
	IClienteService usuarioService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IRolService rolService;

	@Override
	public void run(String... args) throws Exception {

		
		
		/*
		  Cliente usuario = new Cliente(); String passwordEncoded =
		  passwordEncoder.encode("admin"); usuario.setNombreUsuario("admin");
		  usuario.setPassword(passwordEncoded); usuario.setNombre("Alejandro");
		  usuario.setApellido("Rodríguez Bravo");
		  usuario.setDireccion("Calle Concepción Nª 107");
		  usuario.setTelefono("686177070"); usuario.setEmail("damAdmin@hotmail.com");
		  usuario.setCreateAt(new Date()); Rol rolAdmin =
		  rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get(); Set<Rol> roles = new
		  HashSet<>(); roles.add(rolAdmin); usuario.setRoles(roles);
		  usuarioService.save(usuario);
		  
		  
		  Cliente usuario2 = new Cliente(); String passwordEncoded1 =
		  passwordEncoder.encode("user"); usuario2.setNombreUsuario("Dam");
		  usuario2.setPassword(passwordEncoded1); usuario2.setNombre("Test");
		  usuario2.setApellido("Rodríguez JUnit");
		  usuario2.setDireccion("Calle Larios"); usuario2.setTelefono("686177072");
		  usuario2.setEmail("pruebaAlejandro@hotmail.com"); usuario2.setCreateAt(new
		  Date()); Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
		  Set<Rol> rolesU = new HashSet<>(); rolesU.add(rolUser);
		  usuario2.setRoles(rolesU); usuarioService.save(usuario2);*/
		 
		 

	}
}
