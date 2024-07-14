package com.webapp.codeathon.request;

public class ContestantLoginRequest {

	private String facultyNumberAsUsername;
	private String enrollmentNumberAsPassword;

	// Default Constructor
	public ContestantLoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	// Parameterized Constructor
	public ContestantLoginRequest(String facultyNumberAsUsername, String enrollmentNumberAsPassword) {
		super();
		this.facultyNumberAsUsername = facultyNumberAsUsername;
		this.enrollmentNumberAsPassword = enrollmentNumberAsPassword;
	}

	public String getFacultyNumberAsUsername() {
		return facultyNumberAsUsername;
	}

	public void setFacultyNumberAsUsername(String facultyNumberAsUsername) {
		this.facultyNumberAsUsername = facultyNumberAsUsername;
	}

	public String getEnrollmentNumberAsPassword() {
		return enrollmentNumberAsPassword;
	}

	public void setEnrollmentNumberAsPassword(String enrollmentNumberAsPassword) {
		this.enrollmentNumberAsPassword = enrollmentNumberAsPassword;
	}

}
