package com.dominio.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
@Entity
@DiscriminatorValue(value="Libro")
public class Libro extends Obra {
	
	public Libro(Set<Autor> autores, Long id, String editorial, String edicion, String isbn, String encuadernacion) {
		super();
		this.autores = autores;
		this.id = id;
		this.editorial = editorial;
		this.edicion = edicion;
		this.isbn = isbn;
		this.encuadernacion = encuadernacion;
	}

	@ManyToMany
    @JoinTable(
            name = "Escribe", 
            joinColumns = @JoinColumn(name = "IDLibro"), 
            inverseJoinColumns = @JoinColumn(name = "IDAutor"))
	private Set<Autor> autores;

	@Id
	@Column(name = "ID", nullable = false, length = 10)
	private Long id;

	@Column(name = "Editorial", nullable = false, length = 25)
	private String editorial;

	@Column(name = "Edicion", nullable = false, length = 25)
	private String edicion;

	@Column(name = "ISBN", unique = true, nullable = false, length = 25)
	private String isbn;

	@Column(name = "Encuadernacion", nullable = false, length = 25)
	private String encuadernacion;

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEncuadernacion() {
		return encuadernacion;
	}

	public void setEncuadernacion(String encuadernacion) {
		this.encuadernacion = encuadernacion;
	}

}