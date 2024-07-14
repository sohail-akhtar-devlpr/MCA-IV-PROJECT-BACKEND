package com.webapp.codeathon.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//public class SubAdminSignupRequest {
//	
//	@NotBlank(message = "First Name is required")
//	@Size(min = 2, max = 100, message = "First Name should contain atleast 2 characters")
//	private String firstName;
//	
//	private String middleName;
//	
//	private String lastName;
//	
//	
//	@NotBlank(message = "Gender is required")
//	private String gender;
//	
////    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Mobile Number should be valid")	
//	@NotBlank(message = "Mobile Number is required")
//    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Mobile Number")
//	private String mobileNumber;
//	
//	private String designation;
//	
////	@NotBlank(message = "Email is Required")
////	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
////	@Email(message = "Invalid email")
//	@NotBlank(message = "Email is required")
//    @Email(message = "Email is not valid")
//	private String email;
//	
//	
//	@NotBlank(message = "Password is required")
//    @Size(min = 8, max = 40, message = "Password must be atleast 8 characters long and should not exceed 40 characters")
//	private String password;
//	
////	@NotBlank(message = "Re-enter the Password")
////    @Size(min = 8, max = 40, message = "Password must be atleast 8 characters long")
//	private String rePassword;
//	
//	//Default Constructor
//	public SubAdminSignupRequest() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	
//	//Parameterized Constructor
//	public SubAdminSignupRequest(String firstName, String middleName, String lastName, String gender,
//			String mobileNumber, String email, String password,String designation) {
//		super();
//		this.firstName = firstName;
//		this.middleName = middleName;
//		this.lastName = lastName;
//		this.gender = gender;
//		this.mobileNumber = mobileNumber;
//		this.email = email;
//		this.password = password;
//		this.designation=designation;
//	}
//
//
//	//Getters and Setters
//	public String getFirstName() {
//		return firstName;
//	}
//
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//
//	public String getMiddleName() {
//		return middleName;
//	}
//
//
//	public void setMiddleName(String middleName) {
//		this.middleName = middleName;
//	}
//
//
//	public String getLastName() {
//		return lastName;
//	}
//
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//
//	public String getGender() {
//		return gender;
//	}
//
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//
//	public String getMobileNumber() {
//		return mobileNumber;
//	}
//
//
//	public void setMobileNumber(String mobileNumber) {
//		this.mobileNumber = mobileNumber;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	public String getRePassword() {
//		return password;
//	}
//
//
//	public void setRePassword(String rePassword) {
//		this.rePassword = rePassword;
//	}
//	
//	public String getDesignation() {
//		return designation;
//	}
//
//
//	public void setDesignation(String designation) {
//		this.designation = designation;
//	}
//	
//
//}

public class SubAdminSignupRequest {
    
    @NotBlank(message = "First Name is required")
    @Size(min = 2, max = 100, message = "First Name should contain at least 2 characters")
    private String firstName;

    private String middleName;

    private String lastName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Mobile Number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Mobile Number")
    private String mobileNumber;

    private String designation;

//  @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    @Pattern(regexp ="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 40, message = "Password must be at least 8 characters long and should not exceed 40 characters")
    private String password;

    @NotBlank(message = "Re-enter password is required")
    private String rePassword;

    // Default Constructor
    public SubAdminSignupRequest() {
        super();
    }

    // Parameterized Constructor
    public SubAdminSignupRequest(String firstName, String middleName, String lastName, String gender,
                                 String mobileNumber, String email, String password, String rePassword, String designation) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.designation = designation;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}

