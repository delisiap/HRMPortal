package com.hrm.rest.controller;


import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeSalarySlipAccessor;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.uploaddocuments.EmployeeSalaryUpload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
public class UploadSalarySlipTest
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
	EmployeeSalaryUpload employeesalaryUpload;

	@Mock
	EmployeeSalarySlipAccessor employeeSalarySlipAccessor;

	@Mock
	HttpServletResponse response;

	@Test
	@PrepareForTest({DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void UploadSalarySlipControllerExceptionTest()
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

		try{
			Mockito.when(employeesalaryUpload.getEmpId()).thenReturn("employee1");
			Mockito.when(employeesalaryUpload.validateSalarySlip()).thenReturn(resultSet);
			Mockito.when(databaseAccessorFactory.makeEmployeeSalarySlipAccessor(employeesalaryUpload.getEmployeeSalarySlip())).thenReturn(employeeSalarySlipAccessor);
			Mockito.when(employeeSalarySlipAccessor.insertSalarySlip()).thenReturn(true);

			UploadSalarySlip uploadSalarySlip = new UploadSalarySlip();
			results = uploadSalarySlip.uploadSalarySlip(employeesalaryUpload, response);
		}
		catch(Exception e){
			expectedException=e;
		}
		assertNull(expectedException);

	}

	@Test
	@PrepareForTest({DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void UploadSalarySlipHRMExceptionTest()
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

		try{
			Mockito.when(employeesalaryUpload.getEmpId()).thenReturn("employee1");
			Mockito.when(employeesalaryUpload.validateSalarySlip()).thenReturn(resultSet);
			Mockito.when(databaseAccessorFactory.makeEmployeeSalarySlipAccessor(employeesalaryUpload.getEmployeeSalarySlip())).thenReturn(employeeSalarySlipAccessor);
			Mockito.when(employeeSalarySlipAccessor.insertSalarySlip()).thenReturn(false);

			UploadSalarySlip uploadSalarySlip = new UploadSalarySlip();
			results = uploadSalarySlip.uploadSalarySlip(employeesalaryUpload, response);
		}
		catch(Exception e){
			expectedException=e;
		}
		assertNull(expectedException);
	}


}