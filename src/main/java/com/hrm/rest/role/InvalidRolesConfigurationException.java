package com.hrm.rest.role;

public class InvalidRolesConfigurationException extends Exception
{
	private static final long serialVersionUID = 1L;

	public InvalidRolesConfigurationException()
	{
		super("Invalid roles configuration.");
	}
}