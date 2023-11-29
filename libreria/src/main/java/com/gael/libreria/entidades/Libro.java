package com.gael.libreria.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Libro {
    
    // Atributos
    @Id
    private Long isbn;
    private String titulo;
    private Integer ejemplares;
    @Temporal(TemporalType.DATE)
    private Date alta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;
    
    // Constructores
    public Libro() {
    }
    
    // Getters
    public Long getIsbn() {
        return isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public Integer getEjemplares() {
        return ejemplares;
    }
    public Date getAlta() {
        return alta;
    }
    public Autor getAutor() {
        return autor;
    }
    public Editorial getEditorial() {
        return editorial;
    }
    
    // Setters
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }
    public void setAlta(Date alta) {
        this.alta = alta;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
    
}
