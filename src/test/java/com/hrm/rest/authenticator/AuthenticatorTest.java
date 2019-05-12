package com.hrm.rest.authenticator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.hrm.rest.HRMContext;
import com.hrm.rest.controller.Logger;
import com.hrm.rest.controller.SessionController;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLoginAccessor;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.ModelFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class AuthenticatorTest
{
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
	EncryptPassword encryptPassword;

	@Test
	@PrepareForTest({ HRMContext.class, DatabaseAccessorFactory.class, ModelFactory.class, Logger.class })
	public void AuthenticatorValidPassword() throws Exception
	{
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);

		Mockito.when(modelFactory.makeEmptyEmployeeLogin()).thenReturn(employeeLogin);
		Mockito.when(databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin)).thenReturn(employeeLoginAccessor);
		EncryptPassword encryptPassword1 = new EncryptPassword("password");
		Mockito.when(employeeLoginAccessor.fetchPasswordFromDatabase()).thenReturn(encryptPassword1.getHashedPassword());
		Mockito.when(encryptPassword.validatePassword(Mockito.eq("password"))).thenReturn(true);
		Authenticator authenticator = new Authenticator();
		Exception expectedException = null;
		try
		{
			authenticator.processLogin("empId", "password");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}

	@Test
	@PrepareForTest({ HRMContext.class, DatabaseAccessorFactory.class, ModelFactory.class, Logger.class })
	public void AuthenticatorInvalidPAssword() throws Exception
	{
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);

		Mockito.when(modelFactory.makeEmptyEmployeeLogin()).thenReturn(employeeLogin);
		Mockito.when(databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin)).thenReturn(employeeLoginAccessor);
		EncryptPassword encryptPassword1 = new EncryptPassword("password1");
		Mockito.when(employeeLoginAccessor.fetchPasswordFromDatabase()).thenReturn(encryptPassword1.getHashedPassword());
		Mockito.when(encryptPassword.validatePassword(Mockito.eq("password1"))).thenReturn(true);
		Authenticator authenticator = new Authenticator();
		Exception expectedException = null;
		try
		{
		
			authenticator.processLogin("empId", "password");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNotNull(expectedException);
	}
}