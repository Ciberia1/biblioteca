package com;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

/**
 * La función `configure` se utiliza para especificar el origen de la aplicación para su
 * implementación en una aplicación Spring Boot.
 * 
 * @param application El objeto SpringApplicationBuilder que se utiliza para configurar y compilar la
 * aplicación Spring.
 * @return El método devuelve una instancia de la clase SpringApplicationBuilder.
 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryApplication.class);
	}
}
