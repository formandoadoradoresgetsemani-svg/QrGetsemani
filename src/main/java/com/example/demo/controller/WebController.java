package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebController {

    // Si usas application.properties: admin.password=admin1
    // También podrías inyectarlo con @Value, pero para dejarlo simple y estable:
    private static final String ADMIN_PASSWORD = "admin123";

    @GetMapping("/")
    public String home() {
        // Página pública por defecto
        return "forward:/verify.html";
    }

    @GetMapping("/verify")
    public String verify() {
        return "forward:/verify.html";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "forward:/perfil.html";
    }

    @GetMapping("/admin/generate")
    public String adminGenerate(HttpSession session, @RequestParam(required = false) String password) {

        // Requiere password en la URL: /admin/generate?password=admin1
        if (password == null || !ADMIN_PASSWORD.equals(password)) {
            // Si no manda o es incorrecta, reenvía al verificador
            return "redirect:/verify";
        }

        // Password correcta -> marca sesión admin por 30 minutos
        session.setAttribute("admin", true);
        session.setMaxInactiveInterval(30 * 60);
        return "forward:/generate.html";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/verify";
    }
}
