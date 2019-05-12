package com.hrm.rest.authenticator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import com.hrm.rest.authenticator.UserValidate;
import org.junit.Test;

public class UserValidateTest{

	HashMap<String,String> resultstatus = new HashMap<>();

	@Test
	public void UserValidateEmployeeID()
	{
		assertTrue(new UserValidate("empID","pswd").validateempid(resultstatus));
		assertFalse(new UserValidate("", "pswd").validateempid(resultstatus));
		assertFalse(new UserValidate(null, "pswd").validateempid(resultstatus));
	}

	@Test
	public void UserValidateEmpPassword() 
	{	
		assertTrue(new UserValidate("empID","pswd").validateemppassword(resultstatus));
		assertFalse(new UserValidate("empID", "").validateemppassword(resultstatus));
		assertFalse(new UserValidate("empID", null).validateemppassword(resultstatus));			
	}

	@Test
	public void UserValidateEmployee()
	{
		HashMap<String,String> results = new HashMap<>();
		Exception expectedException =null;
		try
		{
			results = new UserValidate("empID","pswd").validateUser();
			assertEquals(results.get("IsValid"),"true");
			
			results = new UserValidate("empID","").validateUser();
			assertEquals(results.get("IsValid"),"false");

			results = new UserValidate("","pswd").validateUser();
			assertEquals(results.get("IsValid"),"false");

			results = new UserValidate("empID",null).validateUser();
			assertEquals(results.get("IsValid"),"false");

			results = new UserValidate(null,"pswd").validateUser();
			assertEquals(results.get("IsValid"),"false");

			results = new UserValidate("","").validateUser();
			assertEquals(results.get("IsValid"),"false");

			results = new UserValidate(null,null).validateUser();
			assertEquals(results.get("IsValid"),"false");
		}
		catch(Exception e)
		{
			expectedException =e;
		}

		assertNull(expectedException);
	}

	@Test
	public void addErrorToResultSetTest()
	{
		HashMap<String,String> resultstatus = new HashMap<>();
		resultstatus.put("status", "success");
		resultstatus.put("error", "Exception Caught");
		UserValidate userValidate = new UserValidate("empId", "password");

		userValidate.addErrorToResultSet("errorMsg",resultstatus) ;
		assertEquals(resultstatus.get("status"), "fail");
		assertEquals(resultstatus.get("error"), "Exception Caught");

		HashMap<String,String> resultstatus2 = new HashMap<>();
		resultstatus2.put("status", "fail");
		resultstatus2.put("error", "Exception Caught2");
		UserValidate userValidate2 = new UserValidate("empId", "password");

		userValidate2.addErrorToResultSet("errorMsg",resultstatus) ;
		assertEquals(resultstatus2.get("status"), "fail");
		assertEquals(resultstatus2.get("error"), "Exception Caught2");
	}
}