package com.dominio.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="claseObra", discriminatorType= DiscriminatorType.STRING)
public class Obra {
	public Obra() {
		super();
	}

	public Obra(Collection<Ejemplar> ejemplares, String genero, String titulo, int nroPaginas, String id,
			Date fechaPublicacion) {
		super();
		this.ejemplares = ejemplares;
		this.genero = genero;
		this.titulo = titulo;
		this.nroPaginas = nroPaginas;
		this.id = id;
		this.fechaPublicacion = fechaPublicacion;
	}

	@OneToMany(mappedBy = "obra")
    private Collection<Ejemplar> ejemplares;

    @Column(name = "Genero")
    private String genero;

    @Column(name = "Titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "NroPaginas", nullable = false)
    private int nroPaginas;

    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private String id;

    @Column(name = "FechaPublicacion", nullable = false)
    @Temporal(TemporalType.DATE)
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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