package com.hrm.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import com.hrm.rest.HRMContext;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeInfoAccessor;
import com.hrm.rest.employee.EmployeeHelper;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.ModelFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ProfileTest
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
	EmployeeInfoAccessor employeeInfoAccessor;

	@Mock
	EmployeeHelper employeeHelper;
	
	@Test
	@PrepareForTest({ HRMContext.class, DatabaseAccessorFactory.class, ModelFactory.class, Logger.class ,EmployeeHelper.class })
	public void ValidProfile() throws Exception
	{
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
		PowerMockito.mockStatic(EmployeeHelper.class);

		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(EmployeeHelper.getEmployeeInfo("8999")).thenReturn(employeeInfo);
		Exception expectedException = null;

		try
		{
			Profile profile = new Profile();
			HashMap<String, Object>  hmap = profile.getEmployeeInfo("8999");
			assertEquals(hmap.get("status"),"success");
			assertNotNull(hmap);
		}
		catch(Exception e)
		{
			expectedException =e;
		}
		assertNull(expectedException);
	}
	
	@Test
	@PrepareForTest({ HRMContext.class, DatabaseAccessorFactory.class, ModelFactory.class, Logger.class ,EmployeeHelper.class })

	public void nullEmployeeIDTest() throws Exception
	{
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
		PowerMockito.mockStatic(EmployeeHelper.class);

		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
		Mockito.when(EmployeeHelper.getEmployeeInfo("8999")).thenReturn(new EmployeeInfo());
		Mockito.when(sessionController.getLoggedInUserId()).thenReturn("8999");
		Exception expectedException = null;

		try
		{
			Profile profile = new Profile();
			HashMap<String, Object>  hmap = profile.getEmployeeInfo(null);
			assertEquals(hmap.get("status"),"success");
			assertNotNull(hmap);
		}
		catch(Exception e)
		{
			expectedException =e;
		}
	}
}