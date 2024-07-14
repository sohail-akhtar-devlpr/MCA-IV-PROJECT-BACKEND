package com.webapp.codeathon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String state;
    private String country;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    // Constructors, getters, and setters

    public Address() {
    }

    public Address(String street, String state, String country, Student student) {
        this.street = street;
        this.state = state;
        this.country = country;
        this.student = student;
    }

    // Getters and setters
    // ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Street
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    // State
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Student
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", state=" + state + ", country=" + country + ", student="
				+ student + "]";
	}
    
    
}
