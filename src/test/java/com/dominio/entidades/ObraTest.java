package com.dominio.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObraTest {
	
	private Obra obra;
	private static Collection<Ejemplar> ejemplares = new HashSet<>();
	private static Collection<Ejemplar> Maxejemplares = new HashSet<>();

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
		obra = new Obra(ejemplares, "Realista" ,"La colmena", 543 ,13L,new Date());
		obra.setId((long) 13);
		obra.setNroPaginas(453);
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	public final void testObra() throws Exception {
		Obra obra1 = new Obra(ejemplares, "Realista" ,"La colmena", 453 ,13L,new Date());
		assertTrue(obra1.getGenero().equals(obra.getGenero())
				&& obra1.getTitulo().equals(obra.getTitulo()));
	}
	
	
	@Test
	public final void testGetejemplares() {
		Collection<Ejemplar> reserva = obra.getEjemplares();
		assertEquals(ejemplares, reserva);
	}
	
	
	@Test
	public final void testSetejemplares() {
		Maxejemplares.add(new Ejemplar());
		obra.setEjemplares(Maxejemplares);
		assertEquals(Maxejemplares, obra.getEjemplares());
	}
	
	@Test
	public final void testGetgenero() {
		assertEquals("Realista", obra.getGenero());
	}
	
	@Test
	public final void testSetgenero() {
		String genero = "Realista";
		obra.setGenero(genero);
		assertEquals(genero, obra.getGenero());
	}
	
	@Test
	public final void testGettitulo() {
		assertEquals("La colmena", obra.getTitulo());
	}
	
	@Test
	public final void testSettitulo() {
		String titulo = "La colmena";
		obra.setGenero(titulo);
		assertEquals(titulo, obra.getTitulo());
	}
	
	@Test
	public final void testGetnroPaginas() {
		assertEquals(453, (obra.getNroPaginas()));
	}
	
	@Test
	public final void testSetnroPaginas() {
		int numPag = 453;
		obra.setNroPaginas(numPag);
		assertEquals(numPag, obra.getNroPaginas());
	}
	
	@Test
	public final void testGetid() {
		assertNotNull(obra.getId());
	}
	
	@Test
	public final void testSetid() {
		long id = (long) 13;
		obra.setId(id);
		assertEquals(id, obra.getId());
	}

	@Test
	public final void testGetfechaPublicacion() {
		
	}
	
	@Test
	public final void testSetfechaPublicacion() {
		
	}

}
