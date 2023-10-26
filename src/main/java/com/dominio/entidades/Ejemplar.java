package com.dominio.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ejemplar")
public class Ejemplar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ejemplarID;

	@ManyToOne
	@JoinColumn(name = "ID")
	private Obra obra;

	@OneToMany(mappedBy = "ejemplar")
	private Collection<Reserva> reservas;

	@OneToMany(mappedBy = "ejemplar")
	private Collection<Prestamo> prestamos;

	@Column(name = "NroEjemplar", nullable = false)
	private int nroEjemplar;

	@Column(name = "NumReserva")
	private int numReserva;

	@Column(name = "Estado", nullable = false, length = 25)
	private String estado;

}