package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.hrm.rest.controller.SessionController;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeLogin;
import com.mysql.jdbc.CallableStatement;
import java.sql.Blob;

import org.junit.Test;
import com.hrm.rest.controller.Logger;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class EmployeeLoginAccessorTest
{

	@Mock
	private SessionController sessionController;

	@Mock
	DatabaseConnector dbConnector;

	@Mock
	CallableStatement callableStatement;

	@Mock
	EmployeeLogin employeeLogin;

	@Mock
	Logger logger;

	@Mock
	Blob returnBlob;


	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void fetchPasswordFromDatabase() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_GETPASSWORD_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.executeUpdate()).thenReturn(1);
		Mockito.when(String.valueOf(callableStatement.getObject(2))).thenReturn("password");

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			String password = employeeLoginAccessor.fetchPasswordFromDatabase();
			assertEquals(password,"password");
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}
	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void InvalidPasswordfromDatabase() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_GETPASSWORD_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.executeUpdate()).thenReturn(0);
		Mockito.when(String.valueOf(callableStatement.getObject(2))).thenReturn("");

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			String password = employeeLoginAccessor.fetchPasswordFromDatabase();
			assertNotEquals(password,"password");
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void NullPasswordfromDatabase() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_GETPASSWORD_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.executeUpdate()).thenReturn(0);
		Mockito.when(String.valueOf(callableStatement.getObject(2))).thenReturn(null);

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			String password = employeeLoginAccessor.fetchPasswordFromDatabase();
			assertNull(password);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}
	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void addEmployeeLoginTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_ADDLOGIN_STATEMENT)).thenReturn(callableStatement);

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			employeeLoginAccessor.addEmployeeLogin();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void invalidAddEmployeeLoginTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_ADDLOGIN_STATEMENT)).thenReturn(null);

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			employeeLoginAccessor.addEmployeeLogin();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void validDeleteEmployeeLoginTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_DELETE_STATEMENT)).thenReturn(callableStatement);

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			employeeLoginAccessor.deleteEmployeeLogin();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void invalidDeleteEmployeeLoginTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_DELETE_STATEMENT)).thenReturn(null);

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			employeeLoginAccessor.deleteEmployeeLogin();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void invalidUpdateEmployeeLoginLoginTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_UPDATE_STATEMENT)).thenReturn(null);

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			employeeLoginAccessor.updateEmployeeLogin();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class,Logger.class})
	public void updateEmployeeLoginLoginTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_UPDATE_STATEMENT)).thenReturn(null);

		Exception expectedexception =null;
		try
		{
			EmployeeLoginAccessor employeeLoginAccessor = new EmployeeLoginAccessor(employeeLogin);
			employeeLoginAccessor.updateEmployeeLogin();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}
}