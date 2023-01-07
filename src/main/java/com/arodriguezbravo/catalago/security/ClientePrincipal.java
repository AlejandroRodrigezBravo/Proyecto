package com.arodriguezbravo.catalago.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arodriguezbravo.catalago.model.entity.Cliente;

/**
 * Implementación de acceso cliente personalizado a web
 * @author bravo
 * @version 06/05/2022
 */

public class ClientePrincipal implements UserDetails {

	
	private Long id;
    private String nombreUsuario;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
	

	public ClientePrincipal(Long id, String nombreUsuario, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static ClientePrincipal build(Cliente cliente) {
		List<GrantedAuthority> authorities =
                cliente.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new ClientePrincipal(cliente.getId(), cliente.getNombreUsuario(), cliente.getPassword(), authorities);
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
