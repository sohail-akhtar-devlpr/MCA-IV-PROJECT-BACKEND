package com.webapp.codeathon.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ContestantSignupRequest {

	@NotBlank(message = "Enrollment number is mandatory")
//	@Size(min = 2, max = 100, message = "First Name should contain atleast 2 characters")
	private String enrollmentNumber;

	@NotBlank(message = "FacultyNumber is mandatory")
//	@Size(min = 2, max = 100, message = "Name should contain atleast 2 characters")
	private String facultyNumber;

	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 100, message = "Name should contain atleast 2 characters")
	private String fullName;

	@NotBlank(message = "Gender is required")
	private String gender;

	@NotBlank(message = "Mobile Number is required")
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Mobile Number should be valid")
	private String mobileNumber;

	@NotBlank(message = "Email is Required")
	@Email(message = "Email Should be valid")
	private String email;

	@NotBlank(message = "Department cannot be blank")
	private String department;

	@NotBlank(message = "Course cannot be blank")
	private String course;

	@NotBlank(message = "Participation Type cannot be blank")
	private String participationType;

	private String contestNumber;

//	@NotBlank(message = "Password is required")
//    @Size(min = 8, max = 40, message = "Password must be atleast 8 characters long")
//	private String password;

	// Default Constructor
	public ContestantSignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContestantSignupRequest(@NotBlank(message = "Enrollment number is mandatory") String enrollmentNumber,
			@NotBlank(message = "FacultyNumber is mandatory") String facultyNumber,
			@NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 100, message = "Name should contain atleast 2 characters") String fullName,
			@NotBlank(message = "Gender is required") String gender,
			@NotBlank(message = "Mobile Number is required") @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Mobile Number should be valid") String mobileNumber,
			@NotBlank(message = "Email is Required") @Email(message = "Email Should be valid") String email,
			@NotBlank(message = "Department cannot be blank") String department,
			@NotBlank(message = "Course cannot be blank") String course,
			@NotBlank(message = "Participation Type cannot be blank") String participationType, String contestNumber) {
		super();
		this.enrollmentNumber = enrollmentNumber;
		this.facultyNumber = facultyNumber;
		this.fullName = fullName;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.department = department;
		this.course = course;
		this.participationType = participationType;
		this.contestNumber = contestNumber;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public String getFacultyNumber() {
		return facultyNumber;
	}

	public void setFacultyNumber(String facultyNumber) {
		this.facultyNumber = facultyNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getParticipationType() {
		return participationType;
	}

	public void setParticipationType(String participationType) {
		this.participationType = participationType;
	}

	public String getContestNumber() {
		return contestNumber;
	}

	public void setContestNumber(String contestNumber) {
		this.contestNumber = contestNumber;
	}

}
