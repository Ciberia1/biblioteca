package com.dominio.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dominio.entidades.*;
import com.persistencia.UsuarioDAO;

@Controller
public class LoginController {

	    @Autowired
	    private UsuarioDAO usuarioDAO;

	    @GetMapping("/login")
	    public String formularioLogin(Model modelo) {
	        modelo.addAttribute("usuario", new Usuario());
	        return "login";
	    }
	    
	    @PostMapping("/login")
	    public String enviarFormularioLogin(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {

	        Usuario usuarioExistente = usuarioDAO.findByDni(usuario.getDni());
	        if (usuarioExistente == null || !usuarioExistente.getContrasena().equals(usuario.getContrasena())) {
	            redirectAttributes.addAttribute("error", "invalid");
	            return "redirect:/login";
	        }

	        // Código para manejar la información de inicio de sesión enviada...
	        return "redirect:/inicio";  // Redirige a inicio.html si los datos de inicio de sesión son correctos
	    }
	}
