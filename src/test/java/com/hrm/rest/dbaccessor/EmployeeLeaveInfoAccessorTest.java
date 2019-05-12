package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.mysql.jdbc.CallableStatement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class EmployeeLeaveInfoAccessorTest
{
	@Mock
	DatabaseConnector dbConnector;

	@Mock
	CallableStatement callableStatement;

	@Mock
	private Logger logger;

	@Mock
	ResultSet resultSet;

	@Mock
	EmployeeLeaveInfo employeeLeaveInfo;

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void getRemainingLeaveCountTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLEAVE_SELECT_REMAININGLEAVECOUNT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.execute()).thenReturn(true);

			EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = new EmployeeLeaveInfoAccessor(employeeLeaveInfo);
			String result = employeeLeaveInfoAccessor.getRemainingLeaveCount();
			assertNotNull(result);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void getEmployeeLeaveDetailsTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLEAVE_SELECT_LEAVEDETAILS_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.executeQuery()).thenReturn(resultSet);

			EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = new EmployeeLeaveInfoAccessor(employeeLeaveInfo);
			ArrayList<HashMap<String, String>> result = employeeLeaveInfoAccessor.getEmployeeLeaveDetails();
			assertNotNull(result);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}
}