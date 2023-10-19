package dominio.entidades;

import java.util.*;

public class Ejemplar {

	Obra obra;
	Collection<Reserva> reservas;
	Collection<Prestamo> prestamos;
	private String id;
	private int numReserva;
	private String estado;
	private int nroEjemplar;

}