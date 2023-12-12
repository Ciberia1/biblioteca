package com.dominio.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PubSeriadasTest {
	
	private PubSeriadas pubSeriadas;

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
		pubSeriadas = new PubSeriadas("987-4561-1234-765", "Luis" ,"Articulo", "Semanal");
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	public final void testPubSeriadas() throws Exception {
		PubSeriadas pubSeriadas1 = new PubSeriadas("987-4561-1234-765", "Luis" ,"Articulo", "Semanal");
		assertTrue(pubSeriadas1.getIssn().equals(pubSeriadas.getIssn()) && pubSeriadas1.getEditor().equals(pubSeriadas.getEditor()) && pubSeriadas1.getTipo().equals(pubSeriadas.getTipo())&& pubSeriadas1.getPeriodicidad().equals(pubSeriadas.getPeriodicidad()));
	}
	
	@Test
	public final void testGetissn() {
		assertEquals("987-4561-1234-765", pubSeriadas.getIssn());
	}
	
	@Test
	public final void testSetissn() {
		String issn = "987-4561-1234-765";
		pubSeriadas.setIssn(issn);
		assertEquals(issn, pubSeriadas.getIssn());
	}
	
	@Test
	public final void testGeteditor() {
		assertEquals("Luis", pubSeriadas.getEditor());
	}
	
	@Test
	public final void testSeteditor() {
		String editor = "Luis";
		pubSeriadas.setEditor(editor);
		assertEquals(editor, pubSeriadas.getEditor());
	}
	
	@Test
	public final void testGettipo() {
		assertEquals("Articulo", pubSeriadas.getTipo());
	}
	
	@Test
	public final void testSettipo() {
		String tipo = "Articulo";
		pubSeriadas.setTipo(tipo);
		assertEquals(tipo, pubSeriadas.getTipo());
	}
	
	@Test
	public final void testGetperiodicidad() {
		assertEquals("Semanal", pubSeriadas.getPeriodicidad());
	}
	
	@Test
	public final void testSetperiodicidad() {
		String periodicidad = "Semanal";
		pubSeriadas.setPeriodicidad(periodicidad);
		assertEquals(periodicidad, pubSeriadas.getPeriodicidad());
	}
}
