package com.spring.restful.persons.app.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.spring.restful.persons.app.model.Person;

public class PersonBatchSetter implements BatchPreparedStatementSetter {

	private List<Person> persons;
	

	public PersonBatchSetter(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		ps.setString(1, persons.get(i).getFirstName());
		ps.setString(2, persons.get(i).getLastName());
		ps.setLong(3, persons.get(i).getPhone());
		ps.setString(4, persons.get(i).getEmail());
		ps.setString(5, persons.get(i).getRole());
	}

	@Override
	public int getBatchSize() {
		return persons.size();
	}

}
