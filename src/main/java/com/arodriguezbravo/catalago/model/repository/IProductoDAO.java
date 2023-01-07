package com.arodriguezbravo.catalago.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.arodriguezbravo.catalago.model.entity.Producto;
/**
 * Dao de Producto
 * @author bravo
 * @version 01/05/2022 1.0.0
 */


@Repository
public interface IProductoDAO extends PagingAndSortingRepository<Producto, Long>{

	
	/**
	 * Busca por nombre de producto, ignora si es mayuscula
	 * @param term datos a buscar
	 * @return devuleve nombre producto, null en caso contrario
	 */
	List<Producto> findByNombreLikeIgnoreCase(String term);
	
	/**
	 * Comprueba si el nombre de producto existe
	 * @param nombre a comprobar
	 * @return devuleve verdadero si existe, false en caso contrario.
	 */
    boolean existsByNombre(String nombre);
    
    /**
     * Buscar por nombre de producto
     * @param nombre a buscar
     * @return devuelve elemento encontrado, null en caso contrario
     */
    Producto findByNombre(String nombre);
}
