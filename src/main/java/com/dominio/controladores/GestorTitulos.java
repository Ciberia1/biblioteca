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

public class GestorTitulos {
	private static final Logger log = LoggerFactory.getLogger(GestorTitulos.class);
	@Autowired
	private ObraDAO obraDAO;
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private PubSeriadasDAO pubSeriadaDAO;
	@Autowired
	private EjemplarDAO ejemplarDAO;
	@Autowired
	private AutorDAO autorDAO;

	@PostMapping("/publicarObra")
	public String altaObra(@ModelAttribute Obra obra, @ModelAttribute Libro libro,
			@ModelAttribute PubSeriadas pubseriada, @RequestParam String claseObra) {

		if ("Libro".equals(claseObra)) {
			Libro libron = new Libro();
			BeanUtils.copyProperties(obra, libron);
			libron.setAutores(libro.getAutores());
			libron.setEdicion(libro.getEdicion());
			libron.setEditorial(libro.getEditorial());
			libron.setEncuadernacion(libro.getEncuadernacion());
			libron.setIsbn(libro.getIsbn());
			libroDAO.save(libron);

		} else if ("Publicación seriada".equals(claseObra)) {
			PubSeriadas Seriada = new PubSeriadas();
			BeanUtils.copyProperties(obra, Seriada);
			Seriada.setEditor(pubseriada.getEditor());
			Seriada.setIssn(pubseriada.getIssn());
			Seriada.setEditor(pubseriada.getEditor());
			Seriada.setTipo(pubseriada.getTipo());
			Seriada.setPeriodicidad(pubseriada.getPeriodicidad());
			pubSeriadaDAO.save(Seriada);
		}
		return "redirect:/";
	}

	@PostMapping("/actualizarObras")
	public String actualizarObras(@RequestBody List<ObraWrapper> obrasWrapper) {
		for (ObraWrapper obraWrapper : obrasWrapper) {
			if (obraWrapper.isEsLibro()) {
				Libro libro = new Libro();
				if (obraWrapper.getEdicion() != "-") {
					libro.setEdicion(obraWrapper.getEdicion());
				}

				if (!obraWrapper.getGenero().equals("-")) {
					libro.setGenero(obraWrapper.getGenero());
				}
				if (!obraWrapper.getIsbn().equals("-")) {
					libro.setIsbn(obraWrapper.getIsbn());
				}
				if (!obraWrapper.getEncuadernacion().equals("-")) {
					libro.setEncuadernacion(obraWrapper.getEncuadernacion());
				}
				if (!obraWrapper.getEditorial().equals("-")) {
					libro.setEditorial(obraWrapper.getEditorial());
				}

				libro.setFechaPublicacion(obraWrapper.getFechaPublicacion());
				libro.setNroPaginas(obraWrapper.getNroPaginas());
				libro.setId(obraWrapper.getId());
				libro.setTitulo(obraWrapper.getTitulo());

				libroDAO.save(libro);

			} else {
				PubSeriadas pubSeriada = new PubSeriadas();
				if (!obraWrapper.getIssn().equals("-")) {
					pubSeriada.setIssn(obraWrapper.getIssn());
				}
				if (!obraWrapper.getEditor().equals("-")) {
					pubSeriada.setEditor(obraWrapper.getEditor());
				}
				if (!obraWrapper.getTipo().equals("-")) {
					pubSeriada.setTipo(obraWrapper.getTipo());
				}
				if (!obraWrapper.getPeriodicidad().equals("-")) {
					pubSeriada.setPeriodicidad(obraWrapper.getPeriodicidad());
				}

				pubSeriada.setFechaPublicacion(obraWrapper.getFechaPublicacion());
				pubSeriada.setNroPaginas(obraWrapper.getNroPaginas());
				pubSeriada.setId(obraWrapper.getId());
				pubSeriada.setTitulo(obraWrapper.getTitulo());
				pubSeriadaDAO.save(pubSeriada);
			}
		}
		return "redirect:/gestion";
	}

	@PostMapping("/borrarObras")
	public String borrarObras(@RequestParam(name = "id", required = false) List<Long> obraIds) {
		if (obraIds != null) {
			for (Long obraId : obraIds) {
				try {
					obraDAO.deleteById(obraId);
				} catch (EmptyResultDataAccessException e) {
					System.out.println("Error");
				}
			}
		}
		return "redirect:/inicio";
	}

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
