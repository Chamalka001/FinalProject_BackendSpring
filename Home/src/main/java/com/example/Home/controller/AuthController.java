package com.example.Home.controller;


import com.example.Home.entity.ResponseMessage;
import com.example.Home.entity.User;
import com.example.Home.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody User user) {
        try {
            User registeredUser = authService.registerUser(user);
            if (registeredUser != null) {
                ResponseMessage responseMessage = new ResponseMessage("User registered successfully", registeredUser);
                return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
            } else {
                ResponseMessage responseMessage = new ResponseMessage("User already exists", null);
                return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage("Registration failed: " + e.getMessage(), null);
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody User user) {
        try {
            User loggedInUser = authService.login(user);
            if (loggedInUser != null) {
                ResponseMessage responseMessage = new ResponseMessage("Login successful", loggedInUser);
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            } else {
                ResponseMessage responseMessage = new ResponseMessage("Invalid credentials", null);
                return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage("Login failed: " + e.getMessage(), null);
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
