package com.gael.libreria.controlador;

import com.gael.libreria.excepciones.MiExcepction;
import com.gael.libreria.sevicios.EditorialServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicios editorialServicios;
    
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
        return "editorial_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
        
        try {
            editorialServicios.crearEditorial(nombre);
            modelo.put("exito", "Tu editorial se ha guardado con éxito");
            
        } catch (MiExcepction ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }
}
