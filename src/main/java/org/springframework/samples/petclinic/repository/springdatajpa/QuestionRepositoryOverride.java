package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.samples.petclinic.model.Question;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.repository.QuestionRepository;


@Profile("spring-data-jpa")
public interface QuestionRepositoryOverride extends QuestionRepository, Repository<Question, Integer>{


}
