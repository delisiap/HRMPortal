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
import java.util.Calendar;
import java.util.HashMap;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.model.EmployeeCTC;
import org.mockito.Mock;


public class EmployeeCTCUploadTest
{
	EmployeeCTCUpload employeectcupload ;
		
	@Mock
	byte[] bytes;
	
	@Mock
	Logger logger;
	
	@Test
	public void EmployeeCTCUploadTestCases() throws IOException
	{
		MultipartFile file = new MockMultipartFile("files", bytes);		
		employeectcupload = new EmployeeCTCUpload();
		employeectcupload.setEmpDOJ("2017-08-06");
		employeectcupload.setEmpId("89999");
		employeectcupload.setFile(file);				
		assertNotNull(employeectcupload);
		assertNotNull(employeectcupload.getFile());	
	}

	@Test
	public void BlobTest()
	{
		MultipartFile file = new MockMultipartFile("files", bytes);	
		employeectcupload = new EmployeeCTCUpload();
		employeectcupload.setEmpDOJ("2017-08-06");
		employeectcupload.setEmpId("89999");
		employeectcupload.setFile(file);
		Exception expectedException =null;		
		try
		{
			Blob sampblob = employeectcupload.getBlob();
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
		employeectcupload = new EmployeeCTCUpload();
		employeectcupload.setEmpDOJ("2017-08-06");
		employeectcupload.setEmpId("89999");
		employeectcupload.setFile(null);
		Exception expectedException =null;		
		try
		{
			assertNull(employeectcupload.getFile());
			assertNull(employeectcupload.getBlob());
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNotNull(expectedException);		
	}
	
	@Test
	public void getYearTest()
	{
		MultipartFile file = new MockMultipartFile("files", bytes);
		
		employeectcupload = new EmployeeCTCUpload();
		employeectcupload.setEmpDOJ("2017-08");
		employeectcupload.setEmpId("89999");
		employeectcupload.setFile(file);
		Exception expectedException =null;		
		try
		{
			int year = employeectcupload.getYear();
			assertNotNull(year);
			assertEquals(year,2017);
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	}

	@Test
	public void getYearNullTest()
	{
		MultipartFile file = new MockMultipartFile("files", bytes);	
		employeectcupload = new EmployeeCTCUpload();
		employeectcupload.setEmpDOJ("77777777777");
		employeectcupload.setEmpId("89999");
		employeectcupload.setFile(file);
		Exception expectedException =null;		
		try
		{
			int year = employeectcupload.getYear();
			assertNotNull(year);
			assertEquals(year,Calendar.getInstance().get(Calendar.YEAR));
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	}

	@Test
	public void getEmployeeCTCTest()
	{
		MultipartFile file = new MockMultipartFile("files", bytes);	
		employeectcupload = new EmployeeCTCUpload();
		employeectcupload.setEmpDOJ("77777777777");
		employeectcupload.setEmpId("89999");
		employeectcupload.setFile(file);
		assertNotNull(employeectcupload.getEmployeeCTC());
		assertTrue(employeectcupload.getEmployeeCTC() instanceof EmployeeCTC);
	}

	@Test
	public void getEmployeeCTCNullTest()
	{
		Exception expectedexception = null;
		try
		{
			employeectcupload = new EmployeeCTCUpload();
			employeectcupload.setEmpDOJ("77777777777");
			employeectcupload.setEmpId("89999");
			employeectcupload.setFile(null);
			EmployeeCTC employeectc = employeectcupload.getEmployeeCTC();
			assertNotNull(employeectc);
		}
		catch(Exception e)
		{
			expectedexception = e;
		}
		assertTrue(expectedexception instanceof NullPointerException);
	}

	@Test
	public void invalidateCTCTest()
	{
		Exception expectedexception=null;
		try
		{
			HashMap<String,String> resultSet= new HashMap<String,String>();
			MultipartFile file = new MockMultipartFile("files", bytes);	
			employeectcupload = new EmployeeCTCUpload();
			employeectcupload.setEmpDOJ("77777777777");
			employeectcupload.setEmpId("89999");
			employeectcupload.setFile(file);
			resultSet=employeectcupload.validateCTC();
			assertNotNull(resultSet);
			assertEquals("false", resultSet.get("IsValid"));
		}
		catch(Exception e)
		{
			expectedexception = e;
		}
		assertNull(expectedexception);
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
			employeectcupload = new EmployeeCTCUpload();
			employeectcupload.setEmpDOJ("2018-03-03");
			employeectcupload.setEmpId("8999");
			employeectcupload.setFile(file);
			resultSet=employeectcupload.validateCTC();
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