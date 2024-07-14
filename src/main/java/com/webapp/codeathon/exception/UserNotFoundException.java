package com.webapp.codeathon.exception;

public class UserNotFoundException extends RuntimeException {
	private String errorMessage;
	private String user;

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String errorMessage, String user) {
		super();
		this.errorMessage = errorMessage;
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
