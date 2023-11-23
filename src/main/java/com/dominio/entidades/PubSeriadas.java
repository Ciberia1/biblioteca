/**
 * La clase `PubSeriadas` es una subclase de la clase `Obra` y representa una publicación serializada
 * con propiedades como ISSN, editor, tipo y periodicidad.
 */
package com.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value="Seriada")
public class PubSeriadas extends Obra {
	public PubSeriadas() {
		super();
	}
	public PubSeriadas(String issn, String editor, String tipo, String periodicidad) {
		super();
		this.issn = issn;
		this.editor = editor;
		this.tipo = tipo;
		this.periodicidad = periodicidad;
	}

	@Column(name = "ISSN", unique = true, nullable = true, length = 25)
	private String issn;

	@Column(name = "Editor", nullable = true, length = 25)
	private String editor;

	@Column(name = "Tipo", nullable = true, length = 25)
	private String tipo;

	@Column(name = "Periodicidad", nullable = true, length = 15)
	private String periodicidad;

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	
	
}
