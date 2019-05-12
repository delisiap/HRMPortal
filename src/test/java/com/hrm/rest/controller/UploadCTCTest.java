package com.hrm.rest.controller;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeCTCAccessor;
import com.hrm.rest.exceptionhandling.HRMExceptions;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.uploaddocuments.EmployeeCTCUpload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
public class UploadCTCTest {
	@Mock
	private SessionController sessionController;
	
	@Mock
	DatabaseAccessorFactory databaseAccessorFactory;

	@Mock
	ModelFactory modelFactory;

	@Mock
	Logger logger;

	@Mock
	EmployeeCTCUpload ctcupload;

	@Mock
	EmployeeCTCAccessor employeeCTCAccessor;

	@Mock
	HttpServletResponse response;

	@Test
	@PrepareForTest({DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void UploadCTCHRMExceptionTest() throws HRMExceptions
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);

		HashMap<String,String> resultSet = new HashMap<String,String>();
		resultSet.put("IsValid", "true");
		Exception expectedException = null;
		Map<String, String> results = new HashMap<String, String>();

		try
		{
			Mockito.when(ctcupload.getEmpId()).thenReturn("employee1");
			Mockito.when(ctcupload.validateCTC()).thenReturn(resultSet);
			Mockito.when(databaseAccessorFactory.makeEmployeeCTCAccessor(ctcupload.getEmployeeCTC())).thenReturn(employeeCTCAccessor);
			Mockito.when(employeeCTCAccessor.insertCTC()).thenReturn(false);

			UploadCTC uploadEmployeeCTC = new UploadCTC();
			results = uploadEmployeeCTC.uploadCTC(ctcupload, response);
			assertTrue(results.get("status").toString() == "failure" );
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	}

	@Test
	@PrepareForTest({DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void UploadCTCControllerExceptionTest()
	{
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);

		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);

		HashMap<String,String> resultSet = new HashMap<String,String>();
		resultSet.put("IsValid", "true");
		Exception expectedException = null;
		try
		{
			Map<String, String> results;
			Mockito.when(ctcupload.getEmpId()).thenReturn("employee1");
			Mockito.when(ctcupload.validateCTC()).thenReturn(resultSet);
			Mockito.when(databaseAccessorFactory.makeEmployeeCTCAccessor(ctcupload.getEmployeeCTC())).thenReturn(employeeCTCAccessor);
			Mockito.when(employeeCTCAccessor.insertCTC()).thenReturn(true);

			UploadCTC uploadEmployeeCTC = new UploadCTC();
			results = uploadEmployeeCTC.uploadCTC(ctcupload, response);
		}
		catch(Exception e){
			expectedException=e;
		}
		assertNull(expectedException);

	}

}
