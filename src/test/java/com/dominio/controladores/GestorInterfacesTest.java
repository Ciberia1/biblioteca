package com.dominio.controladores;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dominio.entidades.Autor;
import com.dominio.entidades.Ejemplar;
import com.dominio.entidades.Libro;
import com.dominio.entidades.Obra;
import com.dominio.entidades.Usuario;
import com.persistencia.AutorDAO;
import com.persistencia.EjemplarDAO;
import com.persistencia.LibroDAO;
import com.persistencia.ObraDAO;
import com.persistencia.UsuarioDAO;

	class GestorInterfacesTest {

	    @InjectMocks
	    private GestorInterfaces gestorInterfaces;

	    @Mock
	    private UsuarioDAO usuarioDAO;

	    @Mock
	    private ObraDAO obraDAO;

	    @Mock
	    private LibroDAO libroDAO;

	    @Mock
	    private AutorDAO autorDAO;

	    @Mock
	    private EjemplarDAO ejemplarDAO;

	    @BeforeEach
	    protected void setUp() throws Exception {
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
	public void testFormularioLogin() {
	    Model model = mock(Model.class);
	    when(model.addAttribute(anyString(), any())).thenReturn(model);

	    String result = gestorInterfaces.formularioLogin(model);

	    verify(model, times(1)).addAttribute(anyString(), any());
	    assertEquals("login", result);
	}

	@Test
	public void testEnviarFormularioLogin_UsuarioInexistente() {
	    Usuario usuario = new Usuario();
	    usuario.setDni("1234");
	    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	    when(usuarioDAO.findByDni(anyString())).thenReturn(null);

	    String result = gestorInterfaces.enviarFormularioLogin(usuario, redirectAttributes);

	    verify(redirectAttributes, times(1)).addAttribute("error", "invalid");
	    assertEquals("redirect:/login", result);
	}

	@Test
	public void testEnviarFormularioLogin_ContrasenaIncorrecta() {
	    Usuario usuario = new Usuario();
	    usuario.setDni("1234");
	    usuario.setContrasena("contrasenaIncorrecta");

	    Usuario usuarioExistente = new Usuario();
	    usuarioExistente.setContrasena("contrasenaCorrecta");

	    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	    when(usuarioDAO.findByDni(anyString())).thenReturn(usuarioExistente);

	    String result = gestorInterfaces.enviarFormularioLogin(usuario, redirectAttributes);

	    verify(redirectAttributes, times(1)).addAttribute("error", "invalid");
	    assertEquals("redirect:/login", result);
	}

	@Test
	public void testEnviarFormularioLogin_Correcto() {
	    Usuario usuario = new Usuario();
	    usuario.setDni("1234");
	    usuario.setContrasena("contrasenaCorrecta");

	    Usuario usuarioExistente = new Usuario();
	    usuarioExistente.setContrasena("contrasenaCorrecta");
	    usuarioExistente.setRol("Administrador");

	    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	    when(usuarioDAO.findByDni(anyString())).thenReturn(usuarioExistente);

	    String result = gestorInterfaces.enviarFormularioLogin(usuario, redirectAttributes);

	    assertEquals("redirect:/inicio", result);
	}

	@Test
	public void testEnviarFormularioLogin_CorrectoCliente() {
	    Usuario usuario = new Usuario();
	    usuario.setDni("1234");
	    usuario.setContrasena("contrasenaCorrecta");

	    Usuario usuarioExistente = new Usuario();
	    usuarioExistente.setContrasena("contrasenaCorrecta");
	    usuarioExistente.setRol("Cliente");

	    RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	    when(usuarioDAO.findByDni(anyString())).thenReturn(usuarioExistente);

	    String result = gestorInterfaces.enviarFormularioLogin(usuario, redirectAttributes);

	    assertEquals("redirect:/inicioCliente", result);
	}

	@Test
	public void testMenuInicio() {
	    Model model = mock(Model.class);
	    String result = gestorInterfaces.menuInicio(model);
	    assertEquals("inicio", result);
	}

	@Test
	public void testMenuInicioCliente() {
	    Model model = mock(Model.class);
	    String result = gestorInterfaces.menuInicioCliente(model);
	    assertEquals("inicioCliente", result);
	}

	@Test
	public void testGetAutores() {
	    Model model = mock(Model.class);
	    when(model.addAttribute(anyString(), any(Autor.class))).thenReturn(model);
	    String result = gestorInterfaces.getAutores(model);
	    verify(model, times(1)).addAttribute(anyString(), any(Autor.class));
	    assertEquals("publicarAutor", result);
	}

	@Test
	public void testGetObrasAutores() {
	    Model model = mock(Model.class);
	    when(autorDAO.findAll()).thenReturn(new ArrayList<Autor>());
	    String result = gestorInterfaces.getObrasAutores(model);
	    verify(autorDAO, times(1)).findAll();
	    assertEquals("publicarObra", result);
	}

	@Test
	public void testGetRelacionObrasAutores() {
	    Model model = mock(Model.class);
	    when(autorDAO.findAll()).thenReturn(new ArrayList<Autor>());
	    when(libroDAO.findAll()).thenReturn(new ArrayList<Libro>());
	    String result = gestorInterfaces.getRelacionObrasAutores(model);
	    verify(autorDAO, times(1)).findAll();
	    verify(libroDAO, times(1)).findAll();
	    assertEquals("editarAutorObra", result);
	}
	
	@Test
	public void testGetObras() {
	    Model model = mock(Model.class);
	    when(obraDAO.findAll()).thenReturn(new ArrayList<Obra>());

	    String result = gestorInterfaces.getObras(model);

	    verify(obraDAO, times(1)).findAll();
	    assertEquals("gestion", result);
	}

	@Test
	public void testGetAutor() {
	    Model model = mock(Model.class);
	    when(autorDAO.findAll()).thenReturn(new ArrayList<Autor>());

	    String result = gestorInterfaces.getAutor(model);

	    verify(autorDAO, times(1)).findAll();
	    assertEquals("gestionAutor", result);
	}

	@Test
	public void testGetEjemplar() {
	    Model model = mock(Model.class);
	    when(ejemplarDAO.findAll()).thenReturn(new ArrayList<Ejemplar>());

	    String result = gestorInterfaces.getEjemplar(model);

	    verify(ejemplarDAO, times(1)).findAll();
	    assertEquals("gestionEjemplar", result);
	}

	@Test
	public void testGetEjemplaresObras() {
	    Model model = mock(Model.class);
	    when(obraDAO.findAll()).thenReturn(new ArrayList<Obra>());

	    String result = gestorInterfaces.getEjemplaresObras(model);

	    verify(obraDAO, times(1)).findAll();
	    assertEquals("publicarEjemplar", result);
	}

	@Test
	public void testGetOperacionesEjemplar() {
	    Model model = mock(Model.class);
	    when(ejemplarDAO.findAll()).thenReturn(new ArrayList<Ejemplar>());

	    String result = gestorInterfaces.getOperacionesEjemplar(model);

	    verify(ejemplarDAO, times(1)).findAll();
	    assertEquals("operacionEjemplar", result);
	}

}
