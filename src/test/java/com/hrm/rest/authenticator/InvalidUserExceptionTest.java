package com.hrm.rest.authenticator;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class InvalidUserExceptionTest{

	@Test
	public void ExceptionTest()
	{
		Exception invalidUserException =new InvalidUserException();
		try
		{
			assertNotNull(invalidUserException);
		}
		catch(Exception e)
		{
			assertNotNull(invalidUserException);;
		}
	}
}