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
@Table(name = "Reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservaID;

	@ManyToOne
	@JoinColumn(name = "DNI")
	private Usuario usuario;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "NroEjemplar", referencedColumnName = "NroEjemplar"),
			@JoinColumn(name = "ID", referencedColumnName = "ID") })
	private Ejemplar ejemplar;

	@Column(name = "Fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
}
