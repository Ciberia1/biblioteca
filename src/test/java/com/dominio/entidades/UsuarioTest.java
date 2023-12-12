package com.dominio.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	
	private Usuario usuario;
	private static Collection<Reserva> reservas = new HashSet<>();
	private static Collection<Reserva> reservado = new HashSet<>();
	private static Collection<Prestamo> prestamos = new HashSet<>();
	private static Collection<Prestamo> prestado = new HashSet<>();
	private static Collection<Ejemplar> ejemplares = new HashSet<>();
	
	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass");
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	@BeforeEach
	protected void setUp() throws Exception {
		System.out.println("@Before");
		usuario = new Usuario(prestado,reservado ,"05749924Q", "Ana", "García", new Date () ,2 ,"usuario", "1234");
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	public final void testUsuario() throws Exception {
		Usuario  usuario1 = new Usuario(prestado,reservado ,"05749924Q", "Ana", "García", new Date () ,2 ,"usuario", "1234");
		assertTrue(usuario1.getDni().equals(usuario.getDni())
				&& usuario1.getNombre().equals(usuario.getNombre()) && usuario1.getApellidos().equals(usuario.getApellidos()) && usuario1.getRol().equals(usuario.getRol()) && usuario1.getContrasena().equals(usuario.getContrasena()));
	}
	
	@Test
	public final void testGetprestamos() {
		Collection<Prestamo> prestado = usuario.getPrestamos();
		assertEquals(prestamos, prestado);
	}
	
	@Test
	public final void testSetprestamos() {
		prestado.add(new Prestamo());
		usuario.setPrestamos(prestado);
		assertEquals(prestado, usuario.getPrestamos());
	}
	
	@Test
	public final void testGetreservas() {
		Collection<Reserva> reservas = usuario.getReservas();
		assertEquals(reservas, reservas);
	}
	
	@Test
	public final void testSetreservas() {
		reservado.add(new Reserva());
		usuario.setReservas(reservado);
		assertEquals(reservado, usuario.getReservas());
	}
	
	@Test
	public final void testGetdni() {
		assertEquals("05749924Q", usuario.getDni());
	}
	
	@Test
	public final void testSetdni() {
		String dni = "05749924Q";
		 usuario.setDni(dni);
		assertEquals(dni, usuario.getDni());
	}
	
	@Test
	public final void testGetnombre() {
		assertEquals("Ana", usuario.getNombre());
	}
	
	@Test
	public final void testSetnombre() {
		String nombre = "Ana";
		 usuario.setNombre(nombre);
		assertEquals(nombre, usuario.getNombre());
	}
	
	@Test
	public final void testGetapellidos() {
		assertEquals("García", usuario.getApellidos());
	}
	
	@Test
	public final void testSetapellidos() {
		String apellidos = "García";
		 usuario.setApellidos(apellidos);
		assertEquals(apellidos, usuario.getApellidos());
	}
	
	@Test
	public final void testGetfechaFinPen() {
		
	}
	
	@Test
	public final void testSetfechaFinPen() {
		
	}
	
	@Test
	public final void testGetcupo() {
		assertEquals(2, usuario.getCupo());
	}
	
	@Test
	public final void testSetcupo() {
		int cupo = 2;
		usuario.setCupo(cupo);
		assertEquals(cupo, usuario.getCupo());
	}
	
	@Test
	public final void testGetrol() {
		assertEquals("usuario", usuario.getRol());
	}
	
	@Test
	public final void testSetrol() {
		String rol = "usuario";
		 usuario.setRol(rol);
		assertEquals(rol, usuario.getRol());
	}
	
	@Test
	public final void testGetcontrasena() {
		assertEquals("1234", usuario.getContrasena());
	}
	
	@Test
	public final void testSetcontrasena() {
		String contrasena = "1234";
		 usuario.setContrasena(contrasena);
		assertEquals(contrasena, usuario.getContrasena());
	}
}
