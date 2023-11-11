package com.dominio.entidades;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="claseObra", discriminatorType= DiscriminatorType.STRING)
public class Obra {
	public Obra() {
		super();
	}

	public Obra(Collection<Ejemplar> ejemplares, String genero, String titulo, int nroPaginas, Long id,
			Date fechaPublicacion) {
		super();
		this.ejemplares = ejemplares;
		this.genero = genero;
		this.titulo = titulo;
		this.nroPaginas = nroPaginas;
		this.id = id;
		this.fechaPublicacion = fechaPublicacion;
	}

	@OneToMany(mappedBy = "obra", cascade = CascadeType.REMOVE)
    private Collection<Ejemplar> ejemplares;

    @Column(name = "Genero")
    private String genero;

    @Column(name = "Titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "NroPaginas", nullable = false)
    private int nroPaginas;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 10)
    private Long id;

    @Column(name = "FechaPublicacion", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;
    

    public Collection<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Collection<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public boolean getEsLibro() {
	    return this instanceof Libro;
	}

}