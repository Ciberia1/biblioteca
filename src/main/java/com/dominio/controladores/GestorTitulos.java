package com.dominio.controladores;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dominio.entidades.*;
import com.persistencia.*;

@Controller

public class GestorTitulos {
	private static final Logger log = LoggerFactory.getLogger(GestorTitulos.class);
	@Autowired
	private ObraDAO obraDAO;
	@Autowired
	private PubSeriadasDAO pubSeriadaDAO;
	@Autowired
	private EjemplarDAO ejemplarDAO;
	@Autowired
	private AutorDAO autorDAO;

	/**
	 * 
	 * @param titulo
	 * @param isbn
	 * @param autores
	 */
	public Obra altaTitulo(String titulo, String isbn, String[] autores) {
		// TODO - implement GestorTitulos.altaT�tulo
		throw new UnsupportedOperationException();
	}

	@PostMapping("/publicarObra")
	public String publicarObra(@ModelAttribute Obra obra, @ModelAttribute Libro libro,
			@ModelAttribute PubSeriadas pubseriada, @RequestParam String claseObra) {
		System.out.println(claseObra);

		if ("Libro".equals(claseObra)) {
			// Crear y guardar un Libro
		} else if ("Publicación seriada".equals(claseObra)) {
			System.out.println("Rrrrrrrrr");
			PubSeriadas Seriada = new PubSeriadas();
			BeanUtils.copyProperties(obra, Seriada);
			Seriada.setEditor(claseObra);
			Seriada.setIssn(pubseriada.getIssn());
			Seriada.setEditor(pubseriada.getEditor());
			Seriada.setTipo(pubseriada.getTipo());
			Seriada.setPeriodicidad(pubseriada.getPeriodicidad());
			pubSeriadaDAO.save(Seriada);
		}
		return "redirect:/";
	}

	/**
	 * 
	 * @param t
	 */
	public void actualizarTitulo(Obra t) {
		// TODO - implement GestorTitulos.actualizarTitulo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param t
	 */

	@PostMapping("/gestion")
	public String gestionObras(@RequestParam(name = "obraId", required = false) List<Long> obraIds) {
		if (obraIds != null) {
			for (Long obraId : obraIds) {
				try {
					obraDAO.deleteById(obraId);
				} catch (EmptyResultDataAccessException e) {
					System.out.println("Error");
				}
			}
		}
		// Redirige a la página principal después de eliminar las obras
		return "redirect:/inicio";
	}

	/**
	 * 
	 * @param t
	 */
	public void altaEjemplar(Obra t) {
		// TODO - implement GestorTitulos.altaEjemplar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param t
	 */
	public void bajaEjemplar(Obra t) {
		// TODO - implement GestorTitulos.bajaEjemplar
		throw new UnsupportedOperationException();
	}

}
