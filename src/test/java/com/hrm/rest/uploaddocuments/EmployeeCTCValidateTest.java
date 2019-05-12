package com.hrm.rest.uploaddocuments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.hrm.rest.model.EmployeeCTC;

import org.junit.Test;

public class EmployeeCTCValidateTest
{
	EmployeeCTCValidate employeeCTCValidate ;

	@Test
	public void validDocumentTest()
	{
		Exception expectedException = null;
		try
		{
			byte[] bytes = (new String("Justaddingalongstringtomakeabigmultipartfile")).getBytes();
			Blob blob = new SerialBlob(bytes);
			EmployeeCTC employeeCTC = new EmployeeCTC();
			employeeCTC.setCtcYear(2018);
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtc(blob);
			employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
			HashMap<String,String> results = employeeCTCValidate.validateDocument();
			assertEquals(results.get("IsValid"),"true");
		}
		catch(Exception e)
		{
			expectedException =e;
		}
		assertNull(expectedException);
	}

	@Test
	public void invalidDocumentTest()
	{
		Exception expectedException = null;
		try
		{
			Blob blob = null;
			EmployeeCTC employeeCTC = new EmployeeCTC();
			employeeCTC.setCtcYear(2018);
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtc(blob);
			employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
			HashMap<String,String> results = employeeCTCValidate.validateDocument();
			assertEquals(results.get("IsValid"),"false");
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	}

	@Test
	public void validDocumentBlobTest()
	{
		Exception expectedexception = null;
		try
		{
			byte[] bytes = (new String("Justaddingalongstringtomakeabigmultipartfile")).getBytes();
			Blob blob = new SerialBlob(bytes);
			EmployeeCTC employeeCTC = new EmployeeCTC();
			employeeCTC.setCtcYear(2018);
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtc(blob);
			employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeCTCValidate.validateBlob(results);
			assertTrue(result);
		}
		catch(Exception e)
		{
			expectedexception =e;
		}	
		assertNull(expectedexception);
	}

	@Test
	public void validBlobTest()
	{
		Exception expectedexception = null;
		try
		{
			byte[] bytes = (new String("Justaddingalongstringtomakeabigmultipartfile")).getBytes();
			Blob blob = new SerialBlob(bytes);
			EmployeeCTC employeeCTC = new EmployeeCTC();
			employeeCTC.setCtcYear(2018);
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtc(blob);
			employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeCTCValidate.validateBlob(results);
			assertTrue(result);
		}
		catch(Exception e)
		{
			expectedexception =e;
		}	
		assertNull(expectedexception);
	}

	@Test
	public void invalidBlobTest()
	{
		Exception expectedexception = null;
		try
		{
			byte[] bytes = (new String("")).getBytes();
			Blob blob = new SerialBlob(bytes);
			EmployeeCTC employeeCTC = new EmployeeCTC();
			employeeCTC.setCtcYear(2018);
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtc(blob);
			employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeCTCValidate.validateBlob(results);
			assertFalse(result);
		}
		catch(Exception e)
		{
			expectedexception =e;
		}	
		assertNull(expectedexception);
	}

	@Test
	public void validYearTest()
	{
		Exception expectedexception = null;
		try
		{
			byte[] bytes = (new String("")).getBytes();
			Blob blob = new SerialBlob(bytes);
			EmployeeCTC employeeCTC = new EmployeeCTC();
			employeeCTC.setCtcYear(2018);
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtc(blob);
			employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeCTCValidate.validateYear(results);
			assertTrue(result);
		}
		catch(Exception e)
		{
			expectedexception =e;
		}	
		assertNull(expectedexception);
	}

	@Test
	public void invalidYearTest()
	{
		Exception expectedexception = null;
		try
		{
			byte[] bytes = (new String("")).getBytes();
			Blob blob = new SerialBlob(bytes);
			EmployeeCTC employeeCTC = new EmployeeCTC();
			employeeCTC.setCtcYear(9999);
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtc(blob);
			employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeCTCValidate.validateYear(results);
			assertFalse(result);
		}
		catch(Exception e)
		{
			expectedexception =e;
		}	
		assertNull(expectedexception);
	}
	@Test
	public void validEmployeeIdTest()
	{
		EmployeeCTC employeeCTC = new EmployeeCTC();
		employeeCTC.setCtcYear(9999);
		employeeCTC.setEmployeeId("89999");
		employeeCTC.setCtc(null);
		employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
		HashMap<String,String> results = new HashMap<String,String>();
		boolean result= employeeCTCValidate.validateempid(results);
		assertTrue(result);
	}
	@Test
	public void invalidEmployeeIdTest()
	{
		EmployeeCTC employeeCTC = new EmployeeCTC();
		employeeCTC.setCtcYear(9999);
		employeeCTC.setEmployeeId("");
		employeeCTC.setCtc(null);
		employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
		HashMap<String,String> results = new HashMap<String,String>();
		boolean result= employeeCTCValidate.validateempid(results);
		assertFalse(result);
	} 

	@Test
	public void addErrorToResultSetTest() throws SerialException, SQLException
	{
		HashMap<String,String> resultstatus = new HashMap<String,String>();
		EmployeeCTC employeeCTC = new EmployeeCTC();
		employeeCTC.setCtcYear(9999);
		employeeCTC.setEmployeeId("");
		employeeCTC.setCtc(null);
		employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
		employeeCTCValidate.addErrorToResultSet("check", resultstatus);

		assertEquals(resultstatus.get("status"), "fail");
		resultstatus.clear();
	}
}

