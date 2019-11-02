package com.spring.restful.persons.app.common;

public interface DbQueries {
	String INSERT_PERSON="INSERT INTO PERSON(ID, FIRSTNAME, LASTNAME, PHONE, EMAIL, ROLE, CREATEDDATE, LASTMODIFIED) VALUES "
			+ "(PER_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
	String SELECT_PERSON_BY_ID="SELECT ID, FIRSTNAME, LASTNAME, PHONE, EMAIL, ROLE, "
			
			+ "CREATEDDATE, LASTMODIFIED FROM PERSON WHERE ID=?";
	String SELECT_ALL_PERSON="SELECT ID, FIRSTNAME, LASTNAME, PHONE, EMAIL, ROLE,"
			+ " CREATEDDATE, LASTMODIFIED FROM PERSON ORDER BY ID";
	String DELETE_PERSON="DELETE FROM PERSON WHERE ID = ?";
	String UPDATE_PERSON="UPDATE PERSON SET FIRSTNAME =?,LASTNAME =?, PHONE =?,EMAIL =?,ROLE=?, "
			+ "LASTMODIFIED = CURRENT_TIMESTAMP WHERE ID =?";
}