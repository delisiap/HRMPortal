package com.hrm.rest.model;

import java.sql.Blob;

public class EmployeeCTC
{
	private String employeeId;
	private int ctcYear;
	private Blob ctc;

	public Blob getCtc()
	{
		return ctc;
	}

	public int getCtcYear()
	{
		return ctcYear;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public void setCtc(Blob ctc)
	{
		this.ctc = ctc;
	}

	public void setCtcYear(int ctcYear)
	{
		this.ctcYear = ctcYear;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}
}