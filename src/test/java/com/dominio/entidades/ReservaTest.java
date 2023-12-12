package com.dominio.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

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
	public final void testReserva() throws Exception {
		Reserva reserva1 = new Reserva(40L, usuario ,ejemplar, new Date());
	}
	
	@Test
	public final void testGetreservaId() {
		assertNotNull(reserva.getReservaID());
	}
	
	@Test
	public final void testSetreservaId() {
		long id = (long) 40;
		reserva.setReservaID(id);
		assertEquals(id, reserva.getReservaID());
	}
	
	@Test
	public final void testGetusuario() {
		
	}
	
	@Test
	public final void testSetusuario() {
		
	}
	
	@Test
	public final void testGetejemplar() {
		
	}
	
	@Test
	public final void testSetejemplar() {
		
	}
	
	@Test
	public final void testGetfecha() {
		
	}
	
	@Test
	public final void testSetfecha() {
		
	}
}
