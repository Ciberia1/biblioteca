package com.dominio.entidades;

import java.util.Date;

public class ObraWrapper {
	private String genero;
	private String isbn;
	private String encuadernacion;
	private String issn;
	private String editor;
	private String tipo;
	private String periodicidad;
	private String titulo;
	private Date fechaPublicacion;
	private Integer nroPaginas;
	private String editorial;
	private String edicion;
	private Long id;
    private boolean esLibro;


	public ObraWrapper(String genero, String isbn, String encuadernacion, String issn, String editor, String tipo,
			String periodicidad, String titulo, Date fechaPublicacion, Integer nroPaginas, String editorial,
			String edicion, Long id, boolean esLibro) {
		super();
		this.genero = genero;
		this.isbn = isbn;
		this.encuadernacion = encuadernacion;
		this.issn = issn;
		this.editor = editor;
		this.tipo = tipo;
		this.periodicidad = periodicidad;
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.nroPaginas = nroPaginas;
		this.editorial = editorial;
		this.edicion = edicion;
		this.id = id;
		this.esLibro = esLibro;
	}

	public ObraWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEncuadernacion() {
		return encuadernacion;
	}

	public void setEncuadernacion(String encuadernacion) {
		this.encuadernacion = encuadernacion;
	}

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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Integer getNroPaginas() {
		return nroPaginas;
	}

	public void setNroPaginas(Integer nroPaginas) {
		this.nroPaginas = nroPaginas;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEsLibro() {
		return esLibro;
	}

	public void setEsLibro(boolean esLibro) {
		this.esLibro = esLibro;
	}
	
	

}
