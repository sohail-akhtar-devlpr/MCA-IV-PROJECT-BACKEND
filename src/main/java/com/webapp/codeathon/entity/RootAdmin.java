package com.webapp.codeathon.entity;


import com.webapp.codeathon.role.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RootAdmin {
	
	//1
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//2
	private String firstName;
	
	//3
	private String middleName;
	
	//4
	private String lastName;
	
	//5
	private String gender;
	
	//6
	@Column(unique = true)
	private String mobileNumber;
	
	//7
	private String email;
	
	//8
	private String password;
	
	//9
	private UserRole role;

	//GETTERS AND SETTERS
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

	//DEFAULT CONSTRUCTOR
	public RootAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	//PARAMETERIZED CONSTRUCTOR
	public RootAdmin(Integer id, String firstName, String middleName, String lastName, String gender,
			String mobileNumber, String email, String password, UserRole role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.role = role;
	}

}
