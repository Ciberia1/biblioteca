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
public class Usuario {
	public Usuario() {
	}
	
	
	public Usuario(String dni, String nombre, String apellidos, Date fechaFinPen, String domicilio, String rol,
			Collection<Prestamo> prestamos, Collection<Reserva> reservas) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaFinPen = fechaFinPen;
		this.domicilio = domicilio;
		this.rol = rol;
		this.prestamos = prestamos;
		this.reservas = reservas;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String dni;
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private Date fechaFinPen;
    @Column
    private String domicilio;
    @Column
    private String rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Collection<Prestamo> prestamos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Collection<Reserva> reservas;

	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public Date getFechaFinPen() {
		return fechaFinPen;
	}


	public void setFechaFinPen(Date fechaFinPen) {
		this.fechaFinPen = fechaFinPen;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public Collection<Prestamo> getPrestamos() {
		return prestamos;
	}


	public void setPrestamos(Collection<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}


	public Collection<Reserva> getReservas() {
		return reservas;
	}


	public void setReservas(Collection<Reserva> reservas) {
		this.reservas = reservas;
	}
    
    
}