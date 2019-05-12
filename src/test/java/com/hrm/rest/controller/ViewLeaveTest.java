package com.hrm.rest.controller;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hrm.rest.HRMContext;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLeaveInfoAccessor;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.model.ModelFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
public class ViewLeaveTest
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
	EmployeeLeaveInfo employeeLeaveInfo;

	@Mock
	EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor;

	@Mock
	HttpServletResponse response;

	@Test
	@PrepareForTest({HRMContext.class, DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void ViewLeaveTestCases()
	{
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);

		Exception expectedException = null;
		ArrayList<HashMap<String, String>> employeeleavedetails = new ArrayList<>();
		Map<String, ArrayList<HashMap<String, String>>> result_map = new HashMap<>();

		try{
			Mockito.when(modelFactory.makeEmptyEmployeeLeaveInfo()).thenReturn(employeeLeaveInfo);
			Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
			Mockito.when(employeeLeaveInfoAccessor.fetchEmpLeaveDetails()).thenReturn(employeeleavedetails);

			ViewLeave viewLeave = new ViewLeave();
			result_map=viewLeave.viewLeave();
			assertNotNull((result_map.get("data")));
		}
		catch(Exception e){
			expectedException=e;
		}
		assertNull(expectedException);
	}

	@Test
	@PrepareForTest({DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void ViewLeaveTestCaseException()
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);

		Exception expectedException = null;
		Map<String, ArrayList<HashMap<String, String>>> result_map = new HashMap<>();
		
		try{
			Mockito.when(modelFactory.makeEmptyEmployeeLeaveInfo()).thenReturn(employeeLeaveInfo);
			Mockito.when(databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo)).thenReturn(employeeLeaveInfoAccessor);
			Mockito.when(employeeLeaveInfoAccessor.fetchEmpLeaveDetails()).thenReturn(null);
			
			ViewLeave viewLeave = new ViewLeave();
			result_map = viewLeave.viewLeave();
			assertNull(result_map.get("data"));
			
			
		}
		catch(Exception e){
			expectedException=e;
		}
		assertNotNull(expectedException);
	}

}