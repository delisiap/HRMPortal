package com.hrm.rest.employee;

public class InvalidUserRoleException extends Exception
{
	private static final long serialVersionUID = 1L;

	InvalidUserRoleException()
	{
		super("The user has an invalid role id.");
	}
}