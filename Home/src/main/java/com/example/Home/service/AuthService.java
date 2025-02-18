package com.example.Home.service;


import com.example.Home.entity.User;
import com.example.Home.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    public User registerUser(User user) {
        if (checkUserExist(user.getEmail())) {
            return null; // User already exists
        }
        return authRepo.save(user);
    }

    private boolean checkUserExist(String email) {
        return authRepo.findByEmail(email).isPresent();
    }

    public User login(User user) {
        // Ensure user email is not null or empty
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            System.out.println("Email is missing");
            return null; // Return null if email is missing
        }

        // Log the email being attempted to log in
        System.out.println("Attempting login for user with email: " + user.getEmail());

        // Retrieve the user using the custom findByEmail method
        User existingUser = authRepo.findByEmail(user.getEmail()).orElse(null);

        if (existingUser != null) {
            // Log the user found
            System.out.println("User found with email: " + existingUser.getEmail());

            // Compare passwords - add hash comparison if using hashed passwords
            if (existingUser.getPassword().equals(user.getPassword())) {
                System.out.println("Password matches for user: " + existingUser.getEmail());
                existingUser.setPassword(""); // Clear the password for security reasons
                return existingUser; // Return user if credentials match
            } else {
                System.out.println("Incorrect password for user: " + existingUser.getEmail());
            }
        } else {
            System.out.println("No user found with email: " + user.getEmail());
        }

        // Return null if no user found or password doesn't match
        return null;
    }



}
