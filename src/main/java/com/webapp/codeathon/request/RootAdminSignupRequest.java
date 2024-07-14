package com.webapp.codeathon.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RootAdminSignupRequest {

	@NotBlank(message = "First Name is required")
	@Size(min = 2, max = 100, message = "First Name should contain atleast 2 characters")
	private String firstName;

	private String middleName;

	private String lastName;

	@NotBlank(message = "Gender is required")
	private String gender;

	@NotBlank(message = "Mobile Number is required")
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Mobile Number should be valid")

	private String mobileNumber;

	@NotBlank(message = "Email is Required")
	@Email(message = "Email Should be valid")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 40, message = "Password must be atleast 8 characters long")
	private String password;

	// Default Constructor
	public RootAdminSignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized Constructor
	public RootAdminSignupRequest(String firstName, String middleName, String lastName, String gender,
			String mobileNumber, String email, String password) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}

	// Getters and Setters
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

}
