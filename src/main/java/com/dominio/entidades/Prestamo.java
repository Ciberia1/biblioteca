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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prestamoID;

	@ManyToOne
	@JoinColumn(name = "DNI")
	private Usuario usuario;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "NroEjemplar", referencedColumnName = "NroEjemplar"),
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
}
