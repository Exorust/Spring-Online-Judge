package org.springframework.samples.petclinic.rest.dto;

public class TestCaseDTO {


    @Override
	public String toString() {
		return "TestCaseDTO [input=" + input + ", output=" + output + ", question_id=" + question_id + "]";
	}

	private String input;
  
    private String output;
    
    private int question_id;

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

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
}
