package com.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue(value="Seriada")
public class PubSeriadas extends Obra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ISSN", unique = true, nullable = false, length = 25)
	private String issn;

	@Column(name = "Editor", nullable = false, length = 25)
	private String editor;

	@Column(name = "Tipo", nullable = false, length = 25)
	private String tipo;

	@Column(name = "Periodicidad", nullable = false, length = 15)
	private String periodicidad;
}
