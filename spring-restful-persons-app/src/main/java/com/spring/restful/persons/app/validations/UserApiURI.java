package com.spring.restful.persons.app.validations;

public interface UserApiURI {
	String PERSON_API_ROOT = "/persons";
	String PERSON_API_URI_BY_ID = PERSON_API_ROOT + "/" + "{id}";
	String GET_USER_BY_NAME_ID = PERSON_API_ROOT + "/" + "{name}/{id}";

}
