package com.hrm.rest.employee;

public class InvalidRoleNameException extends Exception
{
	private static final long serialVersionUID = 1L;

	InvalidRoleNameException()
	{
		super("Invalid role name provided.");
	}
}