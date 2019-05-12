package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.utility.Constants;
import com.mysql.jdbc.CallableStatement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class EmployeeInfoAccessorTest
{
	@Mock
	DatabaseConnector dbConnector;

	@Mock
	CallableStatement callableStatement;

	@Mock
	private Logger logger;

	@Mock
	EmployeeInfo employeeInfo;

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void addEmployeeTest()
	{
		Exception expectedexception =null;
		Date dateOfBirth, dateOfJoining;
		
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEEINFO_INSERT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.executeUpdate()).thenReturn(1);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.MONTH_YEAR_FORMAT_STRING);
			dateOfJoining = simpleDateFormat.parse("2018-12-07");
			dateOfBirth = simpleDateFormat.parse("1992-12-07");
			
			Mockito.when(employeeInfo.getDateOfJoining()).thenReturn(dateOfJoining);
			Mockito.when(employeeInfo.getDateOfBirth()).thenReturn(dateOfBirth);

			EmployeeInfoAccessor employeeInfoAccessor = new EmployeeInfoAccessor(employeeInfo);
			employeeInfoAccessor.addEmployee();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void deleteEmployeeTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEEINFO_DELETE_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.execute()).thenReturn(true);

			EmployeeInfoAccessor employeeInfoAccessor = new EmployeeInfoAccessor(employeeInfo);
			employeeInfoAccessor.deleteEmployee();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void getEmployeeInfoTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEEINFO_SELECT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.execute()).thenReturn(true);
		
			EmployeeInfoAccessor employeeInfoAccessor = new EmployeeInfoAccessor(employeeInfo);
			employeeInfo = employeeInfoAccessor.getEmployeeInfo();
			assertNotNull(employeeInfo);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void updateEmployeeInfoTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEEINFO_UPDATE_STATEMENT)).thenReturn(null);
			Mockito.when(callableStatement.execute()).thenReturn(true);
		
			EmployeeInfoAccessor employeeInfoAccessor = new EmployeeInfoAccessor(employeeInfo);
			employeeInfoAccessor.updateEmployeeInfo();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}
}