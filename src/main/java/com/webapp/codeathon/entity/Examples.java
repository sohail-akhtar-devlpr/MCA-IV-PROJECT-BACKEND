package com.webapp.codeathon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Examples {

	//1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int exampleId;

	//2
	private String exampleNumber;

	//3
	private String input;

	//4
	private String output;

	//5
	private String explanation;

	//6
	@ManyToOne
	@JoinColumn(name = "fk_question_id")
	@JsonBackReference(value = "questions-example")
	private Questions questions;

//	@OneToMany(mappedBy = "examples", cascade = CascadeType.ALL)
//	private List<ExampleDetais> exampleDetails;

	public Examples() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Examples(int exampleId, String exampleNumber, String input, String output, String explanation,
		Questions questions) {
	super();
	this.exampleId = exampleId;
	this.exampleNumber = exampleNumber;
	this.input = input;
	this.output = output;
	this.explanation = explanation;
	this.questions = questions;
}



	public int getExampleId() {
		return exampleId;
	}

	public void setExampleId(int exampleId) {
		this.exampleId = exampleId;
	}

	public String getExampleNumber() {
		return exampleNumber;
	}

	public void setExampleNumber(String exampleNumber) {
		this.exampleNumber = exampleNumber;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
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

//	public List<ExampleDetais> getExampleDetails() {
//		return exampleDetails;
//	}
//
//	public void setExampleDetails(List<ExampleDetais> exampleDetails) {
//		this.exampleDetails = exampleDetails;
//	}

}
