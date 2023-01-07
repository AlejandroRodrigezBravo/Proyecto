package com.arodriguezbravo.catalago.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
/**
 * Dao Factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */

@Repository
public interface IFacturaDAO extends JpaRepository<Factura, Long> {

	/**
	 * Busca por factura de cliente
	 * @param cliente a buscar
	 * @return devuelve cliente, en caso contrario null.
	 */
	List<Factura> findByCliente(Cliente cliente);
}
