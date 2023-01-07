package com.arodriguezbravo.catalago.service;

import java.util.Optional;

import com.arodriguezbravo.catalago.enums.RolNombre;
import com.arodriguezbravo.catalago.model.entity.Rol;

/**
 * Funcionalidad de rol de cliente
 * 
 * @author bravo
 * @version 01/05/2022
 */
public interface IRolService {

	/**
	 * Guardar por rol.
	 * 
	 * @param rol a guardar.
	 */
	Rol save(Rol rol);

	/**
	 * Busca por rol de cliente
	 * 
	 * @param rolNombre a busacar
	 * @return devuelve rol, null en caso contrario
	 */
	Optional<Rol> getByRolNombre(RolNombre rolNombre);

	/**
	 * Comprueba que el nombre del rol exista o este bien escrito
	 * 
	 * @param rolNombre a comprobar
	 * @return devuelve el rol, null en caso contrario
	 */
	boolean existsByRolNombre(RolNombre rolNombre);
}
