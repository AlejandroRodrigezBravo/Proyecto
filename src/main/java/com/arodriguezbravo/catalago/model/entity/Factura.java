package com.arodriguezbravo.catalago.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

/**
 * Clase que representa a la factura
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;

	
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_recibida")
	private Date fechaRecibida;

	private double total;

	@ManyToOne()
	private Cliente cliente;

	@OneToMany(mappedBy = "factura")
	private List<ItemFactura> detalles;

	public Factura() {
	}

	public Factura(Long id, String numero, @NotEmpty String descripcion, Date fechaCreacion, Date fechaRecibida,
			double total) {
		super();
		this.id = id;
		this.numero = numero;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaRecibida = fechaRecibida;
		this.total = total;
	}

	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibida() {
		return fechaRecibida;
	}

	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	public List<ItemFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<ItemFactura> detalles) {
		this.detalles = detalles;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", numero=" + numero + ", descripcion=" + descripcion + ", fechaCreacion="
				+ fechaCreacion + ", fechaRecibida=" + fechaRecibida + ", total=" + total + "]";
	}

	private static final long serialVersionUID = 1L;
}
