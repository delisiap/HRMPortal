package com.hrm.rest.dbaccessor;

import java.sql.CallableStatement;
import java.sql.Types;

import com.hrm.rest.authenticator.InvalidUserException;
import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.dbaccessor.DatabaseConstants;
import com.hrm.rest.model.EmployeeLogin;

public class EmployeeLoginAccessor
{
	private EmployeeLogin employeeLogin;

	public EmployeeLoginAccessor(EmployeeLogin employeeLogin)
	{
		this.employeeLogin = employeeLogin;
	}

	public String fetchPasswordFromDatabase() throws Exception
	{
		DatabaseConnector dbConnector = DatabaseConnector.getInstance();
		String password = null;
		Logger logger;
		try
		{
			String getPasswordQuery = DatabaseConstants.EMPLOYEELOGIN_GETPASSWORD_STATEMENT;
			logger = Logger.getInstance();
			CallableStatement callableStatement = dbConnector.getStatement(getPasswordQuery);
			callableStatement.setString(1, employeeLogin.getEmployeeId());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			int value = callableStatement.executeUpdate();
			if (value > 0)
			{
				password = String.valueOf(callableStatement.getObject(2));
			}
			else
			{
				logger = Logger.getInstance();
				logger.error(new InvalidUserException(), this.getClass().getName(), Logger.Location.ALL);
			}
			callableStatement.close();
		}
		catch (Exception e)
		{
			logger = Logger.getInstance();
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			throw e;
		}
		return password;
	}

	public void addEmployeeLogin() throws Exception
	{
		DatabaseConnector dbConnector = DatabaseConnector.getInstance();

		CallableStatement callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_ADDLOGIN_STATEMENT);
		callableStatement.setString(1, employeeLogin.getPassword());
		callableStatement.setString(2, employeeLogin.getEmployeeId());

		callableStatement.execute();
		callableStatement.close();
	}

	public void deleteEmployeeLogin() throws Exception
	{
		DatabaseConnector dbConnector = DatabaseConnector.getInstance();

		CallableStatement callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_DELETE_STATEMENT);
		callableStatement.setString(1, employeeLogin.getEmployeeId());

		callableStatement.execute();
		callableStatement.close();
	}

	public void updateEmployeeLogin() throws Exception
	{
		DatabaseConnector dbConnector = DatabaseConnector.getInstance();
		CallableStatement callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEELOGIN_UPDATE_STATEMENT);
		callableStatement.setString(1, employeeLogin.getEmployeeId());
		callableStatement.setString(2, employeeLogin.getPassword());
		callableStatement.execute();
		callableStatement.close();
	}
}