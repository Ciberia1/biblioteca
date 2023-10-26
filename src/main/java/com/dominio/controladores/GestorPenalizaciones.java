package com.dominio.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dominio.entidades.*;
import com.persistencia.UsuarioDAO;

@Controller
public class GestorPenalizaciones {
	private static final Logger log = LoggerFactory.getLogger(GestorPenalizaciones.class);

	@Autowired
	private UsuarioDAO usuarioDAO;

	/**
	 * 
	 * @param u
	 */
	public void aplicarPenalizacion(Usuario u) {
		// TODO - implement GestorPenalizaciones.aplicarPenalizacion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param u
	 */
	public void comprobarPenalizacion(Usuario u) {
		// TODO - implement GestorPenalizaciones.comprobarPenalizaci�n
		throw new UnsupportedOperationException();
	}

}