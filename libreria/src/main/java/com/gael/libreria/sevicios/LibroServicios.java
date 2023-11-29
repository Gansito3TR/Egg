package com.gael.libreria.sevicios;

import com.gael.libreria.entidades.Autor;
import com.gael.libreria.entidades.Editorial;
import com.gael.libreria.entidades.Libro;
import com.gael.libreria.excepciones.MiExcepction;
import com.gael.libreria.repositorio.AutorRepositorio;
import com.gael.libreria.repositorio.EditorialRepositorio;
import com.gael.libreria.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicios {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    
    // Este método o servicio nos ayuda a crear un nuevo libro e incorporarlo a la
    // base de datos automáticamente
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepction{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
    }
    
    // Este método/servicio regresa una lista de todos los libros en la base de datos
    public List<Libro> listarLibros(){
        
        List<Libro> libros = new ArrayList();
        
        libros = libroRepositorio.findAll();
        
        return libros;
    }
    
    // Este método modifica un libro, pero con Optionals vamos a validar si los 
    // argumentos que dimos por parámetros existen
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares)throws MiExcepction{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if (respuestaAutor.isPresent()) {
            
            autor = respuestaAutor.get();
        }
        
        if (respuestaEditorial.isPresent()) {
            
            editorial = respuestaEditorial.get();
            
        }
        
        if (respuestaLibro.isPresent()) {
            
            Libro libro = respuestaLibro.get();
            
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
        }
        
    }
    
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepction{
        
        if (isbn == null){
            throw new MiExcepction("El isbn no puede ser nulo");
        }
        if (titulo == null || titulo.isEmpty()){
            throw new MiExcepction("El título no puede ser nulo o estar vacío");
        }
        if (idAutor == null || idAutor.isEmpty()){
            throw new MiExcepction("El autor no puede ser nulo o estar vacío");
        }
        if (idEditorial == null || idEditorial.isEmpty()){
            throw new MiExcepction("La editorial no puede ser nula o estar vacía");
        }
        if (ejemplares == null){
            throw new MiExcepction("Los ejemplares no pueden iniciar sin un libro, no puede ser nulo");
        }  
    }
}
