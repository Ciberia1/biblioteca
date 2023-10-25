package dominio.entidades;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Genero {
	public Genero () {
	}
	
	
	public Genero(Long id, String nombre, Collection<Obra> obras) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.obras = obras;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombre;

	@OneToMany(mappedBy = "generos", cascade = CascadeType.ALL) // Se asume que 'Obra' tiene un campo llamado 'genero'
	private Collection<Obra> obras;


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


	public Collection<Obra> getObras() {
		return obras;
	}


	public void setObras(Collection<Obra> obras) {
		this.obras = obras;
	}
	
}