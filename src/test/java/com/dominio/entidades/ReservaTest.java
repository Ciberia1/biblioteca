package com.dominio.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservaTest {
	private Reserva reserva;
	private Usuario usuario;
	private Ejemplar ejemplar;
	

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
		reserva = new Reserva(40L, usuario ,ejemplar, new Date());
		reserva.setReservaID((long) 40);
		
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	final void testReserva() throws Exception {
	    Reserva reserva1 = new Reserva(40L, usuario ,ejemplar, new Date());
	    assertNotNull(reserva1);
	}

	
	@Test
	final void testGetreservaId() {
		assertNotNull(reserva.getReservaID());
	}
	
	@Test
	final void testSetreservaId() {
		long id = (long) 40;
		reserva.setReservaID(id);
		assertEquals(id, reserva.getReservaID());
	}
	

	@Test
	final void testGetusuario() {
	    Usuario usuario = new Usuario( new HashSet<>(), new HashSet<>(), "12345678A", "Juan", "Pérez", new Date(), 3, "usuario", "1234");
	    reserva.setUsuario(usuario);
	    assertEquals(usuario, reserva.getUsuario());
	}

	@Test
	final void testSetusuario() {
	    Usuario usuario = new Usuario( new HashSet<>(), new HashSet<>(), "12345678A", "Juan", "Pérez", new Date(), 3, "usuario", "1234");

	    reserva.setUsuario(usuario);
	    assertEquals(usuario, reserva.getUsuario());
	}

	
	@Test
	final void testGetejemplar() {
	    Obra obra = new Obra(new HashSet<>(), "Novela", "La Odisea", 500, 1L, new Date());
	    Ejemplar ejemplar = new Ejemplar(11L, obra,new HashSet<>(), new HashSet<>(),"disponible");


	    reserva.setEjemplar(ejemplar);
	    assertEquals(ejemplar, reserva.getEjemplar());
	}

	@Test
	final void testSetejemplar() {
	
	    Obra obra = new Obra(new HashSet<>(), "Novela", "La Odisea", 500, 1L, new Date());
	    Ejemplar ejemplar = new Ejemplar(11L, obra,new HashSet<>(), new HashSet<>(),"disponible");

	    reserva.setEjemplar(ejemplar);
	    assertEquals(ejemplar, reserva.getEjemplar());
	}

	
	@Test
	final void testGetfecha() {
	    Date date = new Date();
	    reserva.setFecha(date);
	    assertEquals(date, reserva.getFecha());
	}

	@Test
	final void testSetfecha() {
	    Date date = new Date();
	    reserva.setFecha(date);
	    assertEquals(date, reserva.getFecha());
	}
}
