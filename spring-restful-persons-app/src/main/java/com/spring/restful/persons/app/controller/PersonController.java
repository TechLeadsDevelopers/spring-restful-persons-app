package com.spring.restful.persons.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restful.persons.app.common.UserApiURI;
import com.spring.restful.persons.app.model.Person;
import com.spring.restful.persons.app.service.PersonService;
@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = UserApiURI.PERSON_API_ROOT, method = RequestMethod.POST)
	public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
		person=personService.createPerson(person);
				return new ResponseEntity<Person>(person, HttpStatus.CREATED);
		
	}

	@RequestMapping(value = UserApiURI.PERSON_API_ROOT, method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllUser() {
		List<Person> listUsers = personService.getAllPersons();
			return new ResponseEntity<List<Person>>(listUsers, HttpStatus.OK);
	}
	
	@RequestMapping(value=UserApiURI.PERSON_API_URI_BY_ID,method=RequestMethod.GET)
	public ResponseEntity<Person> getUser(@PathVariable("id") String id){
		Person person=personService.getPersonById(Long.valueOf(id));
				return new ResponseEntity<Person>(person,HttpStatus.OK);
	}
	@RequestMapping(value=UserApiURI.PERSON_API_URI_BY_ID,method=RequestMethod.PUT)
	public ResponseEntity<Person> updateUser(@RequestBody Person person, @PathVariable("id") String id){
		
				person=personService.getPersonById(Long.valueOf(id));
				return new ResponseEntity<Person>(person,HttpStatus.OK);
	}
	
	@RequestMapping(value=UserApiURI.PERSON_API_URI_BY_ID, method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
			Person person=personService.deletePersonById(Long.valueOf(id));
				return new ResponseEntity<String>("User is deleted",HttpStatus.GONE);
	}
}
