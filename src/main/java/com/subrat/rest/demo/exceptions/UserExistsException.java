package com.subrat.rest.demo.exceptions;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserExistsException(String message) {
		super(message);
	}

}
