package com.library_management.Library_Management_System.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.library_management.Library_Management_System.model.User;
import com.library_management.Library_Management_System.services.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")  // allow React to access
public class AuthController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER"); // must match Spring Security role
        userService.saveUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // Optional test endpoint
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Library Management System API!";
    }
}
