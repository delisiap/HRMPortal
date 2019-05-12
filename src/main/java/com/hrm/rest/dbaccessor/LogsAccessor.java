package com.hrm.rest.dbaccessor;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.Logs;
import java.sql.CallableStatement;

public class LogsAccessor
{
	private Logs logs;

	public LogsAccessor(Logs logs)
	{
		this.logs = logs;
	}

	public void logInDatabase()
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbconnector = DatabaseConnector.getInstance();
		java.sql.Date logTime = new java.sql.Date(logs.getLogTime().getTime());
		try
		{
			CallableStatement callableStatement = dbconnector.getStatement(DatabaseConstants.LOGS_INSERT_STATEMENT);
			callableStatement.setString(1, logs.getEmployeeId());
			callableStatement.setDate(2, logTime);
			callableStatement.setString(3, logs.getLogLevel());
			callableStatement.setString(4, logs.getLogMessage());
			callableStatement.setString(5, logs.getClassName());
			callableStatement.executeUpdate();
			callableStatement.close();
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.CONSOLE);
		}
	}
}