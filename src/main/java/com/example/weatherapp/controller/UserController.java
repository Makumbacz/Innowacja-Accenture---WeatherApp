package com.example.weatherapp.controller;

import com.example.weatherapp.dto.UserRegistrationRequest;
import com.example.weatherapp.model.User;
import com.example.weatherapp.repository.UserRepository;
import com.example.weatherapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String getUser(Principal principal){
        return "Welcome to Weather App, " + principal.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        String username = request.getUsername();
        String email = request.getEmail();
        String password = request.getPassword();

        // Validate request fields
        if (username == null || username.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }

        // Check if user with same email already exists in the database
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("User with same email already exists");
        }

        // Create new user object with the request fields
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);


        userService.addNewUser(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
