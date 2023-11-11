package com.dominio.entidades;

import java.util.*;

import jakarta.persistence.CascadeType;
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

	public Ejemplar() {
		super();
	}

	public Ejemplar(Long ejemplarID, Obra obra, Collection<Reserva> reservas, Collection<Prestamo> prestamos, String estado) {
		super();
		this.ejemplarID = ejemplarID;
		this.obra = obra;
		this.reservas = reservas;
		this.prestamos = prestamos;
		this.estado = estado;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ejemplarID;

	@ManyToOne
	@JoinColumn(name = "ID")
	private Obra obra;

	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.REMOVE)
	private Collection<Reserva> reservas;

	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.REMOVE)
	private Collection<Prestamo> prestamos;

	@Column(name = "Estado", nullable = false, length = 25)
	private String estado;

	public Long getEjemplarID() {
		return ejemplarID;
	}

	public void setEjemplarID(Long ejemplarID) {
		this.ejemplarID = ejemplarID;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Collection<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Collection<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Collection<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Collection<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}