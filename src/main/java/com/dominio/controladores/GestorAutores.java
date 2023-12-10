/**
 * La clase "GestorAutores" es una clase de controlador en una aplicación Java Spring que maneja
 * operaciones relacionadas con autores y libros.
 */
package com.dominio.controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dominio.entidades.*;
import com.persistencia.*;

@Controller

public class GestorAutores {
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private AutorDAO autorDAO;
	
	private String gestionAutor = "redirect:/gestionAutor";

	/**
	 * La función "altaAutor" guarda un nuevo autor en la base de datos y redirige a
	 * la página "gestionAutor".
	 * 
	 * @param autor El parámetro "autor" es un objeto de la clase "Autor" que se
	 *              pasa como atributo del modelo en la solicitud.
	 * @return El método devuelve un valor de cadena "redirect:/gestionAutor".
	 */
	@PostMapping("/publicarAutor")
	public String altaAutor(@ModelAttribute Autor autor) {
		autorDAO.save(autor);
		return gestionAutor;
	}

	/**
	 * La función "editarAutorObra" en el código Java se utiliza para editar los
	 * autores de un libro recibiendo el ID del libro y una lista de ID de autores
	 * como parámetros.
	 * 
	 * @param libroId  El parámetro libroId es una Cadena que representa el ID de un
	 *                 libro que necesita ser editado.
	 * @param autorIds Una lista de ID de autor.
	 * @return El método devuelve una cadena "redirect:/gestionAutor".
	 */
	@PostMapping("/editarAutorObra")
	public String editarAutorObra(@RequestParam("libroId") String libroId,
			@RequestParam(value = "autorIds", required = false) List<String> autorIds) {
		Set<Autor> autoresnuevos = new HashSet<>();
		if (autorIds != null) {
			for (String autor : autorIds) {
				Autor temporal = autorDAO.getById(Long.valueOf(autor));
				autoresnuevos.add(temporal);
			}
		}
		Libro nuevo = libroDAO.getById(Long.valueOf(libroId));
		nuevo.setAutores(autoresnuevos);
		libroDAO.save(nuevo);

		return gestionAutor;
	}

	/**
	 * La función actualiza una lista de autores en una base de datos y redirige a
	 * una página de administración.
	 * 
	 * @param autores Una lista de objetos de Autor que se actualizarán en la base
	 *                de datos.
	 * @return El método devuelve una cadena "redirect:/gestionAutor".
	 */
	@PostMapping("/actualizarAutores")
	public String actualizarAutores(@RequestBody List<Autor> autores) {
		for (Autor autor : autores) {
			Autor temporal = new Autor(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getBiografia(),
					null);
			autorDAO.save(temporal);
		}
		return gestionAutor;
	}

	@GetMapping( "/author/{id}")
	public String getAuthorBooks(@PathVariable("id") Long id, Model model) {
		Autor autor = autorDAO.getById(id);
		Set<Libro> libros = autor.getLibros();

		// Agregar la lista de libros al modelo.
		model.addAttribute("libros", libros);

		// Devolver la vista que muestra la lista de libros.
		return "autorLibros";
	}

	/**
	 * Esta función elimina autores y elimina su asociación con libros.
	 * 
	 * @param autoresIds Una lista de valores largos que representan los ID de los
	 *                   autores que se eliminarán.
	 * @return El método devuelve una cadena "redirect:/gestionAutor".
	 */

	@PostMapping("/borrarAutores")
	public String borrarAutores(@RequestParam(name = "id", required = false) List<Long> autoresIds)
			throws InterruptedException {
		if (autoresIds != null) {
			for (Long autorId : autoresIds) {
				try {
					Autor autor = autorDAO.findById(autorId).orElse(null);
					if (autor != null) {
						Set<Libro> libros = autor.getLibros();
						for (Libro libro : libros) {
							libro.getAutores().remove(autor); // remove the association
						}
						autor.getLibros().clear(); // clear the association from the author side
						autorDAO.save(autor); // save the changes
						autorDAO.deleteById(autorId); // now you can delete the author
						Thread.sleep(200);
					}
				} catch (EmptyResultDataAccessException e) {
					System.out.println("Error");
				}
			}
		}
		return gestionAutor;
	}

}
