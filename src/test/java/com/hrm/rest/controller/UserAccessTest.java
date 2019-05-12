package com.hrm.rest.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.HRMContext;
import com.hrm.rest.authenticator.Authenticator;
import com.hrm.rest.authenticator.EncryptPassword;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLoginAccessor;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.utility.Constants;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class UserAccessTest{

	@Mock
	private SessionController sessionController;
	
	@Mock

	DatabaseAccessorFactory databaseAccessorFactory;

	@Mock
	ModelFactory modelFactory;

	@Mock
	Logger logger;

	@Mock
	EmployeeLogin employeeLogin;

	@Mock
	EmployeeLoginAccessor employeeLoginAccessor;

	@Mock
	Authenticator authenticator;

	@Mock
	Response response;
	
	@Mock
	EncryptPassword encryptPassword;

	@Test
	@PrepareForTest({HRMContext.class,DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void AuthenticatorInValidPassword() throws Exception
	{
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
	
		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin)).thenReturn(employeeLoginAccessor);

		
		Exception ex = null;
		try
		{
			UserAccess userAccess = new UserAccess();
			Map<String,Object> results = new HashMap<String,Object>();
			results = userAccess.login("admin", "admin");	
			String response = (String)results.get(Constants.RESPONSE_STATUS);
			assertEquals(response, "failure");
		} 
		catch(Exception e)
		{
			ex=e;
		}
		assertEquals(ex,null);
	
	}

	@Test
	@PrepareForTest({HRMContext.class,DatabaseAccessorFactory.class,ModelFactory.class,Logger.class,Response.class})
	public void AuthenticatorValidPassword() throws Exception
	{
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
		PowerMockito.mockStatic(Response.class);

		HashMap<String,Object> results = new HashMap<String,Object>();
		results.put("status","success");
		Map<String, Object> loginresults = new HashMap<String,Object>();
	
		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin)).thenReturn(employeeLoginAccessor);
		Mockito.when(Response.createResponse(Constants.RESPONSE_SUCCESS)).thenReturn(results);

		Mockito.when(modelFactory.makeEmptyEmployeeLogin()).thenReturn(employeeLogin);
		Mockito.when(databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin)).thenReturn(employeeLoginAccessor);
		EncryptPassword encryptPassword1 = new EncryptPassword("password");
		Mockito.when(employeeLoginAccessor.fetchPasswordFromDatabase()).thenReturn(encryptPassword1.getHashedPassword());
		Mockito.when(encryptPassword.validatePassword(Mockito.eq("password"))).thenReturn(true);
		
		
		Exception ex = null;
		try
		{
			UserAccess userAccess = new UserAccess();
			loginresults = userAccess.login("empId", "password");	
			String response = (String)results.get(Constants.RESPONSE_STATUS);
			assertEquals(response, "success");
		} 
		catch(Exception e)
		{
			ex=e;
		}
		assertEquals(ex,null);
	
	}

}