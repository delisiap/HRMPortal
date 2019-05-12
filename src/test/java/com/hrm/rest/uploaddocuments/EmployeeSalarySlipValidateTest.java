package com.hrm.rest.uploaddocuments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.hrm.rest.model.EmployeeSalarySlip;

import org.junit.Test;

public class EmployeeSalarySlipValidateTest
{
	EmployeeSalarySlipValidate employeeSalarySlipValidate ;

	@Test
	public void validDocumentTest()
	{
		Exception expectedException = null;
		try
		{
			byte[] bytes = (new String("Justaddingalongstringtomakeabigmultipartfile")).getBytes();
			Blob blob = new SerialBlob(bytes);
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = employeeSalarySlipValidate.validateDocument();
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
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = employeeSalarySlipValidate.validateDocument();
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
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeSalarySlipValidate.validateBlob(results);
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
			
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeSalarySlipValidate.validateBlob(results);
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
			
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeSalarySlipValidate.validateBlob(results);
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
			
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeSalarySlipValidate.validateYear(results);
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
			
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2019-08");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeSalarySlipValidate.validateYear(results);
			assertFalse(result);
		}
		catch(Exception e)
		{
			expectedexception =e;
		}	
		assertNull(expectedexception);
	}
	@Test
	public void validEmployeeIdTest() throws ParseException, SerialException, SQLException
	{
		byte[] bytes = (new String("")).getBytes();
		Blob blob = new SerialBlob(bytes);
		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08-01");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);		
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			HashMap<String,String> results = new HashMap<String,String>();
			boolean result= employeeSalarySlipValidate.validateYear(results);
			assertTrue(result);
		}
		catch(Exception e)
		{
			expectedexception=e;
		}
		assertNull(expectedexception);
	}
	@Test
	public void invalidEmployeeIdTest() throws SerialException, SQLException, ParseException
	{
		byte[] bytes = (new String("")).getBytes();
		Blob blob = new SerialBlob(bytes);
		Exception expectedException = null;
		try
		{
		EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
		employeeSalarySlip.setMonthYear("2018-08-01");
		employeeSalarySlip.setEmployeeId("");
		employeeSalarySlip.setSalarySlip(blob);
		
		employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
		HashMap<String,String> results = new HashMap<String,String>();
		boolean result= employeeSalarySlipValidate.validateempid(results);
		assertFalse(result);
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	} 

	@Test
	public void addErrorToResultSetTest() throws SerialException, SQLException, ParseException
	{
		byte[] bytes = (new String("")).getBytes();
		Blob blob = new SerialBlob(bytes);
		HashMap<String,String> resultstatus = new HashMap<String,String>();
		Exception expectedException = null;
		try
		{
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setMonthYear("2018-08-01");
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setSalarySlip(blob);
			
			employeeSalarySlipValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			employeeSalarySlipValidate.addErrorToResultSet("check", resultstatus);

			assertEquals(resultstatus.get("status"), "fail");
		}
		catch(Exception e)
		{
			expectedException=e;
		} 
		assertNull(expectedException);
	}
}