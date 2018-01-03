package org.springframework.samples.petclinic.rest.dto;

import java.util.List;

public class QuestionDTO {
	
	private String questionString;
	
	private List<Integer> testCaseIds;
	
	public int getTestCaseIdsSize() {
		return testCaseIds.size(); 
	}

	public String getQuestionString() {
		return questionString;
	}

	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}

	public List<Integer> getTestCaseIds() {
		return testCaseIds;
	}

	public void setTestCaseIds(List<Integer> testCaseIds) {
		this.testCaseIds = testCaseIds;
	}

	@Override
	public String toString() {
		return "QuestionDTO [questionString=" + questionString + ", testCaseIds=" + testCaseIds + "]";
	}
	
	
}
