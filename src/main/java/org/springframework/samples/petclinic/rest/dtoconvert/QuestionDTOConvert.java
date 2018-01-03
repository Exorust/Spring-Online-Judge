package org.springframework.samples.petclinic.rest.dtoconvert;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Question;
import org.springframework.samples.petclinic.model.TestCase;
import org.springframework.samples.petclinic.rest.dto.QuestionDTO;
import org.springframework.samples.petclinic.service.ClinicService;

public class QuestionDTOConvert {
	
	@Autowired
	ClinicService clinicService;
	
	public Question questionDTOToQuestion(QuestionDTO questionDTO) {
		Question question = new Question();
		question.setQuestionString(questionDTO.getQuestionString());
		List<TestCase> testCaseList = new ArrayList<TestCase>();
		List<Integer> testCaseIds = questionDTO.getTestCaseIds();
		for(int index = 0; index< questionDTO.getTestCaseIdsSize(); index++ ) {
			testCaseList.add(clinicService.findTestCaseById(testCaseIds.get(index)));
		}
		question.setTestCases(testCaseList);
		return question;
	}
	
}
