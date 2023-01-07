package com.arodriguezbravo.catalago.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.arodriguezbravo.catalago.model.entity.Producto;

/**
 * Funcionalidad de cliente
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
public interface IClienteService {

	/**
	 *  busca lista de cliente
	 * @return devuelve la lista de cliente, null en caso contrario
	 */
	public List<Cliente> findAll();
	
	/**
	 * Paginación de lista de cliente
	 * @param pageable lista a paginar
	 * @return devuelve los elemenstos paginado, null en caso contrario
	 */
	public Page<Cliente> findAll(Pageable pageable);

	/**
	 * Gurardar un cliente
	 * @param cliente a guardar
	 * @return inserta cliente, null en caso contrario
	 */
	public Cliente save(Cliente cliente);
	
	/**
	 * Actualiza un clinte
	 * @param cliente a actualizar
	 */
	public void update(Cliente cliente);
	
	/**
	 * Busca por id de cliente
	 * @param id a buscar
	 * @return devuelve elemento encontrado, null en caso contrarios
	 */
	Optional<Cliente> findOne(Long id);
	
	/**
	 * Elimina un clinete por id.
	 * @param id a eliminar
	 */
	public void delete(Long id);
	
	/**
	 * Busca por nombre de producto
	 * @param term a buscar
	 * @return devuelve elemto si lo encuentra, null en caso contrario
	 */
	public List<Producto> findByNombre(String term);
	
	/**
	 * Guardar factura de cliente
	 * @param factura a guardar
	 */
	public void saveFactura(Factura factura);
	
	/**
	 * Busca por id de cproducto
	 * @param id a buscar
	 * @return devuleve elemento encontrado, null en caso contrario
	 */
	public Producto findProductoById(Long id);
	
	/**
	 * Busca por id de factura 
	 * @param id a buscar
	 * @return devuleve elemento encontrado, null en caso contrario
	 */
	public Factura findFacturaById(Long id);
	
	/**
	 * Elimina factura por id
	 * @param id a eliminar
	 */
	public void deleteFactura(Long id);
	
	
	
	/**
	 * Buscar por nombre de usuario para login
	 * 
	 * @param nombreUsuario a buscar
	 * @return devuelve el nombre de usuario, en caso contrario null
	 */
	public Optional<Cliente> getByNombreUsuario(String nombreUsuario);

	/**
	 * Comprueba que exista el nombre de usuario
	 * 
	 * @param nombreUsuario a comprobar
	 * @return devuelve true si existe, false en caso contrario
	 */
	public boolean existsByNombreusuario(String nombreUsuario);

	/**
	 * Comprueba que exista el id de usuario
	 * @param id a comprobar
	 * @return devuelve true si existe, false en caso contrario
	 */
	 public boolean existsById(Long id);

}
