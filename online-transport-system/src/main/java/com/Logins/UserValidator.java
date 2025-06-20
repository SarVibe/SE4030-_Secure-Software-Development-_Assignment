package com.Logins;

public class UserValidator {
    
    // Method to validate user name and password
    public boolean validate(String username, String password) {
        // Check if user name and password are not null or empty
        if (username == null || username.trim().isEmpty()) {
            return false; // Invalid user name
        }
        if (password == null || password.trim().isEmpty()) {
            return false; // Invalid password
        }
        
        // Check for minimum length
        if (username.length() < 3 || password.length() < 6) {
            return false; // User name must be at least 3 characters and password at least 6 characters
        }

        // Additional validation checks can be added here (e.g., special characters, etc.)

        return true; // Valid user name and password
    }
}