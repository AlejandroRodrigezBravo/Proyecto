package com.arodriguezbravo.catalago.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.arodriguezbravo.catalago.model.repository.IFacturaDAO;

/**
 * Implementación de  factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Service
public class IFacturaServiceImpl  implements IFacturaService{

	@Autowired
	private IFacturaDAO facturaDao;
	
	@Override
	public List<Factura> findAll() {
		return  facturaDao.findAll();
	}

	@Override
	public Optional<Factura> findOne(Long id) {
		return facturaDao.findById(id);
	}

	@Override
	public Factura save(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	public String generarNumero() {
		int numero=0;
		String numeroConcatenado="";
		
		List<Factura> ordenes = findAll();
		
		List<Integer> numeros= new ArrayList<>();
		
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt( o.getNumero())));
		
		if (ordenes.isEmpty()) {
			numero=1;
		}else {
			numero= numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if (numero<10) { //0000001000
			numeroConcatenado="000000000"+String.valueOf(numero);
		}else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
		}else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}else if(numero<10000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}		
		
		return numeroConcatenado;
	}

	@Override
	public List<Factura> findByCliente(Cliente cliente) {
		return facturaDao.findByCliente(cliente);
	}

}
