package com.webapp.codeathon.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private String error;
	private String details;
	private LocalDateTime timeStamp;

	// Parameterized Consructor
	public ErrorDetails(String error, String details, LocalDateTime timeStamp) {
		super();
		this.error = error;
		this.details = details;
		this.timeStamp = timeStamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
