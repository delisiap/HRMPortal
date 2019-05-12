package com.hrm.rest.model;

import java.sql.Blob;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.hrm.rest.utility.Constants;

public class EmployeeSalarySlip
{
	private String employeeId;
	private Date monthYear;
	private Blob salarySlip;

	public String getEmployeeId()
	{
		return employeeId;
	}

	public Date getMonthYear()
	{
		return monthYear;
	}

	public Blob getSalarySlip()
	{
		return salarySlip;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public void setMonthYear(String monthYear) throws Exception
	{
		try
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.MONTH_YEAR_FORMAT_STRING);
			java.util.Date date = simpleDateFormat.parse(monthYear);
			this.monthYear = new java.sql.Date(date.getTime());
		}
		catch(ParseException e)
		{
			throw new Exception ("Invalid value is provided for Month and Year field.: " + e.getMessage(), e);
		}
	}

	public void setSalarySlip(Blob salarySlip)
	{
		this.salarySlip = salarySlip;
	}
}