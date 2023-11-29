package com.gael.libreria.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Esta anotación quiere decir que es un controlador, un puente entre el
// html y el back del programa. La de abajo es para vincular la url.
@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String index(){
        
        return "index.html";
    }
    
}
