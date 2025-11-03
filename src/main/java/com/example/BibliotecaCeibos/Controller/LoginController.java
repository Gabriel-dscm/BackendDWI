package com.example.BibliotecaCeibos.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginController {

    @PostMapping
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        Map<String, Object> response = new HashMap<>();

        if ("admin".equals(username) && "admin123".equals(password)) {
            response.put("role", "admin");
            response.put("access", true);
        } else if ("user".equals(username) && "user123".equals(password)) {
            response.put("role", "user");
            response.put("access", true);
        } else {
            response.put("access", false);
        }

        return response;
    }
}
