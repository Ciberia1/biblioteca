package dominio.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
@Entity
public class Libro extends Obra {
	public Libro() {
	}

	public Libro(String id, String editorial, String edicion, String isbn, String encuadernacion,
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
    private String id;
    @Column
    private String editorial;
    @Column
    private String edicion;
    @Column
    private String isbn;
    @Column
    private String encuadernacion;

    @ManyToMany
    private Collection<Autor> autores;

	
    public String getId() {
		return id;
	}

	public void setId(String id) {
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