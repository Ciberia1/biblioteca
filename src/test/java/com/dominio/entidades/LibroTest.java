package com.dominio.entidades;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibroTest {
	
	private Libro libro;
	private static Set<Autor> autores = new HashSet<>();
	private static Set<Autor> Maxautor = new HashSet<>();
	private static Set<Libro> libros = new HashSet<>();

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
		libro = new Libro(Maxautor,"Santillana", "Tercera", "978-7562-5433-432", "Tapa blanda");
		autores.add(new Autor(33L, "Ana", "Ramirez","Su obra más famosa es el rey", libros));
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	public final void testLibro() throws Exception {
		Libro libro1 = new Libro(Maxautor,"Santillana", "Tercera", "978-7562-5433-432", "Tapa blanda");
		assertTrue(libro1.getEditorial().equals(libro.getEditorial()) && libro1.getEdicion().equals(libro.getEdicion()) && libro1.getIsbn().equals(libro.getIsbn())&& libro1.getEncuadernacion().equals(libro.getEncuadernacion()));
	}
	
	@Test
	public final void testGetautores() throws Exception {
		Set<Autor> autores = libro.getAutores();
		assertEquals(autores, autores);
	}
	
	@Test
	public final void testSetautores() throws Exception {
		Maxautor.add(new Autor(30L, "Ana", "García","Su obra más famosa es la colmena", libros));
		libro.setAutores(Maxautor);
		assertEquals(Maxautor, libro.getAutores());
	}
	
	@Test
	public final void testGeteditorial() throws Exception {
		assertEquals("Santillana", libro.getEditorial());
	}
	
	@Test
	public final void testSeteditorial() throws Exception {
		String editorial = "RA-MA";
		libro.setEditorial(editorial);
		assertEquals(editorial, libro.getEditorial());
	}
	
	@Test
	public final void testGetedicion() throws Exception {
		assertEquals("Tercera", libro.getEdicion());
	}
	
	@Test
	public final void testSetedicion() throws Exception {
		String edicion = "Septima";
		libro.setEdicion(edicion);
		assertEquals(edicion, libro.getEdicion());
	}
	
	@Test
	public final void testGetisbn() throws Exception {
		assertEquals("978-7562-5433-432", libro.getIsbn());
	}
	
	@Test
	public final void testSetisbn() throws Exception {
		String isbn = "324-3443-2345-345";
		libro.setIsbn(isbn);
		assertEquals(isbn, libro.getIsbn());
	}
	
	@Test
	public final void testGetencuadernacion() throws Exception {
		assertEquals("Tapa blanda", libro.getEncuadernacion());
	}
	
	@Test
	public final void testSetencuadernacion() throws Exception {
		String encuadernacion = "Tapa dura";
		libro.setEncuadernacion(encuadernacion);
		assertEquals(encuadernacion, libro.getEncuadernacion());
	}
}
