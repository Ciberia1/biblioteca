package dominio.entidades;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Autor {
	public Autor() {
	}
	
	public Autor(String idAutor, String nombre, String apellido, String biografia, Collection<Libro> libros) {
		super();
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.apellido = apellido;
		this.biografia = biografia;
		this.libros = libros;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idAutor;
    @Column
    private String nombre;    
    @Column
    private String apellido;
    @Column
    private String biografia;

    @OneToMany(mappedBy = "autores")
    private Collection<Libro> libros;

	public String getIdAutor() {
		return idAutor;
	}


	public void setIdAutor(String idAutor) {
		this.idAutor = idAutor;
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


	public Collection<Libro> getLibros() {
		return libros;
	}


	public void setLibros(Collection<Libro> libros) {
		this.libros = libros;
	}

    
}