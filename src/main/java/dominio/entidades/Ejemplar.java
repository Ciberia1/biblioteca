package dominio.entidades;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ejemplar {
	public Ejemplar() {
	}

	public Ejemplar(Long id, int numReserva, String estado, int nroEjemplar, Obra obra, Collection<Reserva> reservas,
			Collection<Prestamo> prestamos) {
		super();
		this.id = id;
		this.numReserva = numReserva;
		this.estado = estado;
		this.nroEjemplar = nroEjemplar;
		this.obra = obra;
		this.reservas = reservas;
		this.prestamos = prestamos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Cambiado de String a Long
	@Column
	private int numReserva;
	@Column
	private String estado;
	@Column
	private int nroEjemplar;

	@ManyToOne(targetEntity = Obra.class) // Agregado targetEntity para especificar la clase objetivo
	private Obra obra;

	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL)
	private Collection<Reserva> reservas;

	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL)
	private Collection<Prestamo> prestamos;
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getNroEjemplar() {
		return nroEjemplar;
	}

	public void setNroEjemplar(int nroEjemplar) {
		this.nroEjemplar = nroEjemplar;
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

}