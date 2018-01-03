package org.springframework.samples.petclinic.rest;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Question;
import org.springframework.samples.petclinic.rest.dto.QuestionDTO;
import org.springframework.samples.petclinic.rest.dtoconvert.QuestionDTOConvert;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/questions")
public class QuestionRestController {
	
	@Autowired
	private ClinicService clinicService;
	
	@RequestMapping(value = "/{questionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Question> getQuestion(@PathVariable("questionId") int questionId) {
		Question question = null;
		question = this.clinicService.findQuestionById(questionId);
		if (question == null) {
			return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
		}
		System.out.println(question);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{questionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") int questionId) {
		Question question = this.clinicService.findQuestionById(questionId);
		if (question == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.clinicService.deleteQuestion(question);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Question> addQuestion(@RequestBody @Valid QuestionDTO questionDTO, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (questionDTO == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Question>(headers, HttpStatus.BAD_REQUEST);
		}
		QuestionDTOConvert questionDTOConvert = new QuestionDTOConvert();
		Question question = questionDTOConvert.questionDTOToQuestion(questionDTO);
		this.clinicService.saveQuestion(question);
		headers.setLocation(ucBuilder.path("/api/questions/{id}").buildAndExpand(question.getId()).toUri());
		return new ResponseEntity<Question>(question, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{questionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Question> updateQuestion(@PathVariable("questionId") int questionId, @RequestBody @Valid Question question,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (question == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<Question>(headers, HttpStatus.BAD_REQUEST);
		}
		Question currentQuestion = this.clinicService.findQuestionById(questionId);
		if (currentQuestion == null) {
			return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
		}
		currentQuestion.setQuestionString(question.getQuestionString());
		currentQuestion.setTestCases(question.getTestCases());
		this.clinicService.saveQuestion(currentQuestion);
		return new ResponseEntity<Question>(currentQuestion, HttpStatus.NO_CONTENT);
	}





}
