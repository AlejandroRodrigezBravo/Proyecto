package com.arodriguezbravo.catalago.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arodriguezbravo.catalago.model.entity.Cliente;
import com.arodriguezbravo.catalago.model.entity.Factura;
import com.arodriguezbravo.catalago.model.entity.Producto;
import com.arodriguezbravo.catalago.model.repository.IClienteDAO;
import com.arodriguezbravo.catalago.model.repository.IFacturaDAO;
import com.arodriguezbravo.catalago.model.repository.IProductoDAO;

/**
 * Implementación del servicio cliente
 * @author bravo
 * @version 01/05/2022 1.0.0
 */

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDAO clienteDao;
	
	@Autowired
	private IProductoDAO productoDao;
	
	@Autowired
	private IFacturaDAO facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findOne(Long id) {
		return clienteDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id); 
	}

	@Override
	public Optional<Cliente> getByNombreUsuario(String nombreUsuario) {
		return clienteDao.findByNombreUsuario(nombreUsuario);
	}

	@Override
	public boolean existsByNombreusuario(String nombreUsuario) {
		return clienteDao.existsByNombreUsuario(nombreUsuario);
	}

	@Override
	public boolean existsById(Long id) {
		return clienteDao.existsById(id);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void update(Cliente cliente) {
		clienteDao.save(cliente);
		
	}
	
}
