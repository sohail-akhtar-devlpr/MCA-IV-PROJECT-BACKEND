package com.webapp.codeathon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class ProfilePicture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fileName;
	
	private String username;
	
	private String type;
	
	@Lob
	@Column(name = "profilePicture", columnDefinition = "LONGBLOB")
	private byte[] imageData;
	
	//Default Constructor
	public ProfilePicture() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public ProfilePicture(int id, String fileName, String username, String type, byte[] imageData) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.username = username;
		this.type = type;
		this.imageData = imageData;
	}


	//Getters and setters
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
