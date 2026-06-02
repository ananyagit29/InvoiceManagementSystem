package com.example.invoiceBackend.controller;

import com.example.invoiceBackend.dto.LoginRequest;
import com.example.invoiceBackend.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService =
                authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String>
    login(
            @RequestBody
            LoginRequest request) {

        boolean valid =
                authService.login(
                        request.getUsername(),
                        request.getPassword());

        if(valid) {

            return ResponseEntity.ok(
                    "Login Success");
        }

        return ResponseEntity
                .badRequest()
                .body("Invalid User");
    }
}