/**
 * La clase "GestorInterfaces" es una clase de controlador Java que maneja varias solicitudes HTTP para proporcionar datos a las interfaces y
 * administra las interfaces para una aplicación web.
 */
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

/**
 * La función devuelve un formulario de inicio de sesión con un objeto de usuario vacío.
 * 
 * @param modelo El parámetro "modelo" es de tipo Model, que es una clase proporcionada por Spring
 * Framework. Se utiliza para pasar datos entre el controlador y la vista. En este caso, el objeto
 * "modelo" se utiliza para agregar al modelo un atributo llamado "usuario", que es una instancia
 * @return El método devuelve una cadena "iniciar sesión".
 */
	@GetMapping("/login")
	public String formularioLogin(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "login";
	}

/**
 * Esta función de Java maneja el envío del formulario de inicio de sesión, verifica si el usuario
 * existe y la contraseña es correcta y redirige en consecuencia.
 * 
 * @param usuario Un objeto de la clase Usuario, que representa un usuario con propiedades como dni
 * (número de identificación) y contrasena (contraseña).
 * @param redirectAttributes RedirectAttributes es una clase proporcionada por Spring MVC que le
 * permite agregar atributos a la URL de redireccionamiento. Luego, estos atributos están disponibles
 * como parámetros de consulta en la URL redirigida. En este caso, el atributo "error" se agrega a la
 * URL de redireccionamiento si el inicio de sesión no es válido, para que pueda ser
 * @return El método devuelve una cadena "redirect:/inicio". Esto significa que si las credenciales de
 * inicio de sesión son correctas, el usuario será redirigido a la página de "inicio".
 */
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

/**
 * La función "Menú Inicio" devuelve la cadena "Inicio" y agrega un nuevo objeto "Usuario" al modelo.
 * 
 * @param modelo El parámetro "modelo" es de tipo Model, que es una clase proporcionada por Spring
 * Framework. Se utiliza para pasar datos entre el controlador y la vista. En este caso, el objeto
 * "modelo" se utiliza para agregar al modelo un atributo llamado "usuario", que es una instancia
 * @return El método devuelve una cadena "inicio".
 */
	@GetMapping("/inicio")
	public String menuInicio(Model modelo) {
		modelo.addAttribute("usuario", new Usuario());
		return "inicio";
	}

/**
 * La función devuelve una cadena "publicarAutor" y agrega un nuevo objeto Autor al modelo.
 * 
 * @param model El parámetro del modelo es un objeto de la clase Modelo, que se utiliza para pasar
 * datos entre el controlador y la vista en Spring MVC. Le permite agregar atributos a los que se puede
 * acceder en la vista. En este caso, el atributo "autor" se agrega al modelo con una nueva instancia
 * de
 * @return El método devuelve un valor de cadena "publicarAutor".
 */
	@GetMapping("/publicarAutor")
	public String getAutores(Model model) {
		model.addAttribute("autor", new Autor());
		return "publicarAutor";
	}

/**
 * La función "getObrasAutores" devuelve una cadena y llena un modelo con datos para publicar un
 * trabajo, incluida una lista de autores.
 * 
 * @param model El objeto modelo se utiliza para pasar datos entre el controlador y la vista. En este
 * caso, se utiliza para agregar los siguientes atributos:
 * @return El método devuelve un valor de cadena "publicarObra".
 */
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

/**
 * La función "getRelacionObrasAutores" recupera una lista de autores y libros de la base de datos y
 * los agrega como atributos al modelo antes de devolver la vista "editarAutorObra".
 * 
 * @param model El objeto modelo se utiliza para pasar datos entre el controlador y la vista. En este
 * caso se utiliza para agregar al modelo los atributos "libros" y "autores", los cuales estarán
 * accesibles en la vista "editarAutorObra". Estos atributos se completan con las listas de
 * @return El método devuelve un valor de cadena "editarAutorObra".
 */
	@GetMapping("/editarAutorObra")
	public String getRelacionObrasAutores(Model model) {
		List<Autor> autores = autorDAO.findAll();
		List<Libro> libros = libroDAO.findAll();
		model.addAttribute("libros", libros);
		model.addAttribute("autores", autores);
		return "editarAutorObra";
	}

/**
 * Esta función de Java recupera una lista de objetos "Obra" de una base de datos y los agrega al
 * modelo antes de devolver el nombre de un archivo HTML.
 * 
 * @param model El modelo es un objeto que se utiliza para pasar datos entre el controlador y la vista.
 * En este caso, se agrega el atributo "obras" al modelo, que contiene una lista de objetos "Obra".
 * Esta lista luego se pasa a la vista "gestion.html", por lo que
 * @return El método devuelve un valor de cadena "gestión".
 */
	@GetMapping("/gestion")
	public String getObras(Model model) {
		List<Obra> obras = obraDAO.findAll();
		model.addAttribute("obras", obras);
		return "gestion"; // Nombre del archivo HTML "gestion.html"
	}

/**
 * Esta función de Java recupera una lista de autores de una base de datos y la agrega al modelo antes
 * de devolver el nombre de la vista que se va a representar.
 * 
 * @param model El modelo es un objeto que se utiliza para pasar datos entre el controlador y la vista.
 * En este caso, la lista de "autores" se agrega al modelo con el nombre de atributo "autores". Esta
 * lista será accesible en la vista "gestionAutor".
 * @return El método devuelve un valor de cadena "gestionAutor".
 */
	@GetMapping("/gestionAutor")
	public String getAutor(Model model) {
		List<Autor> autores = autorDAO.findAll();
		model.addAttribute("autores", autores);
		return "gestionAutor";
	}

/**
 * La función utiliza la anotación @Autowired para inyectar una instancia de la clase EjemplarDAO en la
 * clase EjemplarController.
 * 
 * @param ejemplarDAO El parámetro ejemplarDAO es una instancia de la clase EjemplarDAO. Se inyecta en
 * la clase EjemplarController utilizando la anotación @Autowired. Esto significa que la instancia
 * ejemplarDAO se crea automáticamente y se proporciona a la clase EjemplarController mediante el marco
 * Spring.
 */
	@Autowired
	public void EjemplarController(EjemplarDAO ejemplarDAO) {
		this.ejemplarDAO = ejemplarDAO;
	}

/**
 * Esta función de Java recupera una lista de objetos "Ejemplar" de una base de datos y los agrega al
 * modelo antes de devolver el nombre de un archivo HTML.
 * 
 * @param model El modelo es un objeto que se utiliza para pasar datos entre el controlador y la vista.
 * En este caso, el atributo "ejemplares" se agrega al modelo, que contiene una lista de objetos
 * Ejemplar recuperados del ejemplarDAO. Esta lista de objetos Ejemplar
 * @return El método devuelve una cadena "gestionEjemplar".
 */
	@GetMapping("/gestionEjemplar")
	public String getEjemplar(Model model) {
		List<Ejemplar> ejemplar = ejemplarDAO.findAll();
		model.addAttribute("ejemplares", ejemplar);
		return "gestionEjemplar"; // Nombre del archivo HTML "gestion.html"
	}

/**
 * La función "getExampleWorks" recupera una lista de trabajos y los agrega al modelo, luego devuelve
 * la vista "publishExample"
 * 
 * @param model El objeto modelo se utiliza para pasar datos entre el controlador y la vista. En este
 * caso se utiliza para agregar los atributos "ejemplar" y "obras" al modelo.
 * @return El método devuelve un valor de cadena "publicarEjemplar".
 */
	@GetMapping("/publicarEjemplar")
	public String getEjemplaresObras(Model model) {
		Ejemplar ejemplares = new Ejemplar();
		model.addAttribute("ejemplar", ejemplares);

		List<Obra> obras = obraDAO.findAll();
		model.addAttribute("obras", obras);
		return "publicarEjemplar";
	}

}
