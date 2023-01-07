package com.arodriguezbravo.catalago.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arodriguezbravo.catalago.enums.RolNombre;
import com.arodriguezbravo.catalago.model.entity.Rol;

/**
 * Dao de rol usuario
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Repository
public interface RolDAO extends JpaRepository<Rol, Long> {

	/**
	 * Busca por nombre de rol
	 * 
	 * @param rolNombre a buscar entre los roles
	 * @return devuelve el rol, o null en caso contrario
	 */
	public Optional<Rol> findByRolNombre(RolNombre rolNombre);

	/**
	 * Comprueba que exista el rol, o este bien escrito
	 * 
	 * @param rolNombre para comprobar
	 * @return devuelve true si es verdadero, false en caso contrario
	 */
	public boolean existsByRolNombre(RolNombre rolNombre);

}
