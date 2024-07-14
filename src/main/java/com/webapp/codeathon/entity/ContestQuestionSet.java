package com.webapp.codeathon.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ContestQuestionSet {

	//1
	@Id
	@Column(name = "questionset_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionSet;

	//2
	@Column(unique = true)
	private String contestNumber;

	//3
	@OneToMany(mappedBy = "contestQuestionSet", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "contest-questions")
	private List<Questions> questions;

	//4
	@OneToOne
	@JoinColumn(name = "fk_contest_id")
	@JsonBackReference(value = "contest-question-set")
	private Contest contest;

	public ContestQuestionSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContestQuestionSet(int questionSet, String contestNumber, List<Questions> questions, Contest contest) {
		super();
		this.questionSet = questionSet;
		this.contestNumber = contestNumber;
		this.questions = questions;
		this.contest = contest;
	}

	public int getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(int questionSet) {
		this.questionSet = questionSet;
	}

	public String getContestNumber() {
		return contestNumber;
	}

	public void setContestNumber(String contestNumber) {
		this.contestNumber = contestNumber;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public Contest getContest() {
		return contest;
	}

	public void setContest(Contest contest) {
		this.contest = contest;
	}


}
