package com.arodriguezbravo.catalago.security;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.service.IClienteService;

/**
 * Implementación de acceso cliente a web
 * @author bravo
 * @version 06/05/2022
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IClienteService clienteSVC;
	
	@Autowired
	HttpSession session;
	
	private Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Este es el username");
		Optional<Cliente> optionalUser=clienteSVC.getByNombreUsuario(username);
		if (optionalUser.isPresent()) {
			log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
			session.setAttribute("idusuario", optionalUser.get().getId());
			Cliente usuario= optionalUser.get();
			 return ClientePrincipal.build(usuario);
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");			
		}
	}
	
}
