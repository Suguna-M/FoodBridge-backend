package com.foodbridge.service;

import com.foodbridge.dto.LoginRequest;
import com.foodbridge.dto.LoginResponse;
import com.foodbridge.dto.RegisterRequest;
import com.foodbridge.entity.User;
import com.foodbridge.repository.UserRepository;
import com.foodbridge.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        return "Registration Successful";
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return new LoginResponse(null, "User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse(null, "Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token, "Login Successful");
    }
}