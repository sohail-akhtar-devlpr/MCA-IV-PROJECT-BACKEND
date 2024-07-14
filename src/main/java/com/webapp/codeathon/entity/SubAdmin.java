package com.webapp.codeathon.entity;

import com.webapp.codeathon.role.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SubAdmin {

	// 1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// 2
	private String firstName;

	// 3
	private String middleName;

	// 4
	private String lastName;

	// 5
	private String gender;

	// 6
//	@Column(unique = true)
	private String mobileNumber;

	// 7
	private String designation;

	// 8
	@Column(unique = true)
	private String email;

	// 9
	private String password;

	// 10
	@Enumerated(EnumType.STRING)
	private UserRole role;

	// DEFAULT CONSTRUCTOR
	public SubAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	// PARAMETERIZED CONSTRUCTOR
	public SubAdmin(String firstName, String middleName, String lastName, String gender, String mobileNumber,
			String designation, String email, String password, UserRole role) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.designation = designation;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
