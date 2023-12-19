package com.dominio.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class PrestamoTest {
	
	private Prestamo prestamo;
	private Usuario usuario;
	private Ejemplar ejemplar;

	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() throws Exception {
		prestamo = new Prestamo(20L, usuario ,ejemplar, new Date(), new Date(),true);
		prestamo.setPrestamoID((long) 20);
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}
	
	@Test
	final void testReserva() throws Exception {
		Prestamo prestamo1 = new Prestamo(20L, usuario ,ejemplar, new Date(), new Date(),true);
	    assertNotNull(prestamo1);

	}
	
	@Test
	final void testGetprestamoID() {
		assertNotNull(prestamo.getPrestamoID());
	}
	
	@Test
	final void testSetprestamoID() {
		long id = (long) 20;
		prestamo.setPrestamoID(id);
		assertEquals(id, prestamo.getPrestamoID());
	}

	@Test
	final void testGetusuario() {
	    Usuario usuario = new Usuario( new HashSet<>(), new HashSet<>(), "12345678A", "Juan", "Pérez", new Date(), 3, "usuario", "1234");
	    prestamo.setUsuario(usuario);
	    assertEquals(usuario, prestamo.getUsuario());
	}

	@Test
	final void testSetusuario() {
	    Usuario usuario = new Usuario( new HashSet<>(), new HashSet<>(), "12345678A", "Juan", "Pérez", new Date(), 3, "usuario", "1234");

	    prestamo.setUsuario(usuario);
	    assertEquals(usuario, prestamo.getUsuario());
	}
	
	@Test
	final void testGetejemplar() {
	    Obra obra = new Obra(new HashSet<>(), "Novela", "La Odisea", 500, 1L, new Date());
	    Ejemplar ejemplar = new Ejemplar(11L, obra,new HashSet<>(), new HashSet<>(),"disponible");


	    prestamo.setEjemplar(ejemplar);
	    assertEquals(ejemplar, prestamo.getEjemplar());
	}

	@Test
	final void testSetejemplar() {
	
	    Obra obra = new Obra(new HashSet<>(), "Novela", "La Odisea", 500, 1L, new Date());
	    Ejemplar ejemplar = new Ejemplar(11L, obra,new HashSet<>(), new HashSet<>(),"disponible");

	    prestamo.setEjemplar(ejemplar);
	    assertEquals(ejemplar, prestamo.getEjemplar());
	}

	@Test
	final void testGetfechaInicioPres() {
	    Date fechaInicio = new Date();
	    prestamo.setFechaInicioPres(fechaInicio);
	    assertEquals(fechaInicio, prestamo.getFechaInicioPres());
	}

	@Test
	final void testSetfechaInicioPres() {
	    Date fechaInicio = new Date();
	    prestamo.setFechaInicioPres(fechaInicio);
	    assertEquals(fechaInicio, prestamo.getFechaInicioPres());
	}

	@Test
	final void testGetfechaFinPres() {
	    Date fechaFin = new Date();
	    prestamo.setFechaFinPres(fechaFin);
	    assertEquals(fechaFin, prestamo.getFechaFinPres());
	}

	@Test
	final void testSetfechaFinPres() {
	    Date fechaFin = new Date();
	    prestamo.setFechaFinPres(fechaFin);
	    assertEquals(fechaFin, prestamo.getFechaFinPres());
	}
	
	@Test
	final void testGetactivo() {
		boolean activo = prestamo.getActivo();
		assertTrue(activo);
	}
	
	@Test
	final void testSetactivo() {
		prestamo.setActivo(true);
		assertEquals(true, prestamo.getActivo());
	}

}
