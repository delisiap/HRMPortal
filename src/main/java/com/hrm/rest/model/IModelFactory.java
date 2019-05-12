package com.hrm.rest.model;

import java.sql.Blob;
import java.util.Date;

public interface IModelFactory
{
	public EmployeeCTC makeEmployeeCTC(String employeeId, int ctcYear, Blob ctc);
	public EmployeeCTC makeEmptyEmployeeCTC();

	public EmployeeInfo makeEmployeeInfo(String employeeName, String employeeId, String emailId, String phoneNo, String address, String alternateEmailId, int roleId, String dateOfJoining, String dateOfBirth, int noOfLeaves);
	public EmployeeInfo makeEmptyEmployeeInfo();

	public EmployeeLeaveInfo makeEmployeeLeaveInfo(String employeeId, String startDate, String endDate, String status);
	public EmployeeLeaveInfo makeEmptyEmployeeLeaveInfo();

	public EmployeeLogin makeEmployeeLogin(String employeeId, String passwordHash);
	public EmployeeLogin makeEmptyEmployeeLogin();

	public EmployeeRoles makeEmployeeRoles(String roleId, String roleName);
	public EmployeeRoles makeEmptyEmployeeRoles();

	public EmployeeSalarySlip makeEmployeeSalarySlip(String employeeId, String monthYear, Blob salarySlip);
	public EmployeeSalarySlip makeEmptyEmployeeSalarySlip();

	public Logs makeLogs(String employeeId, Date logTime, String logLevel, String logMessage, String className);
	public Logs makeEmptyLogs();

	public ConfigProperty makeConfigProperty(String propertyName, String propertyValue);
	public ConfigProperty makeEmptyConfigProperty();
}