package com.arodriguezbravo.catalago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arodriguezbravo.catalago.model.entity.Producto;
import com.arodriguezbravo.catalago.model.repository.IProductoDAO;

/**
 * Implementación de productos
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoDAO productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional
	public Page<Producto> findAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

	

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombreLikeIgnoreCase(String term) {
		return productoDao.findByNombreLikeIgnoreCase(term);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
		
	}

	@Override
	public boolean existsById(Long id) {
		return productoDao.existsById(id);
	}

	@Override
	public boolean existsByNombre(String nombre) {
		return productoDao.existsByNombre(nombre);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional
	public void update(Producto produto) {
		productoDao.save(produto);	
		
	}

	@Override
	public Optional<Producto> get(Long id) {
		return productoDao.findById(id);
	}

	@Override
	public Producto findByNombre(String nombre) {
		return productoDao.findByNombre(nombre);
	}

	
}
