package com.hrm.rest.model;

import java.util.Date;

public class Logs
{
	private String employeeId;
	private Date logTime;
	private String logLevel;
	private String logMessage;
	private String className;

	public String getClassName()
	{
		return className;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public String getLogLevel()
	{
		return logLevel;
	}

	public String getLogMessage()
	{
		return logMessage;
	}

	public Date getLogTime()
	{
		return logTime;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public void setLogLevel(String logLevel)
	{
		this.logLevel = logLevel;
	}

	public void setLogMessage(String logMessage)
	{
		this.logMessage = logMessage;
	}

	public void setLogTime(Date logTime)
	{
		this.logTime = logTime;
	}
}