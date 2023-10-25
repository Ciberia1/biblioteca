package dominio.entidades;

import java.time.*;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Obra {
	public Obra() {
	}
	
	
	public Obra(Long id, String titulo, int nroPaginas, LocalDate fechaPublicacion, Collection<Ejemplar> ejemplares,
			Collection<Genero> generos) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.nroPaginas = nroPaginas;
		this.fechaPublicacion = fechaPublicacion;
		this.ejemplares = ejemplares;
		this.generos = generos;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Cambiado de String a Long
	@Column
	private String titulo;
	@Column
	private int nroPaginas;
	@Column
	private LocalDate fechaPublicacion;

	@OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
	private Collection<Ejemplar> ejemplares;

	@OneToMany(mappedBy = "obra", cascade = CascadeType.ALL) // Asumiendo que 'Genero' tiene un campo llamado 'obra'
	private Collection<Genero> generos;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getNroPaginas() {
		return nroPaginas;
	}


	public void setNroPaginas(int nroPaginas) {
		this.nroPaginas = nroPaginas;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Collection<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Collection<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Collection<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(Collection<Genero> generos) {
		this.generos = generos;
	}
    
    

}