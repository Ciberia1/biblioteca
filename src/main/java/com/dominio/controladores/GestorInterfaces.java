package com.dominio.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dominio.entidades.*;
import com.persistencia.AutorDAO;
import com.persistencia.EjemplarDAO;
import com.persistencia.LibroDAO;
import com.persistencia.ObraDAO;
import com.persistencia.UsuarioDAO;

@Controller
public class GestorInterfaces {

	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private ObraDAO obraDAO;
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private AutorDAO autorDAO;
	@Autowired
	private EjemplarDAO ejemplarDAO;

	@GetMapping("/login")
	public String formularioLogin(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "login";
	}

	@PostMapping("/login")
	public String enviarFormularioLogin(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {

		Usuario usuarioExistente = usuarioDAO.findByDni(usuario.getDni());
		if (usuarioExistente == null || !usuarioExistente.getContrasena().equals(usuario.getContrasena())) {
			redirectAttributes.addAttribute("error", "invalid");
			return "redirect:/login";
		}

		// Código para manejar la información de inicio de sesión enviada...
		return "redirect:/inicio"; // Redirige a inicio.html si los datos de inicio de sesión son correctos
	}

	@GetMapping("/inicio")
	public String menuInicio(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "inicio";
	}

	@GetMapping("/publicarAutor")
	public String getAutores(Model model) {
		model.addAttribute("autor", new Autor());
		return "publicarAutor";
	}
	
	@GetMapping("/publicarObra")
	public String getObrasAutores(Model model) {
		List<Autor> autores = autorDAO.findAll();
		Obra obra = new Obra();
		model.addAttribute("obra", obra);
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		PubSeriadas pubseriada = new PubSeriadas();
		model.addAttribute("pubseriada", pubseriada);
		model.addAttribute("autores", autores);
		return "publicarObra";
	}

	
	@GetMapping("/editarAutorObra")
	public String getRelacionObrasAutores(Model model) {
		List<Autor> autores = autorDAO.findAll();
		List<Libro> libros = libroDAO.findAll();
		model.addAttribute("libros", libros);
		model.addAttribute("autores", autores);
		return "editarAutorObra";
	}
	
	@GetMapping("/gestion")
	public String getObras(Model model) {
		List<Obra> obras = obraDAO.findAll();
		model.addAttribute("obras", obras);
		return "gestion"; // Nombre del archivo HTML "gestion.html"
	}
	
	@GetMapping("/gestionAutor")
	public String getAutor(Model model) {
		List<Autor> autores = autorDAO.findAll();
		model.addAttribute("autores", autores);
		return "gestionAutor";
	}
	@Autowired
	public void EjemplarController(EjemplarDAO ejemplarDAO) {
		this.ejemplarDAO = ejemplarDAO;
	}

	@GetMapping("/gestionEjemplar")
	public String getEjemplar(Model model) {
		List<Ejemplar> ejemplar = ejemplarDAO.findAll();
		model.addAttribute("ejemplar", ejemplar);
		return "gestionEjemplar"; // Nombre del archivo HTML "gestion.html"
	}
	@GetMapping("/publicarEjemplar")
	public String getEjemplares(Model model) {
		Ejemplar ejemplar = new Ejemplar();
		model.addAttribute("ejemplar", ejemplar);
		
		List<Obra> obras = obraDAO.findAll();
		model.addAttribute("obras", obras);
		return "publicarEjemplar";
	}


}
