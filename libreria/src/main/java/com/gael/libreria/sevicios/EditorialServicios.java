package com.gael.libreria.sevicios;

import com.gael.libreria.entidades.Editorial;
import com.gael.libreria.excepciones.MiExcepction;
import com.gael.libreria.repositorio.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicios {
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    // Método que crea una nueva editorial
    @Transactional
    public void crearEditorial(String nombre) throws MiExcepction{
        
        validar(nombre);
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
    }
    
    // Método que devuelve una lista de todas las editoriales en la base de datos
    public List<Editorial> listarEditoriales(){
        
        List<Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepositorio.findAll();
        
        return editoriales;
    }
    
    // Método que modifica una editoria
    public void modificarEditorial(String nombre, String id){
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        }
        
    }
    
    // Método que valida los parámetros
    public void validar(String nombre) throws MiExcepction{
        if (nombre == null || nombre.isEmpty()) {
            throw new MiExcepction("El nombre de la editorial no puede ser nulo");
        }
    }
}
