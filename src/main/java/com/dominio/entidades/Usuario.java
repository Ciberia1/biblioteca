package com.dominio.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Usuario")
public class Usuario {

	
	public Usuario() {
		super();
	}

	public Usuario(Collection<Prestamo> prestamos, Collection<Reserva> reservas, String dni, String nombre,
			String apellidos, Date fechaFinPen, int cupo, String rol, String contrasena) {
		super();
		this.prestamos = prestamos;
		this.reservas = reservas;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaFinPen = fechaFinPen;
		this.cupo = cupo;
		this.rol = rol;
		this.contrasena = contrasena;
	}

	@OneToMany(mappedBy = "usuario")
	private Collection<Prestamo> prestamos;

	@OneToMany(mappedBy = "usuario")
	private Collection<Reserva> reservas;

	@Id
	@Column(name = "DNI", nullable = false, length = 20)
	private String dni;

	@Column(name = "Nombre", nullable = false, length = 25)
	private String nombre;

	@Column(name = "Apellidos", nullable = false, length = 40)
	private String apellidos;

	@Column(name = "FechaFinPen", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaFinPen;

	@Column(name = "Cupo", nullable = false, length = 40)
	private int cupo;

	@Column(name = "Rol", nullable = false, length = 25)
	private String rol;

	@Column(name = "Contrasena", nullable = false, length = 25)
	private String contrasena;

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

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}