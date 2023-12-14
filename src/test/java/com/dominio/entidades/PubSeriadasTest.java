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
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() throws Exception {
		pubSeriadas = new PubSeriadas("987-4561-1234-765", "Luis" ,"Articulo", "Semanal");
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}
	
	@Test
	final void testPubSeriadas() throws Exception {
		PubSeriadas pubSeriadas1 = new PubSeriadas("987-4561-1234-765", "Luis" ,"Articulo", "Semanal");
		assertTrue(pubSeriadas1.getIssn().equals(pubSeriadas.getIssn()) && pubSeriadas1.getEditor().equals(pubSeriadas.getEditor()) && pubSeriadas1.getTipo().equals(pubSeriadas.getTipo())&& pubSeriadas1.getPeriodicidad().equals(pubSeriadas.getPeriodicidad()));
	}
	
	@Test
	final void testGetissn() {
		assertEquals("987-4561-1234-765", pubSeriadas.getIssn());
	}
	
	@Test
	final void testSetissn() {
		String issn = "987-4561-1234-765";
		pubSeriadas.setIssn(issn);
		assertEquals(issn, pubSeriadas.getIssn());
	}
	
	@Test
	final void testGeteditor() {
		assertEquals("Luis", pubSeriadas.getEditor());
	}
	
	@Test
	final void testSeteditor() {
		String editor = "Luis";
		pubSeriadas.setEditor(editor);
		assertEquals(editor, pubSeriadas.getEditor());
	}
	
	@Test
	final void testGettipo() {
		assertEquals("Articulo", pubSeriadas.getTipo());
	}
	
	@Test
	final void testSettipo() {
		String tipo = "Articulo";
		pubSeriadas.setTipo(tipo);
		assertEquals(tipo, pubSeriadas.getTipo());
	}
	
	@Test
	final void testGetperiodicidad() {
		assertEquals("Semanal", pubSeriadas.getPeriodicidad());
	}
	
	@Test
	final void testSetperiodicidad() {
		String periodicidad = "Semanal";
		pubSeriadas.setPeriodicidad(periodicidad);
		assertEquals(periodicidad, pubSeriadas.getPeriodicidad());
	}
}
