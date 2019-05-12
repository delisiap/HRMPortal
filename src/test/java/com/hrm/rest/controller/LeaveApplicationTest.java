package com.hrm.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

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
public class LeaveApplicationTest
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


	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void InvalidLeaveApplication() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
		
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(employeeLeaveInfoAccessor.insertLeaveDetails()).thenReturn(true);

		Exception expectedException = null;
		try
		{
			LeaveApplication leaveApplication = new LeaveApplication();
			values = leaveApplication.applyLeave(employeeLeaveInfo);
			assertEquals(values.get("status"),"failure");
			assertEquals(values.get("IsValid"),"false");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}

	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void invalidDateTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
		
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(employeeLeaveInfoAccessor.insertLeaveDetails()).thenReturn(true);

		Exception expectedException = null;
		try
		{
			EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
			employeeLeaveInfo.setId(8888);
			employeeLeaveInfo.setStatus("NEW");
			employeeLeaveInfo.setEmployeeId("8999");
			employeeLeaveInfo.setStartDate("2019-07-08");
			employeeLeaveInfo.setEndDate("2019-07-09");

			LeaveApplication leaveApplication = new LeaveApplication();
			values = leaveApplication.applyLeave(employeeLeaveInfo);

			assertEquals(values.get("error"),"You can apply leaves only for current year.");
			assertEquals(values.get("status"),"failure");
			assertEquals(values.get("IsValid"),"false");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}
	
	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void fromToDateTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
		
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(employeeLeaveInfoAccessor.insertLeaveDetails()).thenReturn(true);

		Exception expectedException = null;
		try
		{
			EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
			employeeLeaveInfo.setId(8888);
			employeeLeaveInfo.setStatus("NEW");
			employeeLeaveInfo.setEmployeeId("8999");
			employeeLeaveInfo.setStartDate("2018-07-08");
			employeeLeaveInfo.setEndDate("2018-07-09");

			LeaveApplication leaveApplication = new LeaveApplication();
			values = leaveApplication.applyLeave(employeeLeaveInfo);
			
			assertEquals(values.get("error"),"From date should be greater than currrent date");
			assertEquals(values.get("status"),"failure");
			assertEquals(values.get("IsValid"),"false");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}

	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void validDateTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");

		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(employeeLeaveInfoAccessor.insertLeaveDetails()).thenReturn(true);

		Exception expectedException = null;
		try
		{
			EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
			employeeLeaveInfo.setId(8888);
			employeeLeaveInfo.setStatus("NEW");
			employeeLeaveInfo.setEmployeeId("8999");
			employeeLeaveInfo.setStartDate("2018-08-29");
			employeeLeaveInfo.setEndDate("2018-08-30");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNull(expectedException);
	}
	
	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void remainingDateTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
		
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
		Mockito.when(employeeLeaveInfoAccessor.getRemainingLeaveCount()).thenReturn("12");

		Exception expectedException = null;
		try
		{
			LeaveApplication leaveApplication = new LeaveApplication();		
			values = leaveApplication.getRemainingLeave();
			assertEquals(values.get("data"),"12");
			assertEquals(values.get("status"),"success");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNotNull(expectedException);
	}

	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class,ModelFactory.class})
	public void remainingNullDateTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		HashMap<String,String> validationResult = new HashMap<>();
		validationResult.put("IsValid", "true");
		Map<String, String > values = new HashMap<String,String>();
	
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
		Mockito.when(employeeLeaveInfoAccessor.getRemainingLeaveCount()).thenReturn(null);

		Exception expectedException = null;
		try
		{
			LeaveApplication leaveApplication = new LeaveApplication();		
			values = leaveApplication.getRemainingLeave();
			assertEquals(values.get("status"),"failure");
		}
		catch (Exception e)
		{
			expectedException = e;
		}
		assertNotNull(expectedException);
	}
}