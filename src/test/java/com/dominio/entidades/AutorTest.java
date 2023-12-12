package com.dominio.entidades;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
class AutorTest {
	
	private Autor autor;
	private static Set<Libro> libros = new HashSet<>();
	private static Set<Autor> autores = new HashSet<>();
	
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
		autor = new Autor(30L, "Ana", "García", "Su obra más famosa es la colmena", libros);
		libros.add(new Libro(autores, "Santillana", "Tercera", "978-7562-5433-432", "Tapa blanda"));
		
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	final void testAutor() throws Exception {
		Autor autor1 = new Autor(30L, "Ana", "García", "Su obra más famosa es la colmena", libros);
		assertTrue(autor1.getNombre().equals(autor.getNombre()) && autor1.getApellido().equals(autor.getApellido()) && autor1.getBiografia().equals(autor.getBiografia()));
	}
	
	@Test
	final void testGetid() throws Exception {
		assertNotNull(autor.getId());
	}
	
	@Test
	final void testSetid() throws Exception {
		long id = (long) 30;
		autor.setId(id);
		assertEquals(id, autor.getId());
	}
	
	@Test
	final void testGetnombre() throws Exception {
		assertEquals("Ana", autor.getNombre());
	}
	
	@Test
	final void testSetnombre() throws Exception {
		String nombre = "Ana";
		autor.setNombre(nombre);
		assertEquals(nombre, autor.getNombre());
	}
	
	@Test
	final void testGetapellido() throws Exception {
		assertEquals("García", autor.getApellido());
	}
	
	@Test
	final void testSetapellido() throws Exception {
		String apellido = "García";
		autor.setApellido(apellido);
		assertEquals(apellido, autor.getApellido());
	}
	
	@Test
	final void testGetBiografia() throws Exception {
		assertEquals("Su obra más famosa es la colmena", autor.getBiografia());
	}
	
	@Test
	final void testSetBiografia() throws Exception {
		String biografia = "Su obra más famosa es la colmena";
		autor.setBiografia(biografia);
		assertEquals(biografia, autor.getBiografia());
	}
	
	@Test
	final void testGetlibros() throws Exception {
	    Set<Libro> libro = autor.getLibros();
	    assertEquals(libros, libro);
	}

	
	@Test
	final void testSetlibros() throws Exception {
		autores.add(new Autor(30L, "Ana", "García", "Su obra más famosa es la colmena", libros));
		libros.add(new Libro(autores, "Santillana", "Tercera", "978-7562-5433-432", "Tapa blanda"));
		autor.setLibros(libros);
		assertEquals(libros, autor.getLibros());
	}
}
