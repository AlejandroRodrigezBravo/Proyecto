package com.arodriguezbravo.catalago.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.arodriguezbravo.catalago.enums.RolNombre;
import com.arodriguezbravo.catalago.model.entity.Rol;
import com.arodriguezbravo.catalago.service.IRolService;

/**
 * Implementación par registrar rol de usuario
 * 
 * @author bravo
 * @version 04/05/2022
 */

@Service
public class CreateRoles implements CommandLineRunner {

	@Autowired
	IRolService rolService;

	@Override
	public void run(String... args) throws Exception {

		
		  Rol rolAdmin = new Rol(1L,RolNombre.ROLE_ADMIN); 
		  Rol rolUser = new Rol(2L,RolNombre.ROLE_USER); 
		  rolService.save(rolAdmin);
		  rolService.save(rolUser);
		 

	}
}
