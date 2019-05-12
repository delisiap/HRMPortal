package com.hrm.rest.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;


public class EmployeeCTCTest
{	
	@Test
	public void EmployeeCTCTestCases()
	{
		EmployeeCTC employeeCTC;
		Exception expectedexception =null;
		try
		{
			byte[] bytes = (new String("")).getBytes();
			Blob blob = new SerialBlob(bytes);

			employeeCTC = new EmployeeCTC();
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtcYear(2018);
			employeeCTC.setCtc(blob);

			final Field field1 = employeeCTC.getClass().getDeclaredField("employeeId");
			field1.setAccessible(true);
			assertEquals("Fields match test", field1.get(employeeCTC), "8999");

			final Field field2 = employeeCTC.getClass().getDeclaredField("ctcYear");
			field2.setAccessible(true);
			assertEquals("Fields match test", field2.get(employeeCTC), 2018);

			final Field field3 = employeeCTC.getClass().getDeclaredField("ctc");
			field3.setAccessible(true);
			assertEquals("Fields match test", field3.get(employeeCTC), blob);
		}
		catch(Exception e)
		{
			expectedexception=e;
		}
		assertNull(expectedexception);	
	}

	@Test
	public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException
	{
		Exception expectedException=null;
		try
		{
			EmployeeCTC employeeCTC = new EmployeeCTC();
			final Field field1 = employeeCTC.getClass().getDeclaredField("employeeId");
			field1.setAccessible(true);
			field1.set(employeeCTC, "sample_values");
		
			final String result = employeeCTC.getEmployeeId();
			assertEquals("field retrieval test", result, "sample_values");

			final Field field2 = employeeCTC.getClass().getDeclaredField("ctcYear");
			field2.setAccessible(true);
			field2.set(employeeCTC, 2018);

			final int result2 = employeeCTC.getCtcYear();
			assertEquals("field retrieval test", result2, 2018);

			final Field field3 = employeeCTC.getClass().getDeclaredField("ctc");
			field3.setAccessible(true);
			field3.set(employeeCTC, new SerialBlob((new String("")).getBytes()));

			final Blob result3 = employeeCTC.getCtc();
			assertEquals("field retrieval test", result3, new SerialBlob((new String("")).getBytes()));

		}
		catch(Exception e)
		{
			expectedException=null;
		}
		assertNull(expectedException);
	}

	@Test
	public void checkForNull()
	{
		EmployeeCTC employeeCTC = new EmployeeCTC();
		Exception expectedException = null;
		try 
		{
			employeeCTC.setEmployeeId("8999");
			employeeCTC.setCtcYear(2018);
			employeeCTC.setCtc(new SerialBlob((new String("")).getBytes()));

			assertNotNull(employeeCTC);
			assertNotNull(employeeCTC.getCtc());
			assertNotNull(employeeCTC.getCtcYear());
			assertNotNull(employeeCTC.getEmployeeId());

			EmployeeCTC employeeCtc_null = new EmployeeCTC();
			employeeCtc_null.setEmployeeId(null);
			employeeCtc_null.setCtc(null);

			assertNull(employeeCtc_null.getEmployeeId());
			assertNull(employeeCtc_null.getCtc());
		} 
		catch (Exception e) 
		{
			expectedException=e;
		} 
		assertNull(expectedException);
	}
}