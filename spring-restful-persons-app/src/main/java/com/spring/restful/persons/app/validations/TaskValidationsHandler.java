package com.spring.restful.persons.app.validations;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TaskValidationsHandler {

	private MessageSource messageSource;

	@Autowired
	public TaskValidationsHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	
	public @ResponseBody List<ApiError> handleInvalidNumbers(NumberFormatException ex) {
		ValidationErrors errors = new ValidationErrors();
		errors.addFieldError("Id", "invalid number");
		ApiError apiError = new ApiError("400-123", "invalid input", errors);
		return Arrays.asList(apiError);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public List<ApiError> handleEmptyResultSet(EmptyResultDataAccessException ex) {
		ValidationErrors errors = new ValidationErrors();
		errors.addFieldError("id", "Person not found");
		ApiError apiError = new ApiError("404-123", "Empty Result", errors);
		return Arrays.asList(apiError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrors processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	private ValidationErrors processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrors validationErrors = new ValidationErrors();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			validationErrors.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return validationErrors;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}
		return localizedErrorMessage;
	}
}
