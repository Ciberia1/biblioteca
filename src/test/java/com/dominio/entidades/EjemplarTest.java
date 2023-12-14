package com.dominio.entidades;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EjemplarTest {
	
	private Ejemplar ejemplar;
	private Obra obra;
	private Usuario usuario;
	private static Collection<Reserva> reservado = new HashSet<>();
	private static Collection<Prestamo> prestado = new HashSet<>();

	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
	}
	
	

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() throws Exception {

	    // Inicializar una colección de ejemplares vacía
	    Collection<Ejemplar> ejemplares = new HashSet<>();

	    // Inicializar la obra con los parámetros
	    obra = new Obra(ejemplares, "Genero de prueba", "Titulo de prueba", 100, 1L, new Date());

	    ejemplar = new Ejemplar(23L, obra ,reservado, prestado ,"Disponible");
	    ejemplar.setEjemplarID((long) 23);
	}


	@AfterEach
	protected void tearDown() throws Exception {
	}
	
	@Test
	final void testEjemplar() throws Exception {
		Ejemplar ejemplar1 = new Ejemplar(23L, obra ,reservado, prestado ,"Disponible");
		assertTrue(ejemplar1.getEstado().equals(ejemplar.getEstado()));
	}
	
	@Test
	final void testGetejemplarId() throws Exception {
		assertNotNull(ejemplar.getEjemplarID());
	}
	
	@Test
	final void testSetejemplarId() throws Exception {
		long id = (long) 23;
		ejemplar.setEjemplarID(id);
		assertEquals(id, ejemplar.getEjemplarID());
	}
	
	@Test
	public final void testGetobra() throws Exception {
		Obra obraSelecionada = ejemplar.getObra();
		assertEquals(obra,obraSelecionada);
	}
	
	@Test
	final void testSetobra() throws Exception {
	    // Inicializar una colección de ejemplares vacía
	    Collection<Ejemplar> ejemplares = new HashSet<>();

   	    // Inicializar la nueva obra con los parámetros
	    Obra nuevaObra = new Obra(ejemplares, "Genero de prueba", "Titulo de prueba", 100, 1L, new Date());

	    ejemplar.setObra(nuevaObra);
	    Obra obraSeleccionada = ejemplar.getObra();
	    assertEquals(nuevaObra, obraSeleccionada);
	}


	
	@Test
	final void testGetreservas() throws Exception {
	    Collection<Reserva> reservas = ejemplar.getReservas();
	    assertEquals(reservado, reservas);
	}

	@Test
	final void testSetreservas() throws Exception {
		reservado.add(new Reserva(23L, usuario ,ejemplar, new Date()));
		ejemplar.setReservas(reservado);
		assertEquals(reservado, ejemplar.getReservas());
	}
	

	@Test
	final void testGetprestamos() throws Exception {
	    Collection<Prestamo> prestadoObtenido = ejemplar.getPrestamos();
	    assertEquals(prestado, prestadoObtenido);
	}

	
	@Test
	final void testSetprestamos() throws Exception {
		prestado.add(new Prestamo());
		ejemplar.setPrestamos(prestado);
		assertEquals(prestado, ejemplar.getPrestamos());
	}
	
	@Test
	final void testGetestado() throws Exception {
		assertEquals("Disponible", ejemplar.getEstado());
	}
	
	@Test
	final void testSetestado() throws Exception {
		String estados = "Disponible";
		ejemplar.setEstado(estados);
		assertEquals(estados, ejemplar.getEstado());
	}
}
