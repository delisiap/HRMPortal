package com.hrm.rest.uploaddocuments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.HashMap;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.model.EmployeeSalarySlip;

import org.mockito.Mock;


public class EmployeeSalaryUploadTest
{
	EmployeeSalaryUpload employeeSalaryUpload ;
		
	@Mock
	byte[] bytes;
	
	@Mock
	Logger logger;
	
	@Test
	public void EmployeeSalarySlipUploadTestCases() throws IOException
	{
		MultipartFile file = new MockMultipartFile("files", bytes);		
		employeeSalaryUpload = new EmployeeSalaryUpload();
		employeeSalaryUpload.setEmpDOJ("2017-08-06");
		employeeSalaryUpload.setEmpId("89999");
		employeeSalaryUpload.setFile(file);				
		assertNotNull(employeeSalaryUpload);
		assertNotNull(employeeSalaryUpload.getFile());	
	}

	@Test
	public void BlobTest()
	{
		MultipartFile file = new MockMultipartFile("files", bytes);	
		employeeSalaryUpload = new EmployeeSalaryUpload();
		employeeSalaryUpload.setEmpDOJ("2017-08-06");
		employeeSalaryUpload.setEmpId("89999");
		employeeSalaryUpload.setFile(file);
		Exception expectedException =null;		
		try
		{
			Blob sampblob = employeeSalaryUpload.getBlob();
			assertTrue(sampblob instanceof Blob);
			assertNotNull(sampblob);
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	}
	
	@Test
	public void BlobnullTest()
	{
		employeeSalaryUpload = new EmployeeSalaryUpload();
		employeeSalaryUpload.setEmpDOJ("2017-08-06");
		employeeSalaryUpload.setEmpId("89999");
		employeeSalaryUpload.setFile(null);
		Exception expectedException =null;		
		try
		{
			assertNull(employeeSalaryUpload.getFile());
			assertNull(employeeSalaryUpload.getBlob());
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNotNull(expectedException);		
	}
	
	@Test
	public void getEmployeeCTCTest()
	{
		Exception expectedException = null;
		try
		{
			MultipartFile file = new MockMultipartFile("files", bytes);	
			employeeSalaryUpload = new EmployeeSalaryUpload();
			employeeSalaryUpload.setEmpDOJ("2018-08-01");
			employeeSalaryUpload.setEmpId("89999");
			employeeSalaryUpload.setFile(file);
			assertNotNull(employeeSalaryUpload.getEmployeeSalarySlip());
			assertTrue(employeeSalaryUpload.getEmployeeSalarySlip() instanceof EmployeeSalarySlip);
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	}

	@Test
	public void getEmployeeCTCNullTest()
	{
		Exception expectedexception = null;
		try
		{
			employeeSalaryUpload = new EmployeeSalaryUpload();
			employeeSalaryUpload.setEmpDOJ("77777777777");
			employeeSalaryUpload.setEmpId("89999");
			employeeSalaryUpload.setFile(null);
			EmployeeSalarySlip employeeSalarySlip = employeeSalaryUpload.getEmployeeSalarySlip();
			assertNotNull(employeeSalarySlip);
		}
		catch(Exception e)
		{
			expectedexception = e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	public void invalidateCTCTest()
	{
		Exception expectedexception=null;
		try
		{
			HashMap<String,String> resultSet= new HashMap<String,String>();
			MultipartFile file = new MockMultipartFile("files", bytes);	
			employeeSalaryUpload = new EmployeeSalaryUpload();
			employeeSalaryUpload.setEmpDOJ("77777777777");
			employeeSalaryUpload.setEmpId("89999");
			employeeSalaryUpload.setFile(file);
			resultSet=employeeSalaryUpload.validateSalarySlip();
			assertNull(resultSet);
		}
		catch(Exception e)
		{
			expectedexception = e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	public void validateCTCTest()
	{
		Exception expectedexception=null;
		try
		{
			HashMap<String,String> resultSet= new HashMap<String,String>();
			byte[] bytes = (new String("Justaddingalongstringtomakeabigmultipartfile")).getBytes();
			MultipartFile file = new MockMultipartFile("files", bytes);	
			employeeSalaryUpload = new EmployeeSalaryUpload();
			employeeSalaryUpload.setEmpDOJ("2018-03-03");
			employeeSalaryUpload.setEmpId("8999");
			employeeSalaryUpload.setFile(file);
			resultSet=employeeSalaryUpload.validateSalarySlip();
			assertNotNull(resultSet);
			assertEquals("true", resultSet.get("IsValid"));
		}
		catch(Exception e)
		{
			expectedexception = e;
		}
		assertNull(expectedexception);
	}
}