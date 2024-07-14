package com.webapp.codeathon.response;

import org.springframework.http.HttpStatus;

public class AuthenticationResponse {
    private HttpStatus status;
    private String jwtToken;

    public AuthenticationResponse(HttpStatus created, String jwtToken) {
        this.status = created;
        this.jwtToken = jwtToken;
    }

    // Getters and Setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

