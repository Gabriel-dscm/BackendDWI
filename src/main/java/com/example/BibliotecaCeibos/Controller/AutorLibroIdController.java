package com.example.BibliotecaCeibos.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autorlibroid")
@CrossOrigin("*")
public class AutorLibroIdController {

    @GetMapping("/info")
    public String info() {
        return "Controlador para manejar IDs compuestos de AutorLibro (IdAutor, IdLibro)";
    }
}
