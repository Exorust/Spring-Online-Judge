package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.TestCase;

public interface TestCaseRepository {
	Collection<TestCase> findByInput(String input) throws DataAccessException;
	
	Collection<TestCase> findByOutput(String output) throws DataAccessException;
	
	TestCase findById(int id) throws DataAccessException;
	
    void save(TestCase testCase ) throws DataAccessException;
    
    void delete(TestCase testCase) throws DataAccessException;
}
