package com.webapp.codeathon.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Questions {

	//1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;

	//2
	private String questionNumber; 

	//3
	private String questionTitle;

	//4
	private String questionDescription;

	//5
	private String questionImage;

	//6
	@OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "questions-example")
	private List<Examples> examples;

	//7
	@OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "questions-constraints")
	private List<QuestionConstraints> constraints;

	//8
	@ManyToOne
	@JoinColumn(name = "fk_questionset_id")
	@JsonBackReference(value = "contest-questions")
	private ContestQuestionSet contestQuestionSet;

	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Questions(int questionId, String questionNumber, String questionTitle, String questionDescription,
			String questionImage, List<Examples> examples, List<QuestionConstraints> constraints,
			ContestQuestionSet contestQuestionSet) {
		super();
		this.questionId = questionId;
		this.questionNumber = questionNumber;
		this.questionTitle = questionTitle;
		this.questionDescription = questionDescription;
		this.questionImage = questionImage;
		this.examples = examples;
		this.constraints = constraints;
		this.contestQuestionSet = contestQuestionSet;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(String questionImage) {
		this.questionImage = questionImage;
	}

	public List<Examples> getExamples() {
		return examples;
	}

	public void setExamples(List<Examples> examples) {
		this.examples = examples;
	}

	public List<QuestionConstraints> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<QuestionConstraints> constraints) {
		this.constraints = constraints;
	}

	public ContestQuestionSet getContestQuestionSet() {
		return contestQuestionSet;
	}

	public void setContestQuestionSet(ContestQuestionSet contestQuestionSet) {
		this.contestQuestionSet = contestQuestionSet;
	}

}
