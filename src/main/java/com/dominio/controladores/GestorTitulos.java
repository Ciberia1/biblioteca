/**
 * La clase "GestorTitulos" es una clase de controlador en una aplicación Java Spring que maneja
 * solicitudes relacionadas con la gestión y publicación de libros y publicaciones seriadas.
 */
package com.dominio.controladores;

import java.util.List;
import java.util.Optional;

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
	
	private String rutaGestion = "redirect:/gestion";

/**
 * La función `altaObra` es un controlador de solicitudes POST que crea una nueva instancia de un
 * objeto `Libro` o `PubSeriadas`, la guarda en la base de datos y crea un número específico de objetos
 * `Ejemplar` asociados con el objeto recién creado.
 * 
 * @param obra Un objeto de tipo Obra, que contiene información general sobre la obra que se publica.
 * @param libro El parámetro "libro" es un objeto de tipo Libro, que contiene información sobre un
 * libro como sus autores, edición, editorial, encuadernación e isbn.
 * @param pubseriada El parámetro "pubseriada" es de tipo PubSeriadas, que es un atributo del modelo
 * utilizado para almacenar información sobre una publicación seriada. Contiene propiedades como
 * editor, issn, tipo y periodicidad.
 * @param claseObra Un parámetro de cadena que representa el tipo de obra (ya sea "Libro" o
 * "Publicación en serie").
 * @param nroEjemplares El parámetro "nroEjemplares" es un String que representa el número de copias de
 * la obra a crear. Se utiliza en un bucle para crear el número especificado de copias de la obra.
 * @return El método devuelve un valor de cadena "redirect:/gestion".
 */
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
	        Optional<Libro> libroOpt = libroDAO.findById(libron.getId());
	        libron=libroOpt.get();

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
	        Optional<PubSeriadas> seriadaOpt = pubSeriadaDAO.findById(Seriada.getId());
	        Seriada=seriadaOpt.get();

	        for (int i = 0; i < Integer.parseInt(nroEjemplares); i++) {
	            Ejemplar ejemplar = new Ejemplar();
	            ejemplar.setEstado("Disponible");
	            ejemplar.setObra(Seriada);
	            ejemplarDAO.save(ejemplar);
	            Thread.sleep(200);
	        }
	    }

	    return rutaGestion;
	}


/**
 * La función "actualizarObras" actualiza la información de un listado de obras (libros o publicaciones
 * seriadas) en base a los datos proporcionados.
 * 
 * @param obrasWrapper Una lista de objetos ObraWrapper.
 * @return El método devuelve una cadena "redirect:/gestion".
 */
	@PostMapping("/actualizarObras")
	public String actualizarObras(@RequestBody List<ObraWrapper> obrasWrapper) {
		for (ObraWrapper obraWrapper : obrasWrapper) {
			if (obraWrapper.isEsLibro()) {
				Libro libro = libroDAO.findById(obraWrapper.getId()).get();

				if (!obraWrapper.getEdicion().equals("-") ) {
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
		return rutaGestion;
	}

/**
 * La función "borrarObras" es un controlador de solicitudes POST que elimina múltiples objetos "obra"
 * según sus ID y redirige al punto final "/gestion".
 * 
 * @param obraIds Una lista de valores largos que representan los ID de las obras que se eliminarán.
 * @return El método devuelve una cadena "redirect:/gestion".
 */
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
		return rutaGestion;
	}
}
