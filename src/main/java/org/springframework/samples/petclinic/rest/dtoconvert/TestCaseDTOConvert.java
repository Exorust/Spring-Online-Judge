package org.springframework.samples.petclinic.rest.dtoconvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Question;
import org.springframework.samples.petclinic.model.TestCase;
import org.springframework.samples.petclinic.rest.dto.TestCaseDTO;
import org.springframework.samples.petclinic.service.ClinicService;

public class TestCaseDTOConvert {

	@Autowired
	ClinicService clinicService;
	
	public TestCase testCaseDTOToTestCase(TestCaseDTO testCaseDTO) {
		TestCase testCase = new TestCase();
		
		testCase.setInput(testCaseDTO.getInput());
		testCase.setOutput(testCaseDTO.getOutput());
		
		Question question = clinicService.findQuestionById(testCaseDTO.getQuestion_id());
		testCase.setQuestion(question);
		return testCase;
	}
	
}
