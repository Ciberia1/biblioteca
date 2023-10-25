package dominio.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Prestamo {
	public Prestamo() {
	}
	
    public Prestamo(Long id, Usuario usuario, Ejemplar ejemplar, Date fechaInicioPres, Date fechaFinPres,
			Boolean activo) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.fechaInicioPres = fechaInicioPres;
		this.fechaFinPres = fechaFinPres;
		this.activo = activo;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Usuario usuario;

    @Column
    private Ejemplar ejemplar;
    
    @Column
    private Date fechaInicioPres;
    @Column
    private Date fechaFinPres;
    @Column
    private Boolean activo;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Ejemplar getEjemplar() {
		return ejemplar;
	}


	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}


	public Date getFechaInicioPres() {
		return fechaInicioPres;
	}


	public void setFechaInicioPres(Date fechaInicioPres) {
		this.fechaInicioPres = fechaInicioPres;
	}


	public Date getFechaFinPres() {
		return fechaFinPres;
	}


	public void setFechaFinPres(Date fechaFinPres) {
		this.fechaFinPres = fechaFinPres;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
    

}