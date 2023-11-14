/**
 * La clase `Autor` representa una entidad de autor con propiedades como identificación, nombre,
 * apellido, biografía y un conjunto de libros que ha escrito.
 */
package com.dominio.entidades;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="Autor")
public class Autor {
	public Autor() {
		super();
	}

	public Autor(Long id, String nombre, String apellido, String biografia, Set<Libro> libros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.biografia = biografia;
		this.libros = libros;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nombre", nullable = false, length = 25)
	private String nombre;

	@Column(name = "Apellido", length = 40)
	private String apellido;

	@Column(name = "Biografia", nullable = false, length = 100)
	private String biografia;
	
    @ManyToMany(mappedBy = "autores")
    private Set<Libro> libros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}

}

