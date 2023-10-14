package dominio.entidades;

import java.util.*;

public class Usuario {

	Collection<Prestamo> prestamos;
	Collection<Reserva> reservas;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fechaFinPen;
	private String domicilio;
	private String rol;

}