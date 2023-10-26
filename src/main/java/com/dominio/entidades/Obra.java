package com.dominio.entidades;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="claseObra", discriminatorType= DiscriminatorType.STRING)
public class Obra {

    @OneToMany(mappedBy = "obra")
    private Collection<Ejemplar> ejemplares;

    @Column(name = "Genero")
    private String genero;

    @Column(name = "Titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "NroPaginas", nullable = false)
    private int nroPaginas;

    @Id
    @Column(name = "ID", nullable = false, length = 10)
    private String id;

    @Column(name = "FechaPublicacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
}