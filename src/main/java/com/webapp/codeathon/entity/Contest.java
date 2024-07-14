package com.webapp.codeathon.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contest {
	// 1
	@Id
	@Column(name = "contest_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contestId;

	// 2
	@Column(unique = true)
	@NotBlank(message = "Contest number is mandatory")
	private String contestNumber;

	// 3
	@Column(name = "type")
	@NotBlank(message = "Contest type is mandatory")
	private String contestType;

	// 4
	@NotBlank(message = "Contest name is mandatory")
	private String contestName;

	// 5
	@Column(name = "date")
	private LocalDate contestDate;

	// 6
	@Column(name = "time")
	private LocalTime contesTime;

	// 7
	@Column(name = "venue")
	private String contestVenue;

	// 8
	private String status;

	// 9
	@OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "contest-organizer")
	private List<Organizer> contestOrganizer = new ArrayList<>();;

	// 10
	@OneToOne(mappedBy = "contest", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "contest-question-set")
	private ContestQuestionSet contestQuestionSet;

	// 11
	@OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "contest-prize")
	private List<Prize> prize;

	// 12
	@OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "contest-winners")
	private List<Winners> contestWinners;

	// 13
	@OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "contest-contestant")
	private List<Contestant> contestant;

	public Contest() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized Constructor
	public Contest(int contestId, String contestNumber, String contestType, String contestName, LocalDate contestDate,
			LocalTime contesTime, String contestVenue, String status, List<Organizer> contestOrganizer,
			ContestQuestionSet contestQuestionSet, List<Prize> prize, List<Winners> contestWinners,
			List<Contestant> contestant) {
		super();
		this.contestId = contestId;
		this.contestNumber = contestNumber;
		this.contestType = contestType;
		this.contestName = contestName;
		this.contestDate = contestDate;
		this.contesTime = contesTime;
		this.contestVenue = contestVenue;
		this.status = status;
		this.contestOrganizer = contestOrganizer;
		this.contestQuestionSet = contestQuestionSet;
		this.prize = prize;
		this.contestWinners = contestWinners;
		this.contestant = contestant;
	}

	// Getters and Setters
	public int getContestId() {
		return contestId;
	}

	public void setContestId(int contestId) {
		this.contestId = contestId;
	}

	public String getContestNumber() {
		return contestNumber;
	}

	public void setContestNumber(String contestNumber) {
		this.contestNumber = contestNumber;
	}

	public String getContestType() {
		return contestType;
	}

	public void setContestType(String contestType) {
		this.contestType = contestType;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}

	public LocalDate getContestDate() {
		return contestDate;
	}

	public void setContestDate(LocalDate contestDate) {
		this.contestDate = contestDate;
	}

	public LocalTime getContesTime() {
		return contesTime;
	}

	public void setContesTime(LocalTime contesTime) {
		this.contesTime = contesTime;
	}

	public String getContestVenue() {
		return contestVenue;
	}

	public void setContestVenue(String contestVenue) {
		this.contestVenue = contestVenue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Organizer> getContestOrganizer() {
		return contestOrganizer;
	}

	public void setContestOrganizer(List<Organizer> contestOrganizer) {
		this.contestOrganizer = contestOrganizer;
	}

	public ContestQuestionSet getContestQuestionSet() {
		return contestQuestionSet;
	}

	public void setContestQuestionSet(ContestQuestionSet contestQuestionSet) {
		this.contestQuestionSet = contestQuestionSet;
	}

	public List<Prize> getPrize() {
		return prize;
	}

	public void setPrize(List<Prize> prize) {
		this.prize = prize;
	}

	public List<Winners> getContestWinners() {
		return contestWinners;
	}

	public void setContestWinners(List<Winners> contestWinners) {
		this.contestWinners = contestWinners;
	}

	public List<Contestant> getContestant() {
		return contestant;
	}

	public void setContestant(List<Contestant> contestant) {
		this.contestant = contestant;
	}

}