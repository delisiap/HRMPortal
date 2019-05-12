package com.hrm.rest.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLeaveInfoAccessor;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.validationlogic.LeaveValidation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ProcessLeaveTest
{
	@Mock
	private SessionController sessionController;

	@Mock
	LeaveValidation leavevalidation;

	@Mock
	DatabaseAccessorFactory databaseAccessorFactory;

	@Mock
	Logger logger;

	@Mock
	EmployeeLeaveInfo employeeLeaveInfo;

	@Mock
	EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor;

	@Mock
	HttpServletResponse response;

	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void processValidLeaveApplication() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
		
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(employeeLeaveInfoAccessor.updateEmployoeePendingLeave()).thenReturn(true);

		Exception expectedException = null;
		try
		{
			ProcessLeave processLeave = new ProcessLeave();
			values = processLeave.processLeave(employeeLeaveInfo,response);
			assertEquals(values.get("status"),"success");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}
	
	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void processInalidLeaveApplication() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
		
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(employeeLeaveInfoAccessor.updateEmployoeePendingLeave()).thenReturn(false);

		Exception expectedException = null;
		try
		{
			ProcessLeave processLeave = new ProcessLeave();
			values = processLeave.processLeave(employeeLeaveInfo,response);
			assertEquals(values.get("status"),"failure");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}
	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void viewProcessLeave() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
		ArrayList<HashMap<String, String>> resultset = new ArrayList<HashMap<String, String>>();
		
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
		Mockito.when(employeeLeaveInfoAccessor.getEmployeeNamesForID()).thenReturn(validationResult);
		Mockito.when(employeeLeaveInfoAccessor.fetchPendingEmpLeaveDetails(validationResult)).thenReturn(resultset);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(employeeLeaveInfoAccessor.updateEmployoeePendingLeave()).thenReturn(false);

		Exception expectedException = null;
		try
		{
			ProcessLeave processLeave = new ProcessLeave();
			values = processLeave.processLeave(employeeLeaveInfo,response);
			assertEquals(values.get("status"),"failure");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}
}