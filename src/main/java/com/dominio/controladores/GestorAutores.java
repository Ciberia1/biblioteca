package com.dominio.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dominio.entidades.*;
import com.persistencia.*;

@Controller

public class GestorAutores {
	private static final Logger log = LoggerFactory.getLogger(GestorTitulos.class);
	@Autowired
	private ObraDAO obraDAO;
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private AutorDAO autorDAO;

	@PostMapping("/publicarAutor")
	public String altaAutor(@ModelAttribute Autor autor, @ModelAttribute Libro libro,
			@ModelAttribute PubSeriadas pubseriada, @RequestParam String claseObra) {

		return "redirect:/";
	}

	@PostMapping("/actualizarAutores")
	public String actualizarAutores(@RequestBody List<Autor> autores) {
		for (Autor autor : autores) {
			Autor temporal= new Autor(autor.getId(),autor.getNombre(), autor.getApellido(), autor.getBiografia(), null);
			autorDAO.save(temporal);
		}
		return "redirect:/inicio";
	}

	@PostMapping("/borrarAutores")
	public String borrarAutores(@RequestParam(name = "id", required = false) List<Long> autoresIds) {
		if (autoresIds != null) {
			for (Long autorId : autoresIds) {
				try {
					autorDAO.deleteById(autorId);
				} catch (EmptyResultDataAccessException e) {
					System.out.println("Error");
				}
			}
		}
		return "redirect:/inicio";
	}

}
