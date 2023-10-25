package dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PubSeriadas extends Obra {
	
	public PubSeriadas() {
	}
    public PubSeriadas(Long id, String issn, String editor, int periodicidad, String tipo) {
		super();
		this.id = id;
		this.issn = issn;
		this.editor = editor;
		this.periodicidad = periodicidad;
		this.tipo = tipo;
	}
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String issn;
    @Column
	private String editor;
    @Column
	private int periodicidad;
    @Column
	private String tipo;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(int periodicidad) {
		this.periodicidad = periodicidad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}