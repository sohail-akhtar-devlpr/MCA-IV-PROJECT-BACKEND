package com.webapp.codeathon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//@Entity
public class ExampleDetais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String exampleId;

	private String input;

	private String output;

	private String explanation;

	@ManyToOne
	@JoinColumn(name = "fk_example_id")
	private Examples examples;

	public ExampleDetais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExampleDetais(String exampleId, String input, String output, String explanation, Examples examples) {
		super();
		this.exampleId = exampleId;
		this.input = input;
		this.output = output;
		this.explanation = explanation;
		this.examples = examples;
	}

	public String getExampleId() {
		return exampleId;
	}

	public void setExampleId(String exampleId) {
		this.exampleId = exampleId;
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

	public Examples getExamples() {
		return examples;
	}

	public void setExamples(Examples examples) {
		this.examples = examples;
	}

}
