package com.arodriguezbravo.catalago.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.lowagie.text.DocumentException;

/**
 * Funcionalidad de Factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
public interface IFacturaService {

	/**
	 * Busca lista de factura
	 * @return devuelve la lista de factura, null en caso contrario
	 */
	List<Factura> findAll();
	
	/**
	 * Busca factura por id
	 * @param id a buscar
	 * @return devuelve factura encontrada, null en caso contrario
	 */
	Optional<Factura> findOne(Long id);
	
	/**
	 * Guardar factura
	 * @param factura a guardar
	 * @return insertar factura, null en caso contrario
	 */
	Factura save(Factura factura);
	
	/**
	 * Genera un número aleatorio para factura
	 * @return devuelve el número generado, null en caso contrario
	 */
	String generarNumero();
	
	/**
	 * Busca lista de cliente con factura
	 * @param cliente a buscar
	 * @return devuelve elemento encontrado, null en caso contrario
	 */
	List<Factura> findByCliente(Cliente cliente);
	
	void enviarFacturaPorCorreo(HttpServletResponse response, String destinatario)
			throws MessagingException, DocumentException, IOException;
}
