package com.hrm.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Blob;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import com.hrm.rest.HRMContext;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeCTCAccessor;
import com.hrm.rest.dbaccessor.EmployeeSalarySlipAccessor;
import com.hrm.rest.model.EmployeeCTC;
import com.hrm.rest.model.EmployeeSalarySlip;
import com.hrm.rest.model.ModelFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;



@RunWith(PowerMockRunner.class)
public class AccountTest 
{
	
	@Mock
	SessionController sessionController;
	
	@Mock
	DatabaseAccessorFactory databaseAccessorFactory;

	@Mock
	ModelFactory modelFactory;

	@Mock
	Logger logger;

	@Mock
	EmployeeSalarySlipAccessor employeeSalarySlipAccessor;

	@Mock
	EmployeeCTCAccessor employeeCTCAccessor;
	
	@Mock 
	EmployeeSalarySlip empSalarySlip;
	
	@Mock
	EmployeeCTC empCTC;

	@Mock
	HttpServletResponse response;
	

	@Test
	@PrepareForTest({HRMContext.class,DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void salarySlipDownloadTest() 
	{		
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
		Exception expectedException =null;
		
		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
			
		
		try
		{
			byte[] bytes = (new String("")).getBytes();
			Blob blob = new SerialBlob(bytes);
			String monthYear = "08-2018";
					

			Mockito.when(modelFactory.makeEmptyEmployeeSalarySlip()).thenReturn(empSalarySlip);
			Mockito.when(databaseAccessorFactory.makeEmployeeSalarySlipAccessor(empSalarySlip)).thenReturn(employeeSalarySlipAccessor);
			Mockito.when(employeeSalarySlipAccessor.getEmployeeSalarySlip()).thenReturn(blob);
			
			Account account = new Account();
			ResponseEntity<InputStreamResource> result = account.downloadSalarySlip(monthYear);
			assertNotNull(result);
			int code = result.getStatusCodeValue();
			assertEquals(code,200);
		}
		catch(Exception e)
		{
			expectedException =e;
		}
		assertNull(expectedException);	
	}
	
	@Test
	@PrepareForTest({HRMContext.class,DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void ctcDownloadTest() 
	{		
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
		Exception expectedException =null;
		
		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
			
		
		try
		{
			byte[] bytes = (new String("")).getBytes();
			Blob blob = new SerialBlob(bytes);
			String year = "2018";
					
			
			Mockito.when(modelFactory.makeEmptyEmployeeCTC()).thenReturn(empCTC);
			Mockito.when(modelFactory.makeEmployeeCTC(sessionController.getLoggedInUserId(), Integer.parseInt(year), null)).thenReturn(empCTC);
			Mockito.when(databaseAccessorFactory.makeEmployeeCTCAccessor(empCTC)).thenReturn(employeeCTCAccessor);
			Mockito.when(employeeCTCAccessor.getEmployeeCTC()).thenReturn(blob);
			
			Account account = new Account();
			ResponseEntity<InputStreamResource> result = account.downloadCTC(year);
			assertNotNull(result);
			int code = result.getStatusCodeValue();
			assertEquals(code,200);
		}
		catch(Exception e)
		{
			expectedException =e;
		}
		assertNull(expectedException);
	}
	
	@Test
	@PrepareForTest({HRMContext.class,DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void ctcInvalidDownloadTest() 
	{		
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
		Exception expectedException =null;
		
		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
			
		
		try
		{
			Blob blob = null;
			String year = "2018";
			
			Mockito.when(modelFactory.makeEmptyEmployeeCTC()).thenReturn(empCTC);
			Mockito.when(modelFactory.makeEmployeeCTC(sessionController.getLoggedInUserId(), Integer.parseInt(year), null)).thenReturn(empCTC);
			Mockito.when(databaseAccessorFactory.makeEmployeeCTCAccessor(empCTC)).thenReturn(employeeCTCAccessor);
			Mockito.when(employeeCTCAccessor.getEmployeeCTC()).thenReturn(blob);
			
			Account account = new Account();
			ResponseEntity<InputStreamResource> result = account.downloadCTC(year);
			assertNull(result);
		}
		catch(Exception e)
		{
			expectedException =e;
		}
		assertNull(expectedException);
	}
	@Test
	@PrepareForTest({HRMContext.class,DatabaseAccessorFactory.class,ModelFactory.class,Logger.class})
	public void salarySlipInvalidDownloadTest() 
	{		
		PowerMockito.mockStatic(HRMContext.class);
		PowerMockito.mockStatic(DatabaseAccessorFactory.class);
		PowerMockito.mockStatic(ModelFactory.class);
		PowerMockito.mockStatic(Logger.class);
		Exception expectedException =null;
		
		Mockito.when(HRMContext.getSessionController()).thenReturn(sessionController);
		Mockito.when(DatabaseAccessorFactory.getInstance()).thenReturn(databaseAccessorFactory);
		Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
		Mockito.when(Logger.getInstance()).thenReturn(logger);
			
		
		try
		{
			Blob blob =null;
			String year = "2018";
					
			
			Mockito.when(modelFactory.makeEmptyEmployeeCTC()).thenReturn(empCTC);
			Mockito.when(modelFactory.makeEmployeeCTC(sessionController.getLoggedInUserId(), Integer.parseInt(year), null)).thenReturn(empCTC);
			Mockito.when(databaseAccessorFactory.makeEmployeeCTCAccessor(empCTC)).thenReturn(employeeCTCAccessor);
			Mockito.when(employeeCTCAccessor.getEmployeeCTC()).thenReturn(blob);
			
			Account account = new Account();
			ResponseEntity<InputStreamResource> result = account.downloadCTC(year);
			assertNull(result);
		}
		catch(Exception e)
		{
			expectedException =e;
		}
		assertNull(expectedException);
	}
	
	
}
