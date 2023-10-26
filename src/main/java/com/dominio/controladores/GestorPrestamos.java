package com.dominio.controladores;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.persistencia.*;

@Controller


public class GestorPrestamos {
	private static final Logger log = LoggerFactory.getLogger(GestorPrestamos.class);

	@Autowired
	private PrestamoDAO prestamoDAO;
	@Autowired
	private ReservaDAO reservaDAO;

	/**
	 * 
	 * @param isbn
	 * @param idEjemplar
	 * @param idUsuario
	 */
	public void realizarPrestamo(String isbn, String idEjemplar, String idUsuario) {

		//Obtiene la fecha de hoy
		Date fechaHoy = new Date(System.currentTimeMillis());
		//Obtiene la fecha de hoy + 15 dias
		Date fechaFin = new Date(System.currentTimeMillis() + 15*24*60*60*1000);
		//Crea un nuevo prestamo
		

		// TODO - implement GestorPrestamos.realizarPrestamo	
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param isbn
	 * @param idEjemplar
	 * @param idUsuario
	 */
	public void realizarDevolucion(String isbn, String idEjemplar, String idUsuario) {
		// TODO - implement GestorPrestamos.realizarDevolucion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idUsuario
	 * @param isbn
	 */
	public void realizarReserva(String idUsuario, String isbn) {
		// TODO - implement GestorPrestamos.realizarReserva
		throw new UnsupportedOperationException();
	}

}