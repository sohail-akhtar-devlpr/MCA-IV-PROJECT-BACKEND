package com.webapp.codeathon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Organizer {

	//1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int organizerId;

	//2
	private String organizerName;

	//3
	private String organizerType;

	//4
	private String contactIndividual;

	//5
	private String contestNumber;

	@ManyToOne
	@JoinColumn(name = "fk_contest_id")
	@JsonBackReference(value = "contest-organizer")
	private Contest contest;

	public Organizer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Organizer(int organizerId, String organizerName, String organizerType, String contactIndividual,
			String contestNumber, Contest contest) {
		super();
		this.organizerId = organizerId;
		this.organizerName = organizerName;
		this.organizerType = organizerType;
		this.contactIndividual = contactIndividual;
		this.contestNumber = contestNumber;
		this.contest = contest;
	}

	public int getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(int organizerId) {
		this.organizerId = organizerId;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getOrganizerType() {
		return organizerType;
	}

	public void setOrganizerType(String organizerType) {
		this.organizerType = organizerType;
	}

	public String getContactIndividual() {
		return contactIndividual;
	}

	public void setContactIndividual(String contactIndividual) {
		this.contactIndividual = contactIndividual;
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

	@Override
	public String toString() {
		return "Organizer [organizerId=" + organizerId + ", organizerName=" + organizerName + ", organizerType="
				+ organizerType + ", contactIndividual=" + contactIndividual + ", contestNumber=" + contestNumber
				+ ", contest=" + contest + "]";
	}

	
}
