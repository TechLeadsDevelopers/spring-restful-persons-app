package com.spring.restful.persons.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restful.persons.app.dao.PersonDao;
import com.spring.restful.persons.app.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	

	@Override
	public Person createPerson(Person person) {
		int count = personDao.createPerson(person);
		return person;
	}

	@Override
	public Person getPersonById(Long id) {
		Person person = personDao.getPersonById(id);
		return person;
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> listPersons = personDao.getAllPersons();
		return listPersons;
	}

	@Override
	public Person updatePerson(Person person, Long id) {
		int count = personDao.updatePerson(person, id);
		return person;
	}

	@Override
	public Person deletePersonById(Long id) {
		int count = personDao.deletePerson(id);
		Person person = new Person();
		person.setId(id);
		return person;
	}

}
