package com.arodriguezbravo.catalago.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.arodriguezbravo.catalago.enums.RolNombre;

/**
 * Clase que representa rol del cliente
 * @author bravo
 *@version 01/05/2022 1.0.0
 */
@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private RolNombre rolNombre;

	public Rol() {
	}

	public Rol(Long id, @NotNull RolNombre rolNombre) {
		this.id = id;
		this.rolNombre = rolNombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	
}
