package com.example.Pegaso.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Pegaso.domain.Service.auth.AuthenticationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha) {
        Optional<String> token = authenticationService.login(email, senha);

        if (token.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
    @PostMapping("/register")
public ResponseEntity<?> register(@RequestParam String nome, @RequestParam String email, @RequestParam String senha) {
    try {
        authenticationService.register(nome, email, senha);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    } catch (Exception e) {
        return ResponseEntity.status(400).body("Erro no registro: " + e.getMessage());
    }
}

}
