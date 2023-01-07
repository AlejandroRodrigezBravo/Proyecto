package com.arodriguezbravo.catalago.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que representa producto
 * 
 * @author bravo
 * @version 01/05/2022 1.0.0
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;

	@DecimalMin(value = "1")
	private double precio;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	private String foto;

	@NonNull
	private String descripcion;

	@Column(length = 1)
	private Integer cantidad;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	@JsonIgnore
	@ManyToOne
	private Cliente cliente;

	public Producto() {

	}

	public Producto(Long id, @NotEmpty String nombre, @DecimalMin("1") double precio, Date createAt, String foto,
			@NonNull String descripcion, Integer cantidad, Cliente cliente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.createAt = createAt;
		this.foto = foto;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.cliente = cliente;
	}

	public Producto(Long id, @NotEmpty String nombre, @DecimalMin("1") double precio, Date createAt, String foto,
			@NonNull String descripcion, Integer cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.createAt = createAt;
		this.foto = foto;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void restarExistencia(Integer cantidad) {
		this.cantidad -= cantidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", createAt=" + createAt + ", foto="
				+ foto + ", descripcion=" + descripcion + ", cantidad=" + cantidad + "]";
	}

	private static final long serialVersionUID = 1L;

}
