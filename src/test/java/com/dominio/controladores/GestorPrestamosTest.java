package com.dominio.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dominio.entidades.Ejemplar;
import com.dominio.entidades.Prestamo;
import com.dominio.entidades.Reserva;
import com.dominio.entidades.Usuario;
import com.persistencia.EjemplarDAO;
import com.persistencia.PrestamoDAO;
import com.persistencia.ReservaDAO;
import com.persistencia.UsuarioDAO;

class GestorPrestamosTest {
    @Mock
    private UsuarioDAO usuarioDAO;

    @Mock
    private EjemplarDAO ejemplarDAO;

    @Mock
    private ReservaDAO reservaDAO;
    
    @Mock
    private PrestamoDAO prestamoDAO;
    
    @InjectMocks
    private GestorPrestamos gestorPrestamos;
    
	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}


	
	
	@Test
	public void testPrestarEjemplar_CP1() {
	    // Configura los datos de prueba
	    Usuario usuario = new Usuario(new HashSet<Prestamo>(), new HashSet<Reserva>(), "55154299T", "Nombre", "Apellidos", null, 9, "Rol", "Contraseña");
	    Ejemplar ejemplar = new Ejemplar();

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);
	    when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "55154299T");
	    body.put("ejemplarID", "72991");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.prestarEjemplar(body);

	    // Verifica los resultados
	    assertEquals("Préstamo realizado con éxito", response.getBody()); // verifica el cuerpo de la respuesta
	    assertEquals(HttpStatus.OK, response.getStatusCode()); // verifica el código de estado HTTP

	}



	@Test
	public void testAltaEjemplar_CP2() {
	    // Configura los datos de prueba
	    Ejemplar Ejemplar = new Ejemplar(Long.parseLong("99999"), null, null, null, "Disponible");
	    Ejemplar ejemplar2 = new Ejemplar(Long.parseLong("72991"), null, null, null, "Disponible");
	    
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar2));

	    Collection<Prestamo> prestamos = new HashSet<>();
		Collection<Reserva> reservas  = new HashSet<>();
	    Reserva reserva = new Reserva(Long.parseLong("111111"), null, Ejemplar, new Date());
	    Prestamo prestamo = new Prestamo(Long.parseLong("1111"), null, ejemplar2, new Date(), new Date(), true);
	    prestamos.add(prestamo);
	    reservas.add(reserva);

	    Usuario usuario = new Usuario(prestamos, reservas, "55154299T", "Nombre", "Apellidos", new Date(4044, 10, 11), 10-2, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "55154299T");
	    body.put("ejemplarID", "72991");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.prestarEjemplar(body);

	    // Verifica los resultados
        assertEquals(new ResponseEntity<>("El usuario tiene penalizaciones pendientes.",HttpStatus.FORBIDDEN), response);
	}
	
	@Test
	public void testReservarAlta_CP3() {

	    Usuario usuario = new Usuario(new HashSet<Prestamo>(), new HashSet<Reserva>(), "111111Z", "Nombre", "Apellidos", new Date(1044, 10, 11), 10+11, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("111111Z")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "111111Z");
	    body.put("ejemplarID", "323ZZZ");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.prestarEjemplar(body);

	    // Verifica los resultados
	    assertEquals(new ResponseEntity<>("El DNI introducido no es correcto", HttpStatus.CONFLICT), response);
	}
	
	@Test
	public void testAltaEjemplar_CP4() {
	    // Configura los datos de prueba
	    Ejemplar Ejemplar = new Ejemplar(Long.parseLong("72991"), null, null, null, "Disponible");
	    Ejemplar Ejemplar2 = new Ejemplar(Long.parseLong("9999"), null, null, null, "Disponible");

	    Collection<Prestamo> prestamos = new HashSet<>();
		Collection<Reserva> reservas  = new HashSet<>();
	    Reserva reserva = new Reserva(Long.parseLong("111111"), null, Ejemplar, new Date());
	    Prestamo prestamo = new Prestamo(Long.parseLong("111111"), null, Ejemplar2, new Date(), new Date(), true);
	    prestamos.add(prestamo);
	    reservas.add(reserva);

	    Usuario usuario = new Usuario(prestamos, reservas, null, "Nombre", "Apellidos", new Date(2024, 0, 11), 0, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", null);
	    body.put("ejemplarID", null);

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.prestarEjemplar(body);

	    // Verifica los resultados
        assertEquals(new ResponseEntity<>("El DNI introducido no es correcto", HttpStatus.CONFLICT), response);
	}
	
	@Test
    public void testAltaEjemplar_CP5() {
        // Configura los datos de prueba
        Usuario usuario = new Usuario(new HashSet<Prestamo>(), new HashSet<Reserva>(), "08633642V", "Nombre", "Apellidos", null, 10, "Rol", "Contraseña");
        Ejemplar ejemplar = new Ejemplar(); // Asegúrate de inicializar el ejemplar correctamente

        when(usuarioDAO.findByDni("08633642V")).thenReturn(usuario);
        when(ejemplarDAO.findById(Long.parseLong("7222991"))).thenReturn(Optional.of(ejemplar));

        // Configura el mapa que se pasará al método
        Map<String, String> body = new HashMap<>();
        body.put("dni", "55154299T");
        body.put("ejemplarID", "72991");

        // Realiza la prueba
        ResponseEntity<String> response = gestorPrestamos.prestarEjemplar(body);

        // Verifica los resultados
	    assertEquals(new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND), response);
    }
	
	@Test
    public void testAltaEjemplar_CP6() {
        // Configura los datos de prueba
        Usuario usuario = new Usuario(null, null, "55154299T", "Nombre", "Apellidos", null, -9, "Rol", "Contraseña");
        Ejemplar ejemplar = new Ejemplar(); // Asegúrate de inicializar el ejemplar correctamente

        when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

        // Configura el mapa que se pasará al método
        Map<String, String> body = new HashMap<>();
        body.put("dni", "55154299T");
        body.put("ejemplarID", "72991");

        // Realiza la prueba
        ResponseEntity<String> response = gestorPrestamos.prestarEjemplar(body);

        // Verifica los resultados
        assertEquals(new ResponseEntity<>("No se ha podido acceder correctamente a los datos del usuario", HttpStatus.CONFLICT), response);
    }
	

	@Test
	public void testRealizarDevolucion_CP0() {
	    // Configura los datos de prueba
	    Ejemplar ejemplar = new Ejemplar(Long.parseLong("72991"), null, null, null, "Disponible");

	    Collection<Prestamo> prestamos = new HashSet<>();
	    Prestamo prestamo = new Prestamo(Long.parseLong("111111"), null, ejemplar, new Date(), new Date(), true);	  
	    prestamos.add(prestamo);
	    Usuario usuario = new Usuario(prestamos, new HashSet<Reserva>(), "55154299T", "Nombre", "Apellidos", new Date(2024, 0, 11), 1, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);
	    when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "55154299T");
	    body.put("ejemplarID", "72991");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.realizarDevolucion(body);

	    // Verifica los resultados
	    assertEquals("Devolución realizada con éxito", response.getBody()); // verifica el cuerpo de la respuesta
	    assertEquals(HttpStatus.OK, response.getStatusCode()); // verifica el código de estado HTTP
	}

	
	@Test
	public void testRealizarDevolucion_CP1() {
	    // Configura los datos de prueba
	    Usuario usuario = new Usuario(new HashSet<Prestamo>(), new HashSet<Reserva>(), "55154299T", "Nombre", "Apellidos", new Date(2024, 0, 11), 1, "Rol", "Contraseña");
	    Ejemplar ejemplar = new Ejemplar(); // Asegúrate de inicializar el ejemplar correctamente

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);
	    when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "55154299T");
	    body.put("ejemplarID", "72991");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.realizarDevolucion(body);

	    // Verifica los resultados
	    assertEquals("No se encontró el préstamo", response.getBody()); // verifica el cuerpo de la respuesta
	    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // verifica el código de estado HTTP
	}

	

	@Test
	public void testRealizarDevolucion_CP2() {
	    // Configura los datos de prueba
	    Ejemplar ejemplar = new Ejemplar(Long.parseLong("72991"), null, null, null, "Prestado");
	    when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

	    Collection<Prestamo> prestamos = new HashSet<>();
	    Prestamo prestamo = new Prestamo(Long.parseLong("1111"), null, ejemplar, new Date(), new Date(2024, 12, 11), false); // Modify 'activo' to true
	    prestamos.add(prestamo);

	    Usuario usuario = new Usuario(prestamos, new HashSet<>(), "55154299T", "Nombre", "Apellidos", new Date(4044, 10, 11), 10-2, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "55154299T");
	    body.put("ejemplarID", "72991");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.realizarDevolucion(body);

	    // Verifica los resultados
	    assertEquals(new ResponseEntity<>("No se ha podido devolver la obra debido a que el préstamo ya no está activo",
	        HttpStatus.CONFLICT), response);
	}


	
	

	@Test
	public void testRealizarDevolucion_CP3() {
	    Usuario usuario = new Usuario(null, new HashSet<Reserva>(), "111111Z", "Nombre", "Apellidos", new Date(), 10+11, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("111111Z")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "111111Z");
	    body.put("ejemplarID", "323ZZZ");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.realizarDevolucion(body);

	    // Verifica los resultados
	    assertEquals(new ResponseEntity<>("El DNI del usuario no es correcto", HttpStatus.CONFLICT), response);
	}
	
	@Test
	public void testRealizarDevolucion_CP4() {
	    // Configura los datos de prueba
	    Ejemplar ejemplar = new Ejemplar(Long.parseLong("72991"), null, null, null, "Prestado");
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

	    Collection<Prestamo> prestamos = new HashSet<>();
	    Prestamo prestamo = new Prestamo(Long.parseLong("1111"), null, ejemplar, new Date(), new Date(4024, 01, 11), false);
	    prestamos.add(prestamo);

	    Usuario usuario = new Usuario(prestamos, new HashSet<>(), null, null, null, new Date(4044, 10, 11), 0, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", null);
	    body.put("ejemplarID", null);

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.realizarDevolucion(body);

	    // Verifica los resultados
        assertEquals(new ResponseEntity<>("El DNI del usuario no es correcto", HttpStatus.CONFLICT), response);
	}
	
	
	@Test
	public void testRealizarDevolucion_CP5() {
	    // Configura los datos de prueba
	    Ejemplar Ejemplar = new Ejemplar(Long.parseLong("72991"), null, null, null, "Disponible");
	    Ejemplar Ejemplar2 = new Ejemplar(Long.parseLong("9999"), null, null, null, "Disponible");
	    Collection<Prestamo> prestamos = new HashSet<>();
	    Prestamo prestamo = new Prestamo(Long.parseLong("111111"), null, Ejemplar2, new Date(), new Date(1024, 10, 11), false);
	    prestamos.add(prestamo);

	    Usuario usuario = new Usuario(prestamos, new HashSet<Reserva>(), null, "Nombre", "Apellidos", null, 10, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", null);
	    body.put("ejemplarID", null);

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.realizarDevolucion(body);

	    // Verifica los resultados
        assertEquals(new ResponseEntity<>("El DNI del usuario no es correcto", HttpStatus.CONFLICT), response);
	}
	
	@Test
    public void testRealizarDevolucion_CP6() {
		Ejemplar ejemplar = new Ejemplar(Long.parseLong("72991"), null, null, null, "Prestado");
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

	    Collection<Prestamo> prestamos = new HashSet<>();
	    Prestamo prestamo = new Prestamo(Long.parseLong("1111"), null, ejemplar, new Date(2024,11,11), null, true);
	    prestamos.add(prestamo);

        // Configura los datos de prueba
        Usuario usuario = new Usuario(prestamos, new HashSet<Reserva>(), "55154299T", "Nombre", "Apellidos", null, 10, "Rol", "Contraseña");

        when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

        // Configura el mapa que se pasará al método
        Map<String, String> body = new HashMap<>();
        body.put("dni", "55154299T");
        body.put("ejemplarID", "72991");

        // Realiza la prueba
        ResponseEntity<String> response = gestorPrestamos.realizarDevolucion(body);

        // Verifica los resultados
        assertEquals(new ResponseEntity<>("No se ha podido acceder correctamente a los datos del usuario",HttpStatus.CONFLICT), response);
    }
	


	@Test
    public void testReservarEjemplar_CP1() {
        // Configura los datos de prueba
        Usuario usuario = new Usuario(new HashSet<>(), new HashSet<>(), "55154299T", "Nombre", "Apellidos", null, 10, "Rol", "Contraseña");
        Ejemplar ejemplar = new Ejemplar(); // Asegúrate de inicializar el ejemplar correctamente

        when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

        // Configura el mapa que se pasará al método
        Map<String, String> body = new HashMap<>();
        body.put("dni", "55154299T");
        body.put("ejemplarID", "72991");

        // Realiza la prueba
        ResponseEntity<String> response = gestorPrestamos.reservarEjemplar(body);

        // Verifica los resultados
        assertEquals(new ResponseEntity<>("Reserva realizada con éxito", HttpStatus.OK), response);
    }
	
	@Test
	public void testReservarEjemplar_CP2() {
	    // Configura los datos de prueba
	    Ejemplar Ejemplar = new Ejemplar(Long.parseLong("99999"), null, null, null, "Disponible");
	    Ejemplar ejemplar2 = new Ejemplar(Long.parseLong("72991"), null, null, null, "Disponible");
	    
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar2));

	    Collection<Prestamo> prestamos = new HashSet<>();
		Collection<Reserva> reservas  = new HashSet<>();
	    Reserva reserva = new Reserva(Long.parseLong("111111"), null, Ejemplar, new Date());
	    Prestamo prestamo = new Prestamo(Long.parseLong("1111"), null, ejemplar2, new Date(), new Date(), true);
	    prestamos.add(prestamo);
	    reservas.add(reserva);

	    Usuario usuario = new Usuario(prestamos, reservas, "55154299T", "Nombre", "Apellidos", null, 0, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "55154299T");
	    body.put("ejemplarID", "72991");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.reservarEjemplar(body);

	    // Verifica los resultados
        assertEquals(new ResponseEntity<>("El usuario ya tiene prestado este ejemplar", HttpStatus.CONFLICT), response);
	}

	@Test
	public void testReservarEjemplar_CP3() {
	    Usuario usuario = new Usuario(new HashSet<Prestamo>(), new HashSet<Reserva>(), "111111Z", "Nombre", "Apellidos", null, 0, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("111111Z")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", "111111Z");
	    body.put("ejemplarID", "323ZZZ");

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.reservarEjemplar(body);

	    // Verifica los resultados
	    assertEquals(new ResponseEntity<>("El DNI introducido no es correcto", HttpStatus.CONFLICT), response);
	}
	
	@Test
	public void testReservarEjemplar_CP4() {
	    // Configura los datos de prueba
	    Ejemplar Ejemplar = new Ejemplar(Long.parseLong("72991"), null, null, null, "Disponible");
	    Ejemplar Ejemplar2 = new Ejemplar(Long.parseLong("9999"), null, null, null, "Disponible");

	    Collection<Prestamo> prestamos = new HashSet<>();
		Collection<Reserva> reservas  = new HashSet<>();
	    Reserva reserva = new Reserva(Long.parseLong("111111"), null, Ejemplar, new Date());
	    Prestamo prestamo = new Prestamo(Long.parseLong("111111"), null, Ejemplar2, new Date(), new Date(), true);
	    prestamos.add(prestamo);
	    reservas.add(reserva);

	    Usuario usuario = new Usuario(prestamos, reservas, null, "Nombre", "Apellidos", null, 0, "Rol", "Contraseña");

	    when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);

	    // Configura el mapa que se pasará al método
	    Map<String, String> body = new HashMap<>();
	    body.put("dni", null);
	    body.put("ejemplarID", null);

	    // Realiza la prueba
	    ResponseEntity<String> response = gestorPrestamos.reservarEjemplar(body);

	    // Verifica los resultados
        assertEquals(new ResponseEntity<>("El DNI introducido no es correcto", HttpStatus.CONFLICT), response);
	}

	@Test
    public void testReservarEjemplar_CP5() {
        // Configura los datos de prueba
        Usuario usuario = new Usuario(new HashSet<Prestamo>(), new HashSet<Reserva>(), "08633642V", "Nombre", "Apellidos", null, 10, "Rol", "Contraseña");
        Ejemplar ejemplar = new Ejemplar(); // Asegúrate de inicializar el ejemplar correctamente

        when(usuarioDAO.findByDni("08633642V")).thenReturn(usuario);
        when(ejemplarDAO.findById(Long.parseLong("7222991"))).thenReturn(Optional.of(ejemplar));

        // Configura el mapa que se pasará al método
        Map<String, String> body = new HashMap<>();
        body.put("dni", "55154299T");
        body.put("ejemplarID", "72991");

        // Realiza la prueba
        ResponseEntity<String> response = gestorPrestamos.reservarEjemplar(body);

        // Verifica los resultados
	    assertEquals(new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND), response);
    }
	
	@Test
    public void testReservarEjemplar_CP6() {
        // Configura los datos de prueba
        Usuario usuario = new Usuario(null, null, "55154299T", "Nombre", "Apellidos", null, 10, "Rol", "Contraseña");
        Ejemplar ejemplar = new Ejemplar(); // Asegúrate de inicializar el ejemplar correctamente

        when(usuarioDAO.findByDni("55154299T")).thenReturn(usuario);
        when(ejemplarDAO.findById(Long.parseLong("72991"))).thenReturn(Optional.of(ejemplar));

        // Configura el mapa que se pasará al método
        Map<String, String> body = new HashMap<>();
        body.put("dni", "55154299T");
        body.put("ejemplarID", "72991");

        // Realiza la prueba
        ResponseEntity<String> response = gestorPrestamos.reservarEjemplar(body);

        // Verifica los resultados
        assertEquals(new ResponseEntity<>("No se ha podido acceder correctamente a los datos del usuario", HttpStatus.CONFLICT), response);
    }

}
