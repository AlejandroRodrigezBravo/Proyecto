package com.arodriguezbravo.catalago.model.repository;


import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.arodriguezbravo.catalago.model.entity.Cliente;

/**
 * Dao de cliente
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Repository
public interface IClienteDAO extends PagingAndSortingRepository<Cliente, Long> {

	 
	/**
	 * Busca por nombre de cliente
	 * 
	 * @param name nombre a buscar
	 * @return devuelve el nombre de cliente, en otro caso null
	 */
	 Cliente findByNombre(String name);


	/**
	 * Buscar por username para login
	 * 
	 * @param nombreUsuario nombre para el login
	 * @return devuelve el usuario o null en caso contrario
	 */
	 Optional<Cliente> findByNombreUsuario(String nombreUsuario);

	/**
	 * Comprobar si el nombre de usuario existe o esta bien escrito
	 * 
	 * @param nombreUsuario nombre a comprobar
	 * @return devuelve true si es verdadero, false en caso contrario
	 */
	 boolean existsByNombreUsuario(String nombreUsuario);
}
