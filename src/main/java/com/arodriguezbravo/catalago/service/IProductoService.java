package com.arodriguezbravo.catalago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arodriguezbravo.catalago.model.entity.Producto;

/**
 * funcionalidad de producto
 * @author bravo
 *@version 01/05/2022 1.0.0
 */
public interface IProductoService {

	/**
	 * Busca lista de producto
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	List<Producto> findAll();

	/**
	 * Busca lista de producto con paginación
	 * @param pageable a listar con paginacion
	 * @return devuel la lista paginada, null en caso contrario
	 */
	Page<Producto> findAll(Pageable pageable);

	/**
	 * Guardad un producto
	 * @param producto a guardar
	 * @return inserta el producto, null en caso contrario
	 */
	Producto save(Producto producto);

	/**
	 * Acturaliza un producto
	 * @param produto a actualizar
	 */
	void update(Producto produto);

	/**
	 * Busca por id de pronducto
	 * @param id de producto
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	Optional<Producto> get(Long id);

	/**
	 * Busca por id de prodcuto, sin optional
	 * @param id ade producto
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	Producto findOne(Long id);

	/**
	 * Busca por nombre de producto, ignora mayuscula
	 * @param term a buscar
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	List<Producto> findByNombreLikeIgnoreCase(String term);

	/**
	 * Elimina por id
	 * @param id a eliminar
	 */
	void delete(Long id);

	/**
	 * Comprueba que exista el id
	 * @param id a comprobar
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	boolean existsById(Long id);

	/**
	 * Comprueba si el nombre existe
	 * @param nombre a comprobar
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	boolean existsByNombre(String nombre);
	
	/**
	 * Busca nombre comcreto de productos
	 * @param nombre a buscar
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	Producto findByNombre(String nombre);
}
