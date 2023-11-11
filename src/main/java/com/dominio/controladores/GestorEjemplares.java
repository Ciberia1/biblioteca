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
