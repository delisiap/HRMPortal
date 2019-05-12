package com.hrm.rest.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;

import com.hrm.rest.utility.Constants;

public class EmployeeSalarySlipTest
{
	EmployeeSalarySlip employeeSalarySlip;
	
	@Test
	public void employeeSalarySlipTestCases()
	{
		Exception expectedexception =null;
		try
		{
			byte[] bytes = (new String("")).getBytes();
			Blob blob = new SerialBlob(bytes);
			SimpleDateFormat simpleDateFormat= new SimpleDateFormat(Constants.MONTH_YEAR_FORMAT_STRING);
			Date date = simpleDateFormat.parse("2018-08");

			employeeSalarySlip = new EmployeeSalarySlip();
			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setMonthYear("2018-08-01");
			employeeSalarySlip.setSalarySlip(blob);

			final Field field1 = employeeSalarySlip.getClass().getDeclaredField("employeeId");
			field1.setAccessible(true);
			assertEquals("Fields match test", field1.get(employeeSalarySlip), "8999");

			final Field field2 = employeeSalarySlip.getClass().getDeclaredField("monthYear");
			field2.setAccessible(true);
			assertEquals("Fields match test", field2.get(employeeSalarySlip), date);

			final Field field3 = employeeSalarySlip.getClass().getDeclaredField("salarySlip");
			field3.setAccessible(true);
			assertEquals("Fields match test", field3.get(employeeSalarySlip), blob);
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
			SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM");
			java.util.Date date = simpleDateFormat.parse("2018-08-01");
			Date sqlDate = new java.sql.Date(date.getTime());

			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
			final Field field1 = employeeSalarySlip.getClass().getDeclaredField("employeeId");
			field1.setAccessible(true);
			field1.set(employeeSalarySlip, "sample_values");
		
			final String result = employeeSalarySlip.getEmployeeId();
			assertEquals("field retrieval test", result, "sample_values");

			final Field field2 = employeeSalarySlip.getClass().getDeclaredField("monthYear");
			field2.setAccessible(true);
			field2.set(employeeSalarySlip, "2018-08-01");

			final Date result2 = employeeSalarySlip.getMonthYear();
			assertEquals("field retrieval test", result2, sqlDate);

			final Field field3 = employeeSalarySlip.getClass().getDeclaredField("salarySlip");
			field3.setAccessible(true);
			field3.set(employeeSalarySlip, new SerialBlob((new String("")).getBytes()));

			final Blob result3 = employeeSalarySlip.getSalarySlip();
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
		Exception expectedException = null;
		try 
		{
			EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();

			employeeSalarySlip.setEmployeeId("8999");
			employeeSalarySlip.setMonthYear("2018-08-01");
			employeeSalarySlip.setSalarySlip(new SerialBlob((new String("")).getBytes()));

			assertNotNull(employeeSalarySlip);
			assertNotNull(employeeSalarySlip.getEmployeeId());
			assertNotNull(employeeSalarySlip.getSalarySlip());
			assertNotNull(employeeSalarySlip.getMonthYear());

			EmployeeSalarySlip employeeSalarySlip_null = new EmployeeSalarySlip();
			employeeSalarySlip_null.setEmployeeId(null);
			employeeSalarySlip_null.setSalarySlip(null);

			assertNull(employeeSalarySlip_null.getEmployeeId());
			assertNull(employeeSalarySlip_null.getSalarySlip());

		} 
		catch (Exception e) 
		{
			expectedException=e;
		} 
		assertNull(expectedException);
	}	
}