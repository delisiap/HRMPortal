package com.hrm.rest.employee;

public class InvalidRoleIdException extends Exception
{
	private static final long serialVersionUID = 1L;

	InvalidRoleIdException()
	{
		super("Invalid role id provided.");
	}
}