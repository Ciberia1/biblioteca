package com.dominio.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Prestamo")
public class Prestamo {
	public Prestamo() {
		super();
	}
	public Prestamo(Long prestamoID, Usuario usuario, Ejemplar ejemplar, Date fechaInicioPres, Date fechaFinPres,
			Boolean activo) {
		super();
		this.prestamoID = prestamoID;
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.fechaInicioPres = fechaInicioPres;
		this.fechaFinPres = fechaFinPres;
		this.activo = activo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prestamoID;

	@ManyToOne
	@JoinColumn(name = "DNI")
	private Usuario usuario;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ejemplarID", referencedColumnName = "ejemplarID"),
			@JoinColumn(name = "ID", referencedColumnName = "ID") })
	private Ejemplar ejemplar;

	@Column(name = "FechaInicioPres", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaInicioPres;

	@Column(name = "FechaFinPres", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaFinPres;

	@Column(name = "Activo", nullable = false)
	private Boolean activo;

	public Long getPrestamoID() {
		return prestamoID;
	}

	public void setPrestamoID(Long prestamoID) {
		this.prestamoID = prestamoID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public Date getFechaInicioPres() {
		return fechaInicioPres;
	}

	public void setFechaInicioPres(Date fechaInicioPres) {
		this.fechaInicioPres = fechaInicioPres;
	}

	public Date getFechaFinPres() {
		return fechaFinPres;
	}

	public void setFechaFinPres(Date fechaFinPres) {
		this.fechaFinPres = fechaFinPres;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
}
