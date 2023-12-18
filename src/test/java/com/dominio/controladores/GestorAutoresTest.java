package com.dominio.controladores;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;





import com.dominio.entidades.Autor;
import com.dominio.entidades.Libro;
import com.persistencia.AutorDAO;
import com.persistencia.LibroDAO;

class GestorAutoresTest {

	    @InjectMocks
	    private GestorAutores gestorAutores;

	    @Mock
	    private AutorDAO autorDAO;

	    @Mock
	    private LibroDAO libroDAO;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @BeforeAll
		protected static void setUpBeforeClass() throws Exception {
		}

		@AfterAll
		protected static void tearDownAfterClass() throws Exception {
		}

		@AfterEach
		protected void tearDown() throws Exception {
		}
		
	    @Test
	    void testAltaAutor() {
	        Autor autor = new Autor(1L, "Nombre", "Apellido", "Biografia", null);
	        when(autorDAO.save(autor)).thenReturn(autor);
	        String resultado = gestorAutores.altaAutor(autor);
	        verify(autorDAO, times(1)).save(autor);
	        assertEquals("redirect:/gestionAutor", resultado);
	    }

	    @Test
	    void testEditarAutorObra() {
	        String libroId = "1";
	        List<String> autorIds = Arrays.asList("1", "2");
	        Autor autor1 = new Autor(1L, "Nombre1", "Apellido1", "Biografia1", null);
	        Autor autor2 = new Autor(2L, "Nombre2", "Apellido2", "Biografia2", null);
	        Libro libro = new Libro();

	        when(autorDAO.getById(Long.valueOf(autorIds.get(0)))).thenReturn(autor1);
	        when(autorDAO.getById(Long.valueOf(autorIds.get(1)))).thenReturn(autor2);
	        when(libroDAO.getById(Long.valueOf(libroId))).thenReturn(libro);
	        when(libroDAO.save(libro)).thenReturn(libro);

	        String resultado = gestorAutores.editarAutorObra(libroId, autorIds);

	        verify(libroDAO, times(1)).save(libro);
	        assertEquals("redirect:/gestionAutor", resultado);
	    }

	    @Test
	    void testActualizarAutores() {
	        List<Autor> autores = Arrays.asList(
	            new Autor(1L, "Nombre1", "Apellido1", "Biografia1", null),
	            new Autor(2L, "Nombre2", "Apellido2", "Biografia2", null)
	        );

	        when(autorDAO.save(any(Autor.class))).thenAnswer(i -> i.getArguments()[0]);

	        String resultado = gestorAutores.actualizarAutores(autores);

	        verify(autorDAO, times(autores.size())).save(any(Autor.class));
	        assertEquals("redirect:/gestionAutor", resultado);
	    }

	   @Test
	   void testBorrarAutores() throws InterruptedException {
	       List<Long> autoresIds = Arrays.asList(1L, 2L);
	       Set<Libro> libros = new HashSet<>();
	       Set<Autor> autores1 = new HashSet<>(); // Inicializa el conjunto de autores para el Autor 1
	       Autor autor1 = new Autor(1L, "Nombre1", "Apellido1", "Biografia1", libros);
	       Autor autor2 = new Autor(2L, "Nombre2", "Apellido2", "Biografia2", libros);
	       Libro libro = new Libro(autores1, "Editorial1", "Edicion1", "ISBN1", "Encuadernacion1");
	       libros.add(libro);

	       when(autorDAO.findById(autoresIds.get(0))).thenReturn(Optional.of(autor1));
	       when(autorDAO.findById(autoresIds.get(1))).thenReturn(Optional.of(autor2));

	       String resultado = gestorAutores.borrarAutores(autoresIds);

	       verify(autorDAO, times(autoresIds.size())).deleteById(any(Long.class));
	       assertEquals("redirect:/gestionAutor", resultado);
	   }


	    
	    @Test
	    void testGetLibrosDelAutor() {
	        Long id = 1L;
	        Set<Libro> libros = new HashSet<>();
	        libros.add(new Libro());
	        Autor autor = new Autor(id, "Nombre", "Apellido", "Biografia", libros);
	        when(autorDAO.getById(id)).thenReturn(autor);

	        Model model = new ConcurrentModel();

	        String resultado = gestorAutores.getLibrosDelAutor(id, model);
	        assertEquals(libros, model.getAttribute("libros"));
	        assertEquals("autorLibros", resultado);
	    }

	    

	   
	}
