package com.dominio.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominio.entidades.Ejemplar;
import com.dominio.entidades.Libro;
import com.dominio.entidades.Obra;
import com.dominio.entidades.ObraWrapper;
import com.dominio.entidades.PubSeriadas;
import com.persistencia.EjemplarDAO;
import com.persistencia.LibroDAO;
import com.persistencia.ObraDAO;
import com.persistencia.PubSeriadasDAO;


class GestorTitulosTest {
	@InjectMocks
	private GestorTitulos gestorTitulos;

	@Mock
	private EjemplarDAO ejemplarDAO;

	@Mock
	private ObraDAO obraDAO;
	@Mock
	private LibroDAO libroDAO;

	@Mock
	private PubSeriadasDAO pubSeriadasDAO;

	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	protected void tearDown() throws Exception {
	}

	@Test
	public void testAltaObraLibro() throws InterruptedException {
	    Libro libro = new Libro( new HashSet<>(), "Editorial Prueba", "Edición Prueba", "ISBN Prueba", "Encuadernacion Prueba");
	    libro.setId(1L);

	    Collection<Ejemplar> ejemplares = new HashSet<>();
	    String genero = "Genero Prueba";
	    String titulo = "Titulo Prueba";
	    int nroPaginas = 100;
	    Long id = 1L;
	    Date fechaPublicacion = new Date();
	    Obra obra = new Obra(ejemplares, genero, titulo, nroPaginas, id, fechaPublicacion);

	    when(libroDAO.save(any(Libro.class))).thenAnswer(invocation -> {
	        Libro savedLibro = invocation.getArgument(0);
	        savedLibro.setId(1L);
	        return savedLibro;
	    });
	    when(libroDAO.findById(any(Long.class))).thenAnswer(invocation -> Optional.of(libro));

	    Ejemplar ejemplar = new Ejemplar();
	    ejemplar.setEstado("Disponible");
	    when(ejemplarDAO.save(any(Ejemplar.class))).thenReturn(ejemplar);

	    String nroEjemplares = "1";
	    String resultado = gestorTitulos.altaObra(obra, libro, null, "Libro", nroEjemplares);

	    assertEquals("redirect:/gestion", resultado);
	    verify(libroDAO, times(1)).save(any(Libro.class));
	    verify(ejemplarDAO, times(Integer.parseInt(nroEjemplares))).save(any(Ejemplar.class));
	}

	
	@Test
	public void testAltaObraPubSeriada() throws InterruptedException {

	    PubSeriadas pubseriada = new PubSeriadas("ISBN Prueba", "Vocento", "Periódico", "Diario");
	    pubseriada.setId(1L);

	    Collection<Ejemplar> ejemplares = new HashSet<>();
	    String genero = "Genero Prueba";
	    String titulo = "Titulo Prueba";
	    int nroPaginas = 100;
	    Long id = 1L;
	    Date fechaPublicacion = new Date();
	    Obra obra = new Obra(ejemplares, genero, titulo, nroPaginas, id, fechaPublicacion);

	    
	    when(pubSeriadasDAO.save(any(PubSeriadas.class))).thenAnswer(invocation -> {
	        PubSeriadas savedPubSeriada = invocation.getArgument(0);
	        savedPubSeriada.setId(1L);
	        return savedPubSeriada;
	    });
	    when(pubSeriadasDAO.findById(any(Long.class))).thenAnswer(invocation -> Optional.of(pubseriada));

	    Ejemplar ejemplar = new Ejemplar();
	    ejemplar.setEstado("Disponible");
	    when(ejemplarDAO.save(any(Ejemplar.class))).thenReturn(ejemplar);

	    String nroEjemplares = "1";
	    String resultado = gestorTitulos.altaObra(obra, null, pubseriada, "Publicación seriada", nroEjemplares);

	    assertEquals("redirect:/gestion", resultado);
	    verify(pubSeriadasDAO, times(1)).save(any(PubSeriadas.class));
	    verify(ejemplarDAO, times(Integer.parseInt(nroEjemplares))).save(any(Ejemplar.class));
	}


	@Test
	public void testActualizarObras_Libros() {
	    List<ObraWrapper> obrasWrapper = new ArrayList<>();
	    ObraWrapper obraWrapper = new ObraWrapper();
	    obraWrapper.setEsLibro(true);
	    obraWrapper.setId(1L);
	    obraWrapper.setEdicion("1º Edicion");
	    obraWrapper.setGenero("Ficción");
	    obraWrapper.setIsbn("434-323-43-242343");
	    obraWrapper.setEncuadernacion("Tapa blanda");
	    obraWrapper.setEditorial("Planeta");
	    obraWrapper.setNroPaginas(33);
	    obrasWrapper.add(obraWrapper);

	    when(libroDAO.findById(1L)).thenReturn(Optional.of(new Libro()));

	    String result = gestorTitulos.actualizarObras(obrasWrapper);
	    verify(libroDAO, times(1)).save(any(Libro.class));

	    assertEquals("redirect:/gestion", result);
	}

	@Test
	public void testActualizarObras_PubSeriadas() {
	    List<ObraWrapper> obrasWrapper = new ArrayList<>();
	    ObraWrapper obraWrapper = new ObraWrapper();
	    obraWrapper.setEsLibro(false);
	    obraWrapper.setNroPaginas(33);
	    obraWrapper.setId(1L);
	    obraWrapper.setGenero("Ficción");
	    obraWrapper.setIssn("434-323-43-242343");
	    obraWrapper.setEditor("Vocento");
	    obraWrapper.setPeriodicidad("Trimestral");
	    obraWrapper.setTipo("Periódico");
	    obrasWrapper.add(obraWrapper);
	    

	    when(pubSeriadasDAO.findById(1L)).thenReturn(Optional.of(new PubSeriadas()));

	    String result = gestorTitulos.actualizarObras(obrasWrapper);
	    verify(pubSeriadasDAO, times(1)).save(any(PubSeriadas.class));

	    assertEquals("redirect:/gestion", result);
	}


	@Test
	public void testBorrarObras() {
		Map<String, List<Long>> data = new HashMap<>();
		data.put("id", Arrays.asList(1L, 2L));

		String result = gestorTitulos.borrarObras(data.get("id"));

		verify(obraDAO, times(2)).deleteById(anyLong());
		assertEquals("redirect:/gestion", result);
	}

}
