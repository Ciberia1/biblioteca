package com.dominio.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	public void testAltaObra_Libro() throws InterruptedException {

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
