package com.hrm.rest.dbaccessor;

import java.sql.CallableStatement;
import java.sql.Types;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeInfo;

public class EmployeeInfoAccessor
{
	private EmployeeInfo employeeInfo;

	public EmployeeInfoAccessor(EmployeeInfo employeeInfo)
	{
		this.employeeInfo = employeeInfo;
	}

	public void addEmployee() throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbconnector = DatabaseConnector.getInstance();
		try
		{
			java.sql.Date dateOfBirth = new java.sql.Date(employeeInfo.getDateOfBirth().getTime());
			java.sql.Date dateofJoining = new java.sql.Date(employeeInfo.getDateOfBirth().getTime());

			CallableStatement callableStatement = dbconnector.getStatement(DatabaseConstants.EMPLOYEEINFO_INSERT_STATEMENT);
			callableStatement.setString(1, employeeInfo.getEmployeeId());
			callableStatement.setString(2, employeeInfo.getEmployeeName());
			callableStatement.setInt(3, employeeInfo.getRoleId());
			callableStatement.setDate(4, dateOfBirth);
			callableStatement.setDate(5, dateofJoining);
			callableStatement.setLong(6, employeeInfo.getPhoneNo());
			callableStatement.setString(7, employeeInfo.getEmailId());
			callableStatement.setString(8, employeeInfo.getAlternateEmailId());
			callableStatement.setString(9, employeeInfo.getAddress());
			callableStatement.setInt(10, employeeInfo.getNoOfLeaves());
			callableStatement.executeUpdate();
			callableStatement.close();
		}
		catch (Exception e)
		{
			logger.error("Error in adding employee to database.", this.getClass().getName(), Logger.Location.ALL);
			throw e;
		}
	}

	public void deleteEmployee() throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbconnector = DatabaseConnector.getInstance();
		try
		{
			CallableStatement callableStatement = dbconnector.getStatement(DatabaseConstants.EMPLOYEEINFO_DELETE_STATEMENT);
			callableStatement.setString(1, employeeInfo.getEmployeeId());
			callableStatement.execute();
			callableStatement.close();
		}
		catch (Exception e)
		{
			logger.error("Error in deleting employee.", this.getClass().getName(), Logger.Location.ALL);
			throw e;
		}
	}

	public EmployeeInfo getEmployeeInfo() throws Exception
	{
		DatabaseConnector dbConnector = DatabaseConnector.getInstance();
		Logger logger = Logger.getInstance();
		CallableStatement callableStatement;
		try
		{
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEEINFO_SELECT_STATEMENT);
			callableStatement.setString(1, employeeInfo.getEmployeeId());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.DATE);
			callableStatement.registerOutParameter(5, Types.DATE);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.INTEGER);
			callableStatement.execute();
			employeeInfo.setEmployeeName(callableStatement.getString(2));
			employeeInfo.setRoleId(callableStatement.getInt(3));
			employeeInfo.setDateOfBirth(callableStatement.getDate(4));
			employeeInfo.setDateOfJoining(callableStatement.getDate(5));
			employeeInfo.setPhoneNo(Long.toString(callableStatement.getLong(6)));
			employeeInfo.setEmailId(callableStatement.getString(7));
			employeeInfo.setAlternateEmailId(callableStatement.getString(8));
			employeeInfo.setAddress(callableStatement.getString(9));
			employeeInfo.setNoOfLeaves(callableStatement.getInt(10));
			callableStatement.close();
		}
		catch (Exception e)
		{
			logger.error("Error in fetching employee information from database.", this.getClass().getName(), Logger.Location.ALL);
			throw e;
		}
		return employeeInfo;
	}

	public void updateEmployeeInfo() throws Exception
	{
		DatabaseConnector dbConnector = DatabaseConnector.getInstance();
		Logger logger = Logger.getInstance();
		CallableStatement callableStatement;
		try
		{
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEEINFO_UPDATE_STATEMENT);
			callableStatement.setString(1, employeeInfo.getEmployeeId());
			callableStatement.setLong(2, employeeInfo.getPhoneNo());
			callableStatement.setString(3, employeeInfo.getAlternateEmailId());
			callableStatement.setString(4, employeeInfo.getAddress());
			callableStatement.execute();
			callableStatement.close();
		}
		catch (Exception e)
		{
			logger.error("Error in updating employee information.", this.getClass().getName(), Logger.Location.ALL);
			throw e;
		}
	}
}