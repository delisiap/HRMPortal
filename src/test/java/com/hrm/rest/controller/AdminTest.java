package com.hrm.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.employee.EmployeeHelper;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.role.Role;
import com.hrm.rest.role.Roles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class AdminTest
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
	EmployeeInfo employeeInfo;
	
	@Mock
	EmployeeHelper employeeHelper;
	
	@Mock
	Roles roles;
	
	@Mock
	Role role;
	

	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class ,ModelFactory.class,EmployeeHelper.class , Roles.class , Role.class})

	public void addEmployeeTest()
	{
		Exception expectedException = null;
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(Logger.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(EmployeeHelper.class);
		PowerMockito.mockStatic(Roles.class);
		PowerMockito.mockStatic(Role.class);
		
		String employeeName="sue";
		String employeeId="8999";
		String emailId="sue@gmail.com";
		String contact="678902345";
		String address="South Street";
		String alternateEmailId="ss@gmail.com";
		int role_num=12;
		String dateOfJoining ="";
		String dateOfBirth= "";
		
		try
		{
			Map<String, Object> result = new HashMap<>();
			Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
			Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
			Mockito.when(EmployeeHelper.getRoleByRoleName("SSE")).thenReturn(role);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(modelFactory.makeEmployeeInfo(employeeName, employeeId, emailId, contact, address,
					alternateEmailId, role_num, dateOfJoining, dateOfBirth, 30)).thenReturn(employeeInfo);
			Admin admin = new Admin();
			result = admin.addEmployee("sue","SSE","15081992","21072010","678902345","sue@gmail.com","ss@gmail.com","34","1234567","South Street");
			assertEquals(result.get("status"),"success");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		assertNull(expectedException);
	}

	@Test
	@PrepareForTest({ DatabaseAccessorFactory.class, Logger.class ,ModelFactory.class,EmployeeHelper.class , Roles.class , Role.class})
	public void removeEmployeeTest()
	{
		Exception expectedException = null;
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(Logger.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(EmployeeHelper.class);
		PowerMockito.mockStatic(Roles.class);
		PowerMockito.mockStatic(Role.class);
		
		try
		{
			Map<String, Object> result = new HashMap<>();
			Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
			Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
			Mockito.when(EmployeeHelper.getRoleByRoleName("SSE")).thenReturn(role);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(modelFactory.makeEmptyEmployeeInfo()).thenReturn(employeeInfo);
			Admin admin = new Admin();
			result = admin.removeEmployee("8999");
			assertEquals(result.get("status"),"success");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		assertNull(expectedException);
	}

	@Test
	@PrepareForTest({Logger.class})
	public void getrolenamesTest()
	{
		Exception expectedException = null;
		PowerMockito.mockStatic(Logger.class);
		
		try
		{
			Map<String, Object> result = new HashMap<>();
			Mockito.when(Logger.getInstance()).thenReturn(logger);

			Admin admin = new Admin();
			result = admin.getRoleNames();
			assertEquals(result.get("status"),"success");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		assertNull(expectedException);
	}
}