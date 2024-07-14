package com.webapp.codeathon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Contestant {

	@Id
	private String enrollmentNumber;

	private String fullName;

	private String department;

	private String course;

	private String facultyNumber;

	private String gender;

	private String participationType;

	private String isWon = "false";

	private String contestNumber;

	@ManyToOne
	@JoinColumn(name = "fk_contest_id")
	@JsonBackReference(value = "contest-contestant")
	private Contest contest;

//	@OneToOne(mappedBy = "contestant", cascade = CascadeType.ALL)
//	@JsonManagedReference(value = "contestant-winner")
//	private Winners winner;

	public Contestant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contestant(String enrollmentNumber, String fullName, String department, String course, String facultyNumber,
			String gender, String participationType, String isWon, String contestNumber, Contest contest) {
		super();
		this.enrollmentNumber = enrollmentNumber;
		this.fullName = fullName;
		this.department = department;
		this.course = course;
		this.facultyNumber = facultyNumber;
		this.gender = gender;
		this.participationType = participationType;
		this.isWon = isWon;
		this.contestNumber = contestNumber;
		this.contest = contest;
	}

	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}

	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getFacultyNumber() {
		return facultyNumber;
	}

	public void setFacultyNumber(String facultyNumber) {
		this.facultyNumber = facultyNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getParticipationType() {
		return participationType;
	}

	public void setParticipationType(String participationType) {
		this.participationType = participationType;
	}

	public String getIsWon() {
		return isWon;
	}

	public void setIsWon(String isWon) {
		this.isWon = isWon;
	}

	public String getContestNumber() {
		return contestNumber;
	}

	public void setContestNumber(String contestNumber) {
		this.contestNumber = contestNumber;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}

}
