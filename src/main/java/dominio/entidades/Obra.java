package dominio.entidades;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Obra {
	public Obra() {
	}
	
	
	public Obra(String id, String titulo, String nroPaginas, String fechaPublicacion, Collection<Ejemplar> ejemplares,
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
    private String id;
    @Column
    private String titulo;
    @Column
    private String nroPaginas;
    @Column
    private String fechaPublicacion;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Collection<Ejemplar> ejemplares;

    @ManyToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Collection<Genero> generos;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getNroPaginas() {
		return nroPaginas;
	}


	public void setNroPaginas(String nroPaginas) {
		this.nroPaginas = nroPaginas;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
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