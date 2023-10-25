package dominio.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
@Entity
public class Libro extends Obra {
	public Libro() {
	}

	public Libro(Long id, String editorial, String edicion, String isbn, String encuadernacion,
			Collection<Autor> autores) {
		super();
		this.id = id;
		this.editorial = editorial;
		this.edicion = edicion;
		this.isbn = isbn;
		this.encuadernacion = encuadernacion;
		this.autores = autores;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Añadido para generar automáticamente el valor de 'id'
	private Long id;
	@Column
	private String editorial;
	@Column
	private String edicion;
	@Column
	private String isbn;
	@Column
	private String encuadernacion;

	@ManyToMany
	@JoinTable(
	  name = "escribe", 
	  joinColumns = @JoinColumn(name = "libro_id"), 
	  inverseJoinColumns = @JoinColumn(name = "autor_id")
	)
	private Collection<Autor> autores;


	
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

	public Collection<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Collection<Autor> autores) {
		this.autores = autores;
	}}