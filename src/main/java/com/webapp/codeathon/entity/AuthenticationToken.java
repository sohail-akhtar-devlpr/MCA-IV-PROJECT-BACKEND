package com.webapp.codeathon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ValueGenerationType;

import com.webapp.codeathon.role.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuthenticationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String token;

	private LocalDateTime expiryDate;

	private String role;

//	private String tokenPassword;

	public AuthenticationToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationToken(int id, String token, LocalDateTime expiryDate, String role) {
		super();
		this.id = id;
		this.token = token;
		this.expiryDate = expiryDate;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
