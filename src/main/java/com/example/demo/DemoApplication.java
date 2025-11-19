package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
@EntityScan(basePackages = "com.example.demo.entity")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   🔐 Sistema de QR Encriptados - AES-256 INICIADO       ║");
        System.out.println("║   📡 API: http://localhost:8080                          ║");
        System.out.println("║   ✅ Endpoints: /api/qr/generate, /api/crypto/encrypt   ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }

    // ======================================================
    //   ⭐ CORS GLOBAL AQUÍ MISMO (como en el video)
    // ======================================================
    @Configuration
    public static class CorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("*") // Cambiar por dominio real en producción
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS")
                            .allowedHeaders("*");
                }
            };
        }
    }
}
