package com.hrm.rest.authenticator;

public enum ValidationErrorMessages
{
	SUCESS("success"),
	FAIL("fail");

	private String errorMsg;
	ValidationErrorMessages(String error)
	{
		errorMsg=error;
	}

	public String getErrorMessage()
	{
		return errorMsg;
	}

	public static ValidationErrorMessages getErrorMessages(final String error)
	{
		for (ValidationErrorMessages errormessage : ValidationErrorMessages.values())
		{
			if (error.equals(errormessage.toString()))
			{
				return errormessage;
			}
		}
		return null;
	}

	@Override
	public String toString()
	{
		return errorMsg;
	}
}
