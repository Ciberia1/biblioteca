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
	public Reserva() {
		super();
	}
	public Reserva(Long reservaID, Usuario usuario, Ejemplar ejemplar, Date fecha) {
		super();
		this.reservaID = reservaID;
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.fecha = fecha;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservaID;

	@ManyToOne
	@JoinColumn(name = "DNI")
	private Usuario usuario;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ejemplarID", referencedColumnName = "ejemplarID"),
			@JoinColumn(name = "ID", referencedColumnName = "ID") })
	private Ejemplar ejemplar;

	@Column(name = "Fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	public Long getReservaID() {
		return reservaID;
	}

	public void setReservaID(Long reservaID) {
		this.reservaID = reservaID;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
