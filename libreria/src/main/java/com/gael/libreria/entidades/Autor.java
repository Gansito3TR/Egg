package com.gael.libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {
    
    // Atributos
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;

    // Constructor
    public Autor() {
    }
    
    // Getters
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
