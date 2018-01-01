package org.springframework.samples.petclinic.rest;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.TestCase;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseRestController {
	
	@Autowired
	private ClinicService clinicService;
	
	@RequestMapping(value = "/input/{input}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<TestCase>> findByInput(@PathVariable("input") String input) {
		if (input == null) {
			input = "";
		}
		Collection<TestCase> testcases = this.clinicService.findTestCaseByInput(input);
		if (testcases.isEmpty()) {
			return new ResponseEntity<Collection<TestCase>>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Collection<TestCase>>(testcases,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/*/output/{output}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<TestCase>> findByOutput(@PathVariable("output") String output) {
		if (output == null) {
			output = "";
		}
		Collection<TestCase> outputs = this.clinicService.findTestCaseByOutput(output);
		if (outputs.isEmpty()) {
			return new ResponseEntity<Collection<TestCase>>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Collection<TestCase>>(outputs,HttpStatus.OK);
		}	
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TestCase> addTestCase(@RequestBody @Valid TestCase testCase, BindingResult bindingResult,
			UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (testCase == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<TestCase>(headers, HttpStatus.BAD_REQUEST);
		}
		this.clinicService.saveTestCase(testCase);
		headers.setLocation(ucBuilder.path("/api/testcases/{id}").buildAndExpand(testCase.getId()).toUri());
		return new ResponseEntity<TestCase>(testCase, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{testCaseId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> deleteTestCase(@PathVariable("testCaseId") int testCaseId) {
		TestCase testCase = this.clinicService.findTestCaseById(testCaseId);
		if (testCase == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		this.clinicService.deleteTestCase(testCase);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{testCaseId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TestCase> updateTestCase(@PathVariable("testCaseId") int testCaseId, @RequestBody @Valid TestCase testCase,
			BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
		BindingErrorsResponse errors = new BindingErrorsResponse();
		HttpHeaders headers = new HttpHeaders();
		if (bindingResult.hasErrors() || (testCase == null)) {
			errors.addAllErrors(bindingResult);
			headers.add("errors", errors.toJSON());
			return new ResponseEntity<TestCase>(headers, HttpStatus.BAD_REQUEST);
		}
		TestCase currentTestCase = this.clinicService.findTestCaseById(testCaseId);
		if (currentTestCase == null) {
			return new ResponseEntity<TestCase>(HttpStatus.NOT_FOUND);
		}
		currentTestCase.setInput(testCase.getInput());
		currentTestCase.setOutput(testCase.getOutput());

		this.clinicService.saveTestCase(currentTestCase);
		return new ResponseEntity<TestCase>(currentTestCase, HttpStatus.NO_CONTENT);
	}
}


