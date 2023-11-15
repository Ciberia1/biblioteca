package com.dominio.controladores;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dominio.entidades.Ejemplar;
import com.dominio.entidades.Prestamo;
import com.dominio.entidades.Reserva;
import com.dominio.entidades.Usuario;
import com.persistencia.*;

@Controller
public class GestorPrestamos {

	@Autowired
	private PrestamoDAO prestamoDAO;
	@Autowired
	private ReservaDAO reservaDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private EjemplarDAO ejemplarDAO;

	@PostMapping("/prestarEjemplar")
	public ResponseEntity<String> prestarEjemplar(@RequestBody Map<String, String> body) {
		String dni = body.get("dni");
		String ejemplarID = body.get("ejemplarID");
		Usuario usuario = usuarioDAO.findByDni(dni);
		if (usuario == null) {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

		// Comprueba si el usuario tiene el cupo completo o tiene penalizaciones
		// pendientes
		if (0 >= usuario.getCupo() || usuario.getFechaFinPen().after(new Date())) {
			return new ResponseEntity<>("El usuario tiene el cupo completo o tiene penalizaciones pendientes.",
					HttpStatus.FORBIDDEN);
		}

		// Crea un nuevo préstamo
		Prestamo prestamo = new Prestamo();
		prestamo.setUsuario(usuario);
		Optional<Ejemplar> ejemplarOpt = ejemplarDAO.findById(Long.parseLong(ejemplarID));
		if (!ejemplarOpt.isPresent()) {
			return new ResponseEntity<>("Ejemplar no encontrado", HttpStatus.NOT_FOUND);
		}
		Ejemplar ejemplar = ejemplarOpt.get();
		prestamo.setEjemplar(ejemplar);

		// Aquí puedes establecer otras propiedades del préstamo, como la fecha de
		// inicio, la fecha de vencimiento, etc.
		prestamo.setFechaInicioPres(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 30);
		prestamo.setFechaFinPres(c.getTime());
		prestamo.setActivo(true);

		// Guarda el préstamo en la base de datos
		prestamoDAO.save(prestamo);

		// Decrementa el cupo
		usuario.setCupo(usuario.getCupo() - 1);
		usuarioDAO.save(usuario);

		ejemplar.setEstado("Prestado");
		ejemplarDAO.save(ejemplar);
		
	    // Elimina todas las reservas para este ejemplar
	    Collection<Reserva> reservas = reservaDAO.findAll();
	    for (Reserva r : reservas) {
	    	if(r.getEjemplar().getEjemplarID().equals(prestamo.getPrestamoID()))
	        reservaDAO.delete(r);
	    }


		return new ResponseEntity<>("Préstamo realizado con éxito", HttpStatus.OK);
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