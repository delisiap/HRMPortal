package com.hrm.rest.model;

public class EmployeeLogin
{
	private String employeeId;
	private String password;

	public String getPassword()
	{
		return password;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public boolean isnull(String value)
	{
		return (value == null);
	}
}