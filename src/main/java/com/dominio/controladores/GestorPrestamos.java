/**
 * La clase GestorPrestamos en Java se encarga de los procesos de préstamo, devolución y reserva de
 * ejemplares de libros para los usuarios, actualizando los datos necesarios en la base de datos.
 */
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

	/**
	 * La función `prestarEjemplar` es un método Java que maneja el proceso de
	 * prestar un libro a un usuario, incluida la verificación de si el usuario es
	 * elegible para el préstamo, la creación de un nuevo registro de préstamo, la
	 * actualización de la cuota del usuario, la actualización del estado del libro
	 * y la eliminación de cualquier reservas para el libro.
	 * 
	 * @param body {
	 * @return El método devuelve una ResponseEntity<String>.
	 */
	@PostMapping("/prestarEjemplar")
	public ResponseEntity<String> prestarEjemplar(@RequestBody Map<String, String> body) {
		String dni = body.get("dni");
		String ejemplarID = body.get("ejemplarID");
		Usuario usuario = usuarioDAO.findByDni(dni);
		if (dni == null || !validarDNI(dni)) {
			return new ResponseEntity<>("El DNI introducido no es correcto", HttpStatus.CONFLICT);
		}

		Optional<Ejemplar> ejemplarOpt = ejemplarDAO.findById(Long.parseLong(ejemplarID));

		if (usuario == null) {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

		if (!esNumero(ejemplarID) || ejemplarID == null || ejemplarOpt == null) {
			return new ResponseEntity<>("El número de ejemplar introducido no es correcto", HttpStatus.CONFLICT);
		}

		if (usuario.getPrestamos() == null || usuario.getReservas() == null) {
			return new ResponseEntity<>("No se ha podido acceder correctamente a los datos del usuario",
					HttpStatus.CONFLICT);
		}

		if (!ejemplarOpt.isPresent()) {
			return new ResponseEntity<>("Ejemplar no encontrado", HttpStatus.NOT_FOUND);
		}

		// Comprueba si el usuario tiene el cupo completo o tiene penalizaciones
		// pendientes
		if (0 >= usuario.getCupo() ) {
			return new ResponseEntity<>("El usuario tiene el cupo completo",
					HttpStatus.FORBIDDEN);
		}

		if (usuario.getFechaFinPen() != null && usuario.getFechaFinPen().after(new Date())) {
		    return new ResponseEntity<>("El usuario tiene penalizaciones pendientes.", HttpStatus.FORBIDDEN);
		}

		// Crea un nuevo préstamo
		Prestamo prestamo = new Prestamo();
		prestamo.setUsuario(usuario);

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
			if (r.getEjemplar().getEjemplarID().equals(prestamo.getPrestamoID()))
				reservaDAO.delete(r);
		}

		return new ResponseEntity<>("Préstamo realizado con éxito", HttpStatus.OK);
	}

	/**
	 * La función `realizarDevolucion` en Java maneja el proceso de devolución de un
	 * artículo prestado, actualizando los datos necesarios en la base de datos y
	 * aplicando penalizaciones si la devolución se retrasa.
	 * 
	 * @param body {
	 * @return El método devuelve una ResponseEntity<String>.
	 */
	@PostMapping("/devolverEjemplar")
	public ResponseEntity<String> realizarDevolucion(@RequestBody Map<String, String> body) {
		
		String dni = body.get("dni");
		String ejemplarID = body.get("ejemplarID");
		Usuario usuario = usuarioDAO.findByDni(dni);
		if (dni == null || !validarDNI(dni)) {
			return new ResponseEntity<>("El DNI del usuario no es correcto", HttpStatus.CONFLICT);
		}

		Optional<Ejemplar> ejemplarOpt = ejemplarDAO.findById(Long.parseLong(ejemplarID));

		if (usuario == null) {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

		if (!esNumero(ejemplarID) || ejemplarID == null || ejemplarOpt == null||!ejemplarOpt.isPresent()) {
			return new ResponseEntity<>("Ejemplar no encontrado", HttpStatus.NOT_FOUND);
		}

		if (usuario.getPrestamos() == null || usuario.getReservas() == null||usuario.getFechaFinPen()==null) {
			return new ResponseEntity<>("No se ha podido acceder correctamente a los datos del usuario",
					HttpStatus.CONFLICT);
		}

		
		Ejemplar ejemplar = ejemplarOpt.get();

		// Buscar el préstamo
		Collection<Prestamo> prestamos = usuario.getPrestamos();
		Prestamo prestamo = null;
		for (Prestamo p : prestamos) {
			if (p.getEjemplar().equals(ejemplar)) {
				prestamo = p;
				break;
			}
		}
		
		if (prestamo == null) {
			return new ResponseEntity<>("No se encontró el préstamo", HttpStatus.NOT_FOUND);
		}

		// Actualizar el préstamo
		if(!prestamo.getActivo()) {
			return new ResponseEntity<>("No se ha podido devolver la obra debido a que el préstamo ya no está activo",
					HttpStatus.CONFLICT);
		}
		
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
		}else if(prestamo.getFechaFinPres()==null) {
			return new ResponseEntity<>("No se ha podido acceder correctamente a los datos del préstamo",
					HttpStatus.CONFLICT);
		}

		// Actualizar el cupo del usuario
		usuario.setCupo(usuario.getCupo() + 1);
		usuarioDAO.save(usuario);

		ejemplar.setEstado("Disponible");
		ejemplarDAO.save(ejemplar);

		return new ResponseEntity<>("Devolución realizada con éxito", HttpStatus.OK);
	}

	/**
	 * La función "reservarEjemplar" en el código Java permite a un usuario reservar
	 * una copia del libro verificando si el usuario existe, si la copia del libro
	 * existe, si el usuario ya tiene la copia del libro prestada y si el usuario ya
	 * ha reservado la copia del libro. .
	 * 
	 * @param body {
	 * @return El método devuelve una ResponseEntity<String>.
	 */

	@PostMapping("/reservarEjemplar")
	public ResponseEntity<String> reservarEjemplar(@RequestBody Map<String, String> body) {
		String dni = body.get("dni");
		String ejemplarID = body.get("ejemplarID");

		if (dni == null || !validarDNI(dni)) {
			return new ResponseEntity<>("El DNI introducido no es correcto", HttpStatus.CONFLICT);
		}

		Usuario usuario = usuarioDAO.findByDni(dni);
		Optional<Ejemplar> ejemplarOpt = ejemplarDAO.findById(Long.parseLong(ejemplarID));

		if (usuario == null) {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}

		if (!esNumero(ejemplarID) || ejemplarID == null || ejemplarOpt == null) {
			return new ResponseEntity<>("El número de ejemplar introducido no es correcto", HttpStatus.CONFLICT);
		}

		if (usuario.getPrestamos() == null || usuario.getReservas() == null) {
			return new ResponseEntity<>("No se ha podido acceder correctamente a los datos del usuario",
					HttpStatus.CONFLICT);
		}

		if (!ejemplarOpt.isPresent()) {
			return new ResponseEntity<>("Ejemplar no encontrado", HttpStatus.NOT_FOUND);
		}

		Ejemplar ejemplar = ejemplarOpt.get();

		// Verificar si el usuario ya tiene prestado el ejemplar
		Collection<Prestamo> prestamos = usuario.getPrestamos();
		if (usuario.getPrestamos() != null) {
			for (Prestamo p : prestamos) {
				if (p.getEjemplar().equals(ejemplar)) {
					return new ResponseEntity<>("El usuario ya tiene prestado este ejemplar", HttpStatus.CONFLICT);
				}
			}
		}

		// Verificar si el usuario ya ha reservado el ejemplar
		Collection<Reserva> reservas = usuario.getReservas();
		if (usuario.getReservas() != null) {
			for (Reserva r : reservas) {
				if (r.getEjemplar().equals(ejemplar)) {
					return new ResponseEntity<>("El usuario ya ha reservado este ejemplar", HttpStatus.CONFLICT);
				}
			}
		}

		if (usuario.getFechaFinPen() != null && usuario.getFechaFinPen().after(new Date())) {
		    return new ResponseEntity<>("El usuario tiene penalizaciones pendientes.", HttpStatus.FORBIDDEN);
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

	private boolean esNumero(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean validarDNI(String dni) {
		if (dni == null || dni.length() != 9) {
			return false;
		}

		String dniNum = dni.substring(0, 8);
		char dniChar = Character.toUpperCase(dni.charAt(8));

		try {
			int index = Integer.parseInt(dniNum) % 23;
			char expectedChar = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(index);

			return dniChar == expectedChar;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}