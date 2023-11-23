/**
 * La clase "GestorEjemplares" es un controlador Java que maneja la creación y eliminación de ejemplares
 * asociados a las obras.
 */
package com.dominio.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dominio.entidades.Ejemplar;
import com.dominio.entidades.Obra;
import com.persistencia.EjemplarDAO;
import com.persistencia.ObraDAO;

@Controller
public class GestorEjemplares {
	@Autowired
	private EjemplarDAO ejemplarDAO;
	@Autowired
	private ObraDAO obraDAO;

/**
 * La función "altaEjemplar" es un método Java que crea un número específico de objetos "Ejemplar"
 * asociados con un objeto "Obra" determinado y establece su estado inicial en "Disponible".
 * 
 * @param obraId El ID de la obra para la cual se crea el ejemplar (copia).
 * @param cantidad El parámetro "cantidad" representa el número de copias de un libro (ejemplar) que se
 * crearán y publicarán.
 * @param redirectAttributes RedirectAttributes es una clase proporcionada por Spring MVC que le
 * permite agregar atributos a la URL de redireccionamiento. Estos atributos se almacenan temporalmente
 * y se puede acceder a ellos en la URL o vista redirigida. Se usa comúnmente para mostrar mensajes
 * flash o pasar datos entre solicitudes. En este fragmento de código, el atributo "mensaje"
 * @return El método devuelve una cadena.
 */
	@PostMapping("/publicarEjemplar")
	public String altaEjemplar(@RequestParam(name = "id") Long obraId, @RequestParam(name = "nroEjemplar") int cantidad,
			RedirectAttributes redirectAttributes) throws InterruptedException {
		try {
			Obra obra = obraDAO.findById(obraId).orElse(null);
			if (obra != null) {
				for (int i = 0; i < cantidad; i++) {
					Ejemplar ejempar = new Ejemplar();
					ejempar.setObra(obra);
					ejempar.setEstado("Disponible");
					ejemplarDAO.save(ejempar);
			        Thread.sleep(200);
				}
				return "redirect:/gestionEjemplar";
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Error");
		}
		redirectAttributes.addFlashAttribute("message", "Error"); // 오류 메시지 추가
		return "redirect:/publicarEjemplar";
	}

/**
 * La función `bajaEjemplar` elimina múltiples objetos ejemplares de la base de datos según sus ID.
 * 
 * @param data {
 * @return El método devuelve una cadena "redirect:/inicio".
 */
	@PostMapping("/borrarEjemplar")
	public String bajaEjemplar(@RequestBody Map<String, List<Long>> data) {
		List<Long> ejemplarIds = data.get("id");
		if (ejemplarIds != null) {
			for (Long ejemplarId : ejemplarIds) {
				try {
					ejemplarDAO.deleteById(ejemplarId);
				} catch (EmptyResultDataAccessException e) {
					System.out.println("Error");
				}
			}
		}
		return "redirect:/inicio";
	}
}
