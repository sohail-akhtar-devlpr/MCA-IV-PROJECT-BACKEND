package com.webapp.codeathon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class QuestionConstraints {

	//1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int constraintId;

	//2
	private int contraintNumber;

	//3
	private String explanation;

//	private List<String> constraints;

	//4
	@ManyToOne
	@JoinColumn(name = "fk_question_id")
	@JsonBackReference(value = "questions-constraints")
	private Questions questions;

	public QuestionConstraints() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionConstraints(int constraintId, int contraintNumber, String explanation, Questions questions) {
		super();
		this.constraintId = constraintId;
		this.contraintNumber = contraintNumber;
		this.explanation = explanation;
		this.questions = questions;
	}

	public int getConstraintId() {
		return constraintId;
	}

	public void setConstraintId(int constraintId) {
		this.constraintId = constraintId;
	}

	public int getContraintNumber() {
		return contraintNumber;
	}

	public void setContraintNumber(int contraintNumber) {
		this.contraintNumber = contraintNumber;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

}
