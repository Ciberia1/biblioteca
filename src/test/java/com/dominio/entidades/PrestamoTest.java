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
class PrestamoTest {
	
	private Prestamo prestamo;
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
		prestamo = new Prestamo(20L, usuario ,ejemplar, new Date(), new Date(),true);
		prestamo.setPrestamoID((long) 20);
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	public final void testReserva() throws Exception {
		Prestamo prestamo1 = new Prestamo(20L, usuario ,ejemplar, new Date(), new Date(),true);
	}
	
	@Test
	public final void testGetprestamoID() {
		assertNotNull(prestamo.getPrestamoID());
	}
	
	@Test
	public final void testSetprestamoID() {
		long id = (long) 20;
		prestamo.setPrestamoID(id);
		assertEquals(id, prestamo.getPrestamoID());
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
	public final void testGetfechaInicioPres() {
		
	}
	
	@Test
	public final void testSetfechaInicioPres() {
		
	}
	
	@Test
	public final void testGetfechaFinPres() {
		
	}
	
	@Test
	public final void testSetfechaFinPres() {
		
	}
	
	@Test
	public final void testGetactivo() {
		boolean activo = prestamo.getActivo();
		assertTrue(activo);
	}
	
	@Test
	public final void testSetactivo() {
		prestamo.setActivo(true);
		assertEquals(true, prestamo.getActivo());
	}

}
