package com.spring.restful.persons.app.dao;

import java.util.List;

import com.spring.restful.persons.app.model.Person;

public interface PersonDao {
	public Person getPersonById(Long id);
	public List<Person> getAllPersons();
	public int createPerson(Person person);
	public int[] createPersonBatch(List<Person> persons);
	public int deletePerson(Long id);
	public int updatePerson(Person person, Long id);

}
