package com.subrat.rest.demo.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From MethodArgumentNotValid Exception in GEH", methodArgumentNotValidException.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);

	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupported Exception in GEH - Method not allowed",
				httpRequestMethodNotSupportedException.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);

	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(
			UserNameNotFoundException userNameNotFoundException, WebRequest webRequest) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				userNameNotFoundException.getMessage(),webRequest.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(
			ConstraintViolationException constraintViolationException, WebRequest webRequest) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				constraintViolationException.getMessage(),webRequest.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
}
