package com.arodriguezbravo.catalago.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arodriguezbravo.catalago.enums.RolNombre;
import com.arodriguezbravo.catalago.model.entity.Rol;
import com.arodriguezbravo.catalago.model.repository.RolDAO;

/**
 * Implementación de rol de cliente 
 * @author bravo
 * @version 01/05/2022 1.0.0
 * 
 */

@Service
@Transactional
public class RolServiceImpl implements IRolService{

	@Autowired
	private RolDAO rolDao;
	
	@Override
	public Rol save(Rol rol) {
		return rolDao.save(rol);
	}

	@Override
	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolDao.findByRolNombre(rolNombre);
	}

	@Override
	public boolean existsByRolNombre(RolNombre rolNombre) {
		return rolDao.existsByRolNombre(rolNombre);
	}

}
