package com.foodbridge.controller;

import com.foodbridge.dto.LoginRequest;
import com.foodbridge.dto.LoginResponse;
import com.foodbridge.dto.RegisterRequest;
import com.foodbridge.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {

        String response = authService.register(request);

        if (response.equals("Registration Successful")) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        if (response.getToken() != null) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().body(response);
    }
}