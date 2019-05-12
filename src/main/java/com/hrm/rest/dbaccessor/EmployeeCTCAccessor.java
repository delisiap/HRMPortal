package com.hrm.rest.dbaccessor;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import java.sql.Blob;
import java.sql.CallableStatement;
import com.hrm.rest.model.EmployeeCTC;
import java.sql.Types;

public class EmployeeCTCAccessor
{
	private EmployeeCTC employeeCTC;

	public EmployeeCTCAccessor(EmployeeCTC employeeCTC)
	{
		this.employeeCTC = employeeCTC;
	}

	public Blob getEmployeeCTC() throws Exception
	{
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		Blob ctc = null;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_SELECT_STATEMENT);
			callableStatement.setString(1, employeeCTC.getEmployeeId());
			callableStatement.setInt(2, employeeCTC.getCtcYear());
			callableStatement.registerOutParameter(3, Types.BLOB);
			callableStatement.execute();
			ctc = callableStatement.getBlob(3);
			callableStatement.close();
		}
		catch (Exception e)
		{
			throw e;
		}
		return ctc;
	}

	public void updateEmployeeCTC() throws Exception
	{
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_UPDATE_STATEMENT);
			callableStatement.setString(1, employeeCTC.getEmployeeId());
			callableStatement.setInt(2, employeeCTC.getCtcYear());
			callableStatement.setBlob(3, employeeCTC.getCtc());
			callableStatement.execute();
			callableStatement.close();
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public void deleteEmployeeCTC() throws Exception
	{
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_DELETE_STATEMENT);
			callableStatement.setString(1, employeeCTC.getEmployeeId());
			callableStatement.setInt(2, employeeCTC.getCtcYear());
			callableStatement.execute();
			callableStatement.close();
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public boolean insertCTC()
	{
		int result = 0;
		try
		{
			DatabaseConnector dbConnector = DatabaseConnector.getInstance();
			CallableStatement callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_INSERT_STATEMENT);
			callableStatement.setString(1, employeeCTC.getEmployeeId());
			callableStatement.setInt(2, employeeCTC.getCtcYear());
			callableStatement.setBlob(3, employeeCTC.getCtc());
			result = callableStatement.executeUpdate();
			callableStatement.close();

		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		return (result > 0 ? true : false);
	}
}