package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
	
    @Column(name = "question_string")
    @NotEmpty
	private String questionString;
	
    @OneToMany(
            mappedBy = "question", 
            cascade = CascadeType.ALL, 
            orphanRemoval = true
        )
	private List<TestCase> testCases;

	@Override
	public String toString() {
		return "Question [questionString=" + questionString + ", testCases=" + testCases + "]";
	}

	public String getQuestionString() {
		return questionString;
	}

	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}


	
}
