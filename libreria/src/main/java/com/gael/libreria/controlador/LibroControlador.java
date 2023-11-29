package com.gael.libreria.controlador;

import com.gael.libreria.entidades.Autor;
import com.gael.libreria.entidades.Editorial;
import com.gael.libreria.entidades.Libro;
import com.gael.libreria.excepciones.MiExcepction;
import com.gael.libreria.sevicios.AutorServicios;
import com.gael.libreria.sevicios.EditorialServicios;
import com.gael.libreria.sevicios.LibroServicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicios libroServicios;
    @Autowired
    private AutorServicios autorServicios;
    @Autowired
    private EditorialServicios editorialServicios;

    @GetMapping("/registrar") // "localhost:8080/libro/registrar"
    public String registrar(ModelMap modelo) {

        List<Autor> autores = autorServicios.listarAutores();
        List<Editorial> editoriales = editorialServicios.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro") // "localhost:8080/libro/registro"
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial, ModelMap modelo) {
        try {

            // Probamos guardar el libro después de darle los parametros únicamente necesarios
            // Datos como ejemplares y id los omitimos con @RequestParam(required = false)
            libroServicios.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "Felicidades, tu libro se ha guardado con éxito.");

        } catch (MiExcepction ex) {
            List<Autor> autores = autorServicios.listarAutores();
            List<Editorial> editoriales = editorialServicios.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            modelo.put("error", ex.getMessage());
            return "libro_form.html"; // vuelve a cargar el formulario en caso de error
        }

        return "index.html";
    }
    
    // Método que envia al endpoint la lista de todos los libros
    @GetMapping("/lista") // "localhost:8080/libro/lista"
    public String listar(ModelMap modelo){
        
        List<Libro> libros = libroServicios.listarLibros();
        
        modelo.addAttribute("libros", libros);
        
        return "libro_list.html";
    }

}
