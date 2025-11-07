package com.example.BibliotecaCeibos.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libropalabraclaveid")
@CrossOrigin("*")
public class LibroPalabraClaveIdController {

    @GetMapping("/info")
    public String info() {
        return "Controlador para manejar IDs compuestos de LibroPalabraClave (IdLibro, IdPalabraClave)";
    }
}
