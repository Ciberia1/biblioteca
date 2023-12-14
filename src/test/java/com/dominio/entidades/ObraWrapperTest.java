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

class ObraWrapperTest {
	private ObraWrapper obraWrapper;

	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() throws Exception {
		obraWrapper = new ObraWrapper("Misterio", "978-7562-5433-432" ,"Tapa blanda", "234-5678-9876-543","Luis","Articulo","Semanal","La colmena", new Date(), 64, "Santillana", "Tercera", 13L,true);
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}
	
	@Test
	final void testObraWrapper() throws Exception {
		ObraWrapper obraWrapper1 = new ObraWrapper("Misterio", "978-7562-5433-432" ,"Tapa blanda", "234-5678-9876-543","Luis","Articulo","Semanal","La colmena", new Date(), 64, "Santillana", "Tercera", 13L,true);
		assertTrue(obraWrapper1.getIssn().equals(obraWrapper.getIssn()) && obraWrapper1.getEditor().equals(obraWrapper.getEditor()) && obraWrapper1.getTipo().equals(obraWrapper.getTipo())&& obraWrapper1.getPeriodicidad().equals(obraWrapper.getPeriodicidad()) && obraWrapper1.getTitulo().equals(obraWrapper.getTitulo()) && obraWrapper1.getEditorial().equals(obraWrapper.getEditorial()) && obraWrapper1.getEdicion().equals(obraWrapper.getEdicion()));
	}
	
	@Test
	final void testGetgenero() {
		assertEquals("Misterio", obraWrapper.getGenero());
	}
	
	@Test
	final void testSetgenero() {
		String genero = "Misterio";
		obraWrapper.setGenero(genero);
		assertEquals(genero, obraWrapper.getGenero());
	}
	
	@Test
	final void testGetisbn() {
		assertEquals("978-7562-5433-432", obraWrapper.getIsbn());
	}
	
	@Test
	final void testSetisbn() {
		String isbn = "978-7562-5433-432";
		obraWrapper.setIsbn(isbn);
		assertEquals(isbn, obraWrapper.getIsbn());
	}
	
	@Test
	final void testGetencuadernacion() {
		assertEquals("Tapa blanda", obraWrapper.getEncuadernacion());
	}
	
	@Test
	final void testSetencuadernacion() {
		String encuadernacion = "Tapa blanda";
		obraWrapper.setEncuadernacion(encuadernacion);
		assertEquals(encuadernacion, obraWrapper.getEncuadernacion());
	}
	
	@Test
	final void testGetissn() {
		assertEquals("234-5678-9876-543", obraWrapper.getIssn());
	}
	
	@Test
	final void testSetissn() {
		String issn = "234-5678-9876-543";
		obraWrapper.setIssn(issn);
		assertEquals(issn, obraWrapper.getIssn());
	}
	
	@Test
	final void testGeteditor() {
		assertEquals("Luis", obraWrapper.getEditor());
	}
	
	@Test
	final void testSeteditor() {
		String editor = "Luis";
		obraWrapper.setEditor(editor);
		assertEquals(editor, obraWrapper.getEditor());
	}
	
	@Test
	final void testGettipo() {
		assertEquals("Articulo", obraWrapper.getTipo());
	}
	
	@Test
	final void testSettipo() {
		String tipo = "Articulo";
		obraWrapper.setTipo(tipo);
		assertEquals(tipo, obraWrapper.getTipo());
	}
	
	@Test
	final void testGetperiodicidad() {
		assertEquals("Semanal", obraWrapper.getPeriodicidad());
	}
	
	@Test
	final void testSetperiodicidad() {
		String periodicidad = "Semanal";
		obraWrapper.setPeriodicidad(periodicidad);
		assertEquals(periodicidad, obraWrapper.getPeriodicidad());
	}
	
	@Test
	final void testGettitulo() {
		assertEquals("La colmena", obraWrapper.getTitulo());
	}
	
	@Test
	final void testSettitulo() {
	    String titulo = "La colmena";
	    obraWrapper.setTitulo(titulo);
	    assertEquals(titulo, obraWrapper.getTitulo());
	}

	
	@Test
	final void testGetfechaPublicacion() {
	    assertNotNull(obraWrapper.getFechaPublicacion());
	}
	
	
	@Test
	final void testSetfechaPublicacion() {
		Date fecha = new Date();
		obraWrapper.setFechaPublicacion(fecha);
		assertEquals(fecha, obraWrapper.getFechaPublicacion());
	}
	
	@Test
	final void testGetnroPaginas() {
		assertEquals(64, (obraWrapper.getNroPaginas()));
	}
	
	@Test
	final void testSetnroPaginas() {
		int numPag = 64;
		obraWrapper.setNroPaginas(numPag);
		assertEquals(numPag, obraWrapper.getNroPaginas());
	}
	
	@Test
	final void testGeteditorial() {
		assertEquals("Santillana", obraWrapper.getEditorial());
	}
	
	@Test
	final void testSeteditorial() {
		String editorial = "Santillana";
		obraWrapper.setEditorial(editorial);
		assertEquals(editorial, obraWrapper.getEditorial());
	}
	
	@Test
	final void testGetedicion() {
		assertEquals("Tercera", obraWrapper.getEdicion());
	}
	
	@Test
	public final void testSetedicion() {
		String edicion = "Tercera";
		obraWrapper.setEdicion(edicion);
		assertEquals(edicion, obraWrapper.getEdicion());
	}
	
	@Test
	final void testGetid() {
		assertNotNull(obraWrapper.getId());
	}
	
	@Test
	final void testSetid() {
		long id = (long) 13;
		obraWrapper.setId(id);
		assertEquals(id, obraWrapper.getId());
	}
		
	@Test
	final void testGetesLibro() {
		boolean esLibro = obraWrapper.isEsLibro();
		assertTrue(esLibro);
	}
	
	@Test
	final void testSetesLibro() {
		obraWrapper.setEsLibro(true);
		assertEquals(true, obraWrapper.isEsLibro());
	}

}
