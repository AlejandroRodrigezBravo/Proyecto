package com.arodriguezbravo.catalago.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arodriguezbravo.catalago.model.entity.ItemFactura;
import com.arodriguezbravo.catalago.model.repository.IFacturaItemsDAO;

/**
 * Implementación de items de factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Service
public class IFacturaItemsServiceimpl implements IFacturaItemsService {

	@Autowired
	private IFacturaItemsDAO itemsDao;
	
	@Override
	public ItemFactura save(ItemFactura facturaItems) {
		return itemsDao.save(facturaItems);
	}

}
