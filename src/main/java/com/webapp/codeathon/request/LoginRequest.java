package com.webapp.codeathon.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginRequest {

	@NotBlank(message = "Email is required")
    @Pattern(regexp ="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
    private String email;
	
	@NotBlank(message = "Password is required")
	private String password;

	// Default Constructor
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized Constructor
	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
