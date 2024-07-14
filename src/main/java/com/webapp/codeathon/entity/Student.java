package com.webapp.codeathon.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rollNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @JsonManagedReference
    private List<Address> addresses;

    // Constructors, getters, and setters

    public Student() {
    }

    public Student(String name, String rollNumber, List<Address> addresses) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.addresses = addresses;
    }

    // Getters and setters
    // ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Roll number
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    // Addresses
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
