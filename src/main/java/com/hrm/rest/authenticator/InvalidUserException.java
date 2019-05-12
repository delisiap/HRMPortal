package com.hrm.rest.authenticator;

public class InvalidUserException extends Exception
{
	private static final long serialVersionUID = 1L;

	public InvalidUserException()
	{
		super("Invalid user id or password");
	}
}