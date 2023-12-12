package com.dominio.entidades;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EjemplarTest {
	
	private Ejemplar ejemplar;
	private Obra obra;
	private Usuario usuario;
	private static Collection<Reserva> reservas = new HashSet<>();
	private static Collection<Reserva> reservado = new HashSet<>();
	private static Collection<Prestamo> prestamos = new HashSet<>();
	private static Collection<Prestamo> prestado = new HashSet<>();
	private static Collection<Ejemplar> ejemplares = new HashSet<>();

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
		ejemplar = new Ejemplar(23L, obra ,reservado, prestado ,"Disponible");
		ejemplar.setEjemplarID((long) 23);
		
	}

	@AfterEach
	protected void tearDown() throws Exception {
		System.out.println("@After");
	}
	
	@Test
	public final void testEjemplar() throws Exception {
		Ejemplar ejemplar1 = new Ejemplar(23L, obra ,reservado, prestado ,"Disponible");
		assertTrue(ejemplar1.getEstado().equals(ejemplar.getEstado()));
	}
	
	@Test
	public final void testGetejemplarId() throws Exception {
		assertNotNull(ejemplar.getEjemplarID());
	}
	
	@Test
	public final void testSetejemplarId() throws Exception {
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
	public final void testSetobra() throws Exception {
//		Ejemplar ejemplar = new Ejemplar(23L, obra ,reservado, prestado ,"Disponible");
//		Obra obra = new Obra(ejemplares, "Realista", "La colmena", 324, 30L, new Date());
//		ejemplar.setObra(obra);
//		Obra obraSelecionada = ejemplar.getObra();
//		assertEquals(obra, ejemplar.getObra());
	}
	
	@Test
	public final void testGetreservas() throws Exception {
		Collection<Reserva> reservas = ejemplar.getReservas();
		assertEquals(reservas, reservas);
	}
	
	@Test
	public final void testSetreservas() throws Exception {
		reservado.add(new Reserva(23L, usuario ,ejemplar, new Date()));
		ejemplar.setReservas(reservado);
		assertEquals(reservado, ejemplar.getReservas());
	}
	
	@Test
	public final void testGetprestamos() throws Exception {
		Collection<Prestamo> prestado = ejemplar.getPrestamos();
		assertEquals(prestamos, prestado);
	}
	
	@Test
	public final void testSetprestamos() throws Exception {
		prestado.add(new Prestamo());
		ejemplar.setPrestamos(prestado);
		assertEquals(prestado, ejemplar.getPrestamos());
	}
	
	@Test
	public final void testGetestado() throws Exception {
		assertEquals("Disponible", ejemplar.getEstado());
	}
	
	@Test
	public final void testSetestado() throws Exception {
		String estados = "Disponible";
		ejemplar.setEstado(estados);
		assertEquals(estados, ejemplar.getEstado());
	}
}
