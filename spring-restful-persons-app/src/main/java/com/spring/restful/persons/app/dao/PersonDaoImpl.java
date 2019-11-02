package com.spring.restful.persons.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.restful.persons.app.common.DbQueries;
import com.spring.restful.persons.app.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public int createPerson(Person person) {
		Object[] params = { person.getFirstName(), person.getLastName(), person.getPhone(), person.getEmail(),
				person.getRole() };
		int count = jdbcTemplate.update(DbQueries.INSERT_PERSON, params);
		return count;
	}

	@Override
	public Person getPersonById(Long id) {
		Object[] params = { id };
		Person person = jdbcTemplate.queryForObject(DbQueries.SELECT_PERSON_BY_ID,
				new BeanPropertyRowMapper<Person>(Person.class), params);
		return person;
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> listPersons = jdbcTemplate.query(DbQueries.SELECT_ALL_PERSON,
				new BeanPropertyRowMapper<Person>(Person.class));
		return listPersons;
	}

	@Override
	public int updatePerson(Person person, Long id) {
		Object[] params = { person.getFirstName(), person.getLastName(), person.getPhone(), person.getEmail(),
				person.getRole(), id };
		int count = jdbcTemplate.update(DbQueries.UPDATE_PERSON, params);
		return count;
	}

	@Override
	public int deletePerson(Long id) {
		int count = jdbcTemplate.update(DbQueries.DELETE_PERSON, new Object[] { id });
		return count;
	}

	@Override
	public int[] createPersonBatch(List<Person> persons) {
		int count[]=jdbcTemplate.batchUpdate(DbQueries.INSERT_PERSON, new PersonBatchSetter(persons));
		return null;
	}

}
