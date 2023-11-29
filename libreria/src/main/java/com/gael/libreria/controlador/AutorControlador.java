package com.gael.libreria.controlador;

import com.gael.libreria.entidades.Autor;
import com.gael.libreria.excepciones.MiExcepction;
import com.gael.libreria.sevicios.AutorServicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {
    
    // Anotación para traer entidades y usar sus métodos. 
    @Autowired
    private AutorServicios autorServicios;
    
    // Este método nos ayuda a decirle al servidor, que en la url de autor, y 
    // luego en registrar, vamos a ir al html que hicimos para todos los autores
    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar(){
        return "autor_form.html";
    }
    
    // La siguiente anotación va a buscar un objeto de tipo post, para que el 
    // usuario ingrese el nombre del autor, y este lo reciba como argumento/parámero
    // y así, usarlo en la consulta de la base de datos
    @PostMapping("/registro") // localhost:8080/autor/registro
    public String registro(@RequestParam String nombre, ModelMap modelo){
        System.out.println("Nombre del autor: "+ nombre);
        
        try {
            autorServicios.crearAutor(nombre);
            modelo.put("exito", "El autor ha sido cargado correctamente");
        } catch (MiExcepction ex) {
            modelo.put("error", ex.getMessage());
            return "autor_form.html";
        }
        
        return "index.html";
    }
    
    // Método que enlista los autores de la base de datos
    @GetMapping("lista") // localhost:8080/autor/lista
    public String listar(ModelMap modelo){
        
        List<Autor> autores = autorServicios.listarAutores();
        
        modelo.addAttribute("autores", autores);
        
        return "autor_list.html";
    }
    
}
