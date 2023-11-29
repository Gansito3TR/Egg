package com.gael.libreria.sevicios;

import com.gael.libreria.entidades.Autor;
import com.gael.libreria.excepciones.MiExcepction;
import com.gael.libreria.repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicios {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    // Método que crea un nuevo autor
    @Transactional
    public void crearAutor(String nombre) throws MiExcepction{
        validar(nombre);
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
    // Método que hace una lista de los autores
    public List<Autor> listarAutores(){
        
        List<Autor> autores = new ArrayList();
        
        autores = autorRepositorio.findAll();
        
        return autores;
    }
    
    // Método que modifica un autor
    public void modificarAutor(String nombre, String id){
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Autor autor = respuesta.get();
            
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);            
        }
    }
    
    // Método que valida los parámetros
    public void validar(String nombre) throws MiExcepction{
        if (nombre == null || nombre.isEmpty()) {
            throw new MiExcepction("El nombre del autor no puede ser nulo");
        }
    }
}
