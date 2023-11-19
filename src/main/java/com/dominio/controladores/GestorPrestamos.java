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

	@PostMapping("/devolverEjemplar")
	public ResponseEntity<String> realizarDevolucion(@RequestBody Map<String, String> body) {
		String dni = body.get("dni");
		String ejemplarID = body.get("ejemplarID");

		// Buscar al usuario
		Usuario usuario = usuarioDAO.findByDni(dni);
		if (usuario == null) {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

		// Buscar al ejemplar
		Optional<Ejemplar> ejemplarOpt = ejemplarDAO.findById(Long.parseLong(ejemplarID));
		if (!ejemplarOpt.isPresent()) {
			return new ResponseEntity<>("Ejemplar no encontrado", HttpStatus.NOT_FOUND);
		}
		Ejemplar ejemplar = ejemplarOpt.get();

		// Buscar el préstamo
		Collection<Prestamo> prestamos = usuario.getPrestamos();
		Prestamo prestamo = null;
		for (Prestamo p : prestamos) {
			if (p.getEjemplar().equals(ejemplar) && p.getActivo()) {
				prestamo = p;
				break;
			}
		}
		if (prestamo == null) {
			return new ResponseEntity<>("No se encontró el préstamo", HttpStatus.NOT_FOUND);
		}

		// Actualizar el préstamo
		prestamo.setActivo(false);
		prestamoDAO.save(prestamo);

		// Comprobar si la devolución es tardía
		if (prestamo.getFechaFinPres().before(new Date())) {
			// Aplicar una penalización (e.g., el usuario no puede pedir préstamos durante 7
			// días)
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, 7);
			usuario.setFechaFinPen(c.getTime());
			usuarioDAO.save(usuario);
		}

		// Actualizar el cupo del usuario
		usuario.setCupo(usuario.getCupo() + 1);
		usuarioDAO.save(usuario);

		ejemplar.setEstado("Disponible");
		ejemplarDAO.save(ejemplar);

		return new ResponseEntity<>("Devolución realizada con éxito", HttpStatus.OK);
	}

	@PostMapping("/reservarEjemplar")
	public ResponseEntity<String> reservarEjemplar(@RequestBody Map<String, String> body) {
		String dni = body.get("dni");
		String ejemplarID = body.get("ejemplarID");

		Usuario usuario = usuarioDAO.findByDni(dni);
		if (usuario == null) {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

		Optional<Ejemplar> ejemplarOpt = ejemplarDAO.findById(Long.parseLong(ejemplarID));
		if (!ejemplarOpt.isPresent()) {
			return new ResponseEntity<>("Ejemplar no encontrado", HttpStatus.NOT_FOUND);
		}

		Ejemplar ejemplar = ejemplarOpt.get();

	    // Verificar si el usuario ya tiene prestado el ejemplar
	    Collection<Prestamo> prestamos = usuario.getPrestamos();
	    for (Prestamo p : prestamos) {
	        if (p.getEjemplar().equals(ejemplar)) {
	            return new ResponseEntity<>("El usuario ya tiene prestado este ejemplar", HttpStatus.CONFLICT);
	        }
	    }
	    
		// Verificar si el usuario ya ha reservado el ejemplar
		Collection<Reserva> reservas = usuario.getReservas();
		for (Reserva r : reservas) {
			if (r.getEjemplar().equals(ejemplar)) {
				return new ResponseEntity<>("El usuario ya ha reservado este ejemplar", HttpStatus.CONFLICT);
			}
		}

		Reserva reserva = new Reserva();
		reserva.setUsuario(usuario);
		reserva.setEjemplar(ejemplar);
		reserva.setFecha(new Date());

		reservaDAO.save(reserva);

		ejemplar.setEstado("Reservado");
		ejemplarDAO.save(ejemplar);

		return new ResponseEntity<>("Reserva realizada con éxito", HttpStatus.OK);
	}
}