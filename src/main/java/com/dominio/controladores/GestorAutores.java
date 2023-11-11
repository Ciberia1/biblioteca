package com.dominio.controladores;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dominio.entidades.*;
import com.persistencia.*;

@Controller

public class GestorAutores {
	@Autowired
	private ObraDAO obraDAO;
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private AutorDAO autorDAO;

	@PostMapping("/publicarAutor")
	public String altaAutor(@ModelAttribute Autor autor) {
		autorDAO.save(autor);
		return "redirect:/gestionAutor";
	}
	
	@PostMapping("/editarAutorObra")
	public String editarAutorObra(@RequestParam("libroId") String libroId, @RequestParam(value = "autorIds", required = false) List<String> autorIds) {
		Set<Autor> autoresnuevos = new HashSet<>();
		if (autorIds != null) {
		for (String autor : autorIds) {
			Autor temporal= autorDAO.getById(Long.valueOf(autor));
			autoresnuevos.add(temporal);
		}
	}
		Libro nuevo = libroDAO.getById(Long.valueOf(libroId));
		nuevo.setAutores(autoresnuevos);
		libroDAO.save(nuevo);
		
        return "redirect:/gestionAutor";
    }

	@PostMapping("/actualizarAutores")
	public String actualizarAutores(@RequestBody List<Autor> autores) {
		for (Autor autor : autores) {
			Autor temporal= new Autor(autor.getId(),autor.getNombre(), autor.getApellido(), autor.getBiografia(), null);
			autorDAO.save(temporal);
		}
		return "redirect:/gestionAutor";
	}

	@PostMapping("/borrarAutores")
	public String borrarAutores(@RequestParam(name = "id", required = false) List<Long> autoresIds) throws InterruptedException {
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
	    return "redirect:/gestionAutor";
	}


}
