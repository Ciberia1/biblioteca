package dominio.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Reserva {
	public Reserva() {
	}
	
	
    public Reserva(Long id, Usuario usuario, Ejemplar ejemplar, Date fecha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.fecha = fecha;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Usuario usuario;

    @Column
    private Ejemplar ejemplar;
    @Column
    private Date fecha;
    
    
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


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
}