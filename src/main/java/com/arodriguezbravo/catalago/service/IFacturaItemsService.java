package com.arodriguezbravo.catalago.service;

import com.arodriguezbravo.catalago.model.entity.ItemFactura;

/**
 * Funcionalidad de items de factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
public interface IFacturaItemsService {

	/**
	 * Items de factura a guaradar
	 * @param facturaItems a guardar
	 * @return guarda los items de la factura, null en caso contrario
	 */
	ItemFactura save(ItemFactura facturaItems);
}
