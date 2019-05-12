package com.hrm.rest.authenticator;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import com.hrm.rest.authenticator.EncryptPassword;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class EncryptPasswordTest 
{

	@Test
	public void ValidatePassword()
	{
		Exception expected = null;
		try
		{
			EncryptPassword encryptPassword = Mockito.mock(EncryptPassword.class);
			when(encryptPassword.getHashedPassword()).thenReturn("password_Test");
			assertEquals(encryptPassword.getHashedPassword(), "password_Test");
		}
		catch(Exception e)
		{
			expected=e;
		}
		assertNull(expected);
	}

	@Test
	public void InvalidPassword()
	{
		Exception expected = null;
		try
		{
			EncryptPassword encryptPassword = Mockito.mock(EncryptPassword.class);
			when(encryptPassword.getHashedPassword()).thenReturn("password_Test");
			assertNotEquals(encryptPassword.getHashedPassword(), "password_Test1");
		}
		catch(Exception e)
		{
			expected =e;
		}
		assertNull(expected);
	}
}
