package com.dominio.controladores;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

public class GestorTitulos {
	@Autowired
	private ObraDAO obraDAO;
	@Autowired
	private LibroDAO libroDAO;
	@Autowired
	private PubSeriadasDAO pubSeriadaDAO;
	@Autowired
	private EjemplarDAO ejemplarDAO;

	@PostMapping("/publicarObra")
	public String altaObra(@ModelAttribute Obra obra, @ModelAttribute Libro libro,
	        @ModelAttribute PubSeriadas pubseriada, @RequestParam String claseObra,
	        @RequestParam String nroEjemplares) throws InterruptedException {

	    if ("Libro".equals(claseObra)) {
	        Libro libron = new Libro();
	        BeanUtils.copyProperties(obra, libron, "id");
	        libron.setAutores(libro.getAutores());
	        libron.setEdicion(libro.getEdicion());
	        libron.setEditorial(libro.getEditorial());
	        libron.setEncuadernacion(libro.getEncuadernacion());
	        libron.setIsbn(libro.getIsbn());

	        libroDAO.save(libron);
	        libron = libroDAO.findById(libron.getId()).get();

	        for (int i = 0; i < Integer.parseInt(nroEjemplares); i++) {
	            Ejemplar ejemplar = new Ejemplar();
	            ejemplar.setEstado("Disponible");
	            ejemplar.setObra(libron);
	            ejemplarDAO.save(ejemplar);
	            Thread.sleep(200);
	        }

	    } else if ("Publicación seriada".equals(claseObra)) {
	        PubSeriadas Seriada = new PubSeriadas();
	        BeanUtils.copyProperties(obra, Seriada, "id");
	        Seriada.setEditor(pubseriada.getEditor());
	        Seriada.setIssn(pubseriada.getIssn());
	        Seriada.setEditor(pubseriada.getEditor());
	        Seriada.setTipo(pubseriada.getTipo());
	        Seriada.setPeriodicidad(pubseriada.getPeriodicidad());

	        pubSeriadaDAO.save(Seriada);
	        Seriada = pubSeriadaDAO.findById(Seriada.getId()).get();

	        for (int i = 0; i < Integer.parseInt(nroEjemplares); i++) {
	            Ejemplar ejemplar = new Ejemplar();
	            ejemplar.setEstado("Disponible");
	            ejemplar.setObra(Seriada);
	            ejemplarDAO.save(ejemplar);
	            Thread.sleep(200);
	        }
	    }

	    return "redirect:/gestion";
	}


	@PostMapping("/actualizarObras")
	public String actualizarObras(@RequestBody List<ObraWrapper> obrasWrapper) {
		for (ObraWrapper obraWrapper : obrasWrapper) {
			if (obraWrapper.isEsLibro()) {
				Libro libro = libroDAO.findById(obraWrapper.getId()).get();

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
				libro.setTitulo(obraWrapper.getTitulo());

				libroDAO.save(libro);

			} else {
				PubSeriadas pubSeriada =pubSeriadaDAO.findById(obraWrapper.getId()).get();

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
		return "redirect:/gestion";
	}
}
