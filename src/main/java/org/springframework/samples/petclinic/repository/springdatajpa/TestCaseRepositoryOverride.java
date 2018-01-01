package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.repository.TestCaseRepository;
import org.springframework.samples.petclinic.model.TestCase;


@Profile("spring-data-jpa")
public interface TestCaseRepositoryOverride extends TestCaseRepository, Repository<TestCase, Integer>{
	
}
