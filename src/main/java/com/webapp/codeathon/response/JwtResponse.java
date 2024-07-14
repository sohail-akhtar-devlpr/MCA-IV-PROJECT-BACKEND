package com.webapp.codeathon.response;

import com.webapp.codeathon.role.UserRole;

public class JwtResponse {

    // 1. Set jwt Response
    private String jwt;
    private String message;
    private boolean isAuthenticated;
    private boolean isError;
    private String errorDetails;
    private UserRole type;
    private String username; // New field

    // Default Constructor
    public JwtResponse() {
        super();
    }

    // Parameterized Constructor
    public JwtResponse(String jwt, String message, boolean isAuthenticated, boolean isError, String errorDetails,
            UserRole type, String username) { // Modified constructor
        super();
        this.jwt = jwt;
        this.message = message;
        this.isAuthenticated = isAuthenticated;
        this.isError = isError;
        this.errorDetails = errorDetails;
        this.type = type;
        this.username = username; // Set the username
    }

    // Getters and Setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public UserRole getType() {
        return type;
    }

    public void setType(UserRole type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
