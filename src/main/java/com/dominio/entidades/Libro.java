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

}