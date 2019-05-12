package com.hrm.rest.model;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hrm.rest.controller.Logger;

public class ModelFactory implements IModelFactory
{
	private static ModelFactory modelFactory = null;

	private String inputDateFormat = "yyyy-MM-dd";

	public static ModelFactory getInstance()
	{
		if (modelFactory == null)
		{
			modelFactory = new ModelFactory();
		}
		return modelFactory;
	}

	@Override
	public EmployeeCTC makeEmployeeCTC(String employeeId, int ctcYear, Blob ctc)
	{
		Logger logger = Logger.getInstance();
		EmployeeCTC employeeCTC = new EmployeeCTC();
		try
		{
			employeeCTC.setEmployeeId(employeeId);
			employeeCTC.setCtcYear(ctcYear);
			employeeCTC.setCtc(ctc);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		logger.info("Creating a new EmployeeCTC instance.", this.getClass().getName(), Logger.Location.ALL);
		return employeeCTC;
	}

	@Override
	public EmployeeInfo makeEmployeeInfo(String employeeName, String employeeId, String emailId, String phoneNo,
			String address, String alternateEmailId, int roleId, String dateOfJoining, String dateOfBirth,
			int noOfLeaves)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inputDateFormat);
		Logger logger = Logger.getInstance();
		EmployeeInfo employeeInfo = new EmployeeInfo();
		try
		{
			employeeInfo.setEmployeeName(employeeName);
			employeeInfo.setEmployeeId(employeeId);
			employeeInfo.setEmailId(emailId);
			employeeInfo.setPhoneNo(phoneNo);
			employeeInfo.setAddress(address);
			employeeInfo.setAlternateEmailId(alternateEmailId);
			employeeInfo.setRoleId(roleId);
			employeeInfo.setDateOfJoining(simpleDateFormat.parse(dateOfJoining));
			employeeInfo.setDateOfBirth(simpleDateFormat.parse(dateOfJoining));
			employeeInfo.setNoOfLeaves(noOfLeaves);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		logger.info("Creating a new EmployeeInfo instance.", this.getClass().getName(), Logger.Location.ALL);
		return employeeInfo;
	}

	@Override
	public EmployeeLeaveInfo makeEmployeeLeaveInfo(String employeeId, String startDate, String endDate, String status)
	{
		Logger logger = Logger.getInstance();
		EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
		try
		{
			employeeLeaveInfo.setEmployeeId(employeeId);
			employeeLeaveInfo.setStartDate(startDate);
			employeeLeaveInfo.setEndDate(endDate);
			employeeLeaveInfo.setStatus(status);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		logger.info("Creating a new EmployeeLeaveInfo instance.", this.getClass().getName(), Logger.Location.ALL);
		return employeeLeaveInfo;
	}

	@Override
	public EmployeeLogin makeEmployeeLogin(String employeeId, String passwordHash)
	{
		Logger logger = Logger.getInstance();
		EmployeeLogin employeeLogin = new EmployeeLogin();
		try
		{
			employeeLogin.setEmployeeId(employeeId);
			employeeLogin.setPassword(passwordHash);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		logger.info("Creating a new EmployeeLogin instance.", this.getClass().getName(), Logger.Location.ALL);
		return employeeLogin;
	}

	@Override
	public EmployeeRoles makeEmployeeRoles(String roleId, String roleName)
	{
		Logger logger = Logger.getInstance();
		EmployeeRoles employeeRoles = new EmployeeRoles();
		try
		{
			employeeRoles.setRoleId(roleId);
			employeeRoles.setRoleName(roleName);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		logger.info("Creating a new EmployeeRoles instance.", this.getClass().getName(), Logger.Location.ALL);
		return employeeRoles;
	}

	@Override
	public EmployeeSalarySlip makeEmployeeSalarySlip(String employeeId, String monthYear, Blob salarySlip) {
		Logger logger = Logger.getInstance();
		EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
		try
		{
			employeeSalarySlip.setEmployeeId(employeeId);
			employeeSalarySlip.setMonthYear(monthYear);
			employeeSalarySlip.setSalarySlip(salarySlip);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		logger.info("Creating a new EmployeeSalarySlip instance.", this.getClass().getName(), Logger.Location.ALL);
		return employeeSalarySlip;
	}

	@Override
	public Logs makeLogs(String employeeId, Date logTime, String logLevel, String logMessage, String className)
	{
		Logger logger = Logger.getInstance();
		Logs logs = new Logs();
		try
		{
			logs.setEmployeeId(employeeId);
			logs.setLogTime(logTime);
			logs.setLogLevel(logLevel);
			logs.setLogMessage(logMessage);
			logs.setClassName(className);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.CONSOLE);
		}
		// Do not log in this function, it is used for logging.
		// If any logging is performed then the logic will go in infinite loop.
		return logs;
	}

	@Override
	public ConfigProperty makeConfigProperty(String propertyName, String propertyValue)
	{
		Logger logger = Logger.getInstance();
		ConfigProperty configProperty = new ConfigProperty();
		try
		{
			configProperty.setPropertyName(propertyName);
			configProperty.setPropertyValue(propertyValue);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		logger.info("Creating a new ConfigProperty instance.", this.getClass().getName(), Logger.Location.ALL);
		return configProperty;
	}

	@Override
	public EmployeeCTC makeEmptyEmployeeCTC()
	{
		return new EmployeeCTC();
	}

	@Override
	public EmployeeInfo makeEmptyEmployeeInfo()
	{
		return new EmployeeInfo();
	}

	@Override
	public EmployeeLeaveInfo makeEmptyEmployeeLeaveInfo()
	{
		return new EmployeeLeaveInfo();
	}

	@Override
	public EmployeeLogin makeEmptyEmployeeLogin()
	{
		return new EmployeeLogin();
	}

	@Override
	public EmployeeRoles makeEmptyEmployeeRoles()
	{
		return new EmployeeRoles();
	}

	@Override
	public EmployeeSalarySlip makeEmptyEmployeeSalarySlip()
	{
		return new EmployeeSalarySlip();
	}

	@Override
	public Logs makeEmptyLogs()
	{
		return new Logs();
	}

	@Override
	public ConfigProperty makeEmptyConfigProperty()
	{
		return new ConfigProperty();
	}
}