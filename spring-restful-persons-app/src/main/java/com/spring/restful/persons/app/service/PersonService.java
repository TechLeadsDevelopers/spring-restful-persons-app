package com.spring.restful.persons.app.service;

import java.util.List;

import com.spring.restful.persons.app.model.Person;


public interface PersonService {
	public Person getPersonById(Long id);
	public List<Person> getAllPersons();
	public Person createPerson(Person Person);
	public Person updatePerson(Person Person,Long id);
	public Person deletePersonById(Long id);
}
