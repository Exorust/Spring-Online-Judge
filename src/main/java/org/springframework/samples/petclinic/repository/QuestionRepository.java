package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Question;

public interface QuestionRepository {
	Question findById(int id) throws DataAccessException;
	
	void save(Question question) throws DataAccessException;
	
	Collection<Question> findAll() throws DataAccessException;

	void delete(Question question) throws DataAccessException;
}
