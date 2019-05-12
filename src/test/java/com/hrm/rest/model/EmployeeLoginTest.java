package com.hrm.rest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;

import org.junit.Test;

public class EmployeeLoginTest {
	@Test
	public void employeeLoginTestCases() {
		EmployeeLogin employeeLogin = new EmployeeLogin();
		employeeLogin.setEmployeeId("xyz@a.c");
		employeeLogin.setPassword("abc");
		Exception expectedException=null;
		try
		{
			final Field field1 = employeeLogin.getClass().getDeclaredField("employeeId");
			field1.setAccessible(true);
			assertEquals("Fields match test", field1.get(employeeLogin), "xyz@a.c");
	
			final Field field2 = employeeLogin.getClass().getDeclaredField("password");
			field2.setAccessible(true);
			assertEquals("Fields match test", field2.get(employeeLogin), "abc");
		}
		catch(Exception e)
		{
			expectedException =e;
		}
		assertNull(expectedException);
	}
	@Test
	public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException
	{
		Exception expectedException=null;
		try
		{
			EmployeeLogin employeeLogin = new EmployeeLogin();
			final Field field1 = employeeLogin.getClass().getDeclaredField("employeeId");
			field1.setAccessible(true);
			field1.set(employeeLogin, "sample_values");
		
			final String result = employeeLogin.getEmployeeId();
			assertEquals("field retrieval test", result, "sample_values");

			final Field field2 = employeeLogin.getClass().getDeclaredField("password");
			field2.setAccessible(true);
			field2.set(employeeLogin, "abc");

			String result2 = employeeLogin.getPassword();
			assertEquals("field retrieval test", result2,  "abc");
		}
		catch(Exception e)
		{
			expectedException=null;
		}
		assertNull(expectedException);
	}

	@Test
	public void checkForNull()
	{
		Exception expectedException = null;
		try 
		{
			EmployeeLogin employeeLogin = new EmployeeLogin();

			employeeLogin.setEmployeeId("8999");
			employeeLogin.setPassword("abc");

			assertNotNull(employeeLogin);
			assertNotNull(employeeLogin.getEmployeeId());
			assertNotNull(employeeLogin.getPassword());

			EmployeeLogin employeeLogin_null = new EmployeeLogin();

			employeeLogin_null.setEmployeeId(null);
			employeeLogin_null.setPassword(null);

			assertNull(employeeLogin_null.getEmployeeId());
			assertNull(employeeLogin_null.getPassword());
		} 
		catch (Exception e) 
		{
			expectedException=e;
		} 
		assertNull(expectedException);
	}	
}
