package com.hrm.rest.dbaccessor;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeSalarySlip;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Types;

public class EmployeeSalarySlipAccessor
{
	private EmployeeSalarySlip employeeSalarySlip;

	public EmployeeSalarySlipAccessor(EmployeeSalarySlip employeeSalarySlip)
	{
		this.employeeSalarySlip = employeeSalarySlip;
	}

	public Blob getEmployeeSalarySlip() throws Exception
	{
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		Blob salarySlip = null;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_SELECT_STATEMENT);
			callableStatement.setString(1, employeeSalarySlip.getEmployeeId());
			callableStatement.setDate(2, employeeSalarySlip.getMonthYear());
			callableStatement.registerOutParameter(3, Types.BLOB);
			callableStatement.execute();
			salarySlip = callableStatement.getBlob(3);
			callableStatement.close();
		}
		catch (Exception e)
		{
			throw e;
		}
		return salarySlip;
	}

	public void updateEmployeeSalarySlip() throws Exception
	{
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_UPDATE_STATEMENT);
			callableStatement.setString(1, employeeSalarySlip.getEmployeeId());
			callableStatement.setDate(2, employeeSalarySlip.getMonthYear());
			callableStatement.setBlob(3, employeeSalarySlip.getSalarySlip());
			callableStatement.execute();
			callableStatement.close();
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public void deleteEmployeeSalarySlip() throws Exception
	{
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_DELETE_STATEMENT);
			callableStatement.setString(1, employeeSalarySlip.getEmployeeId());
			callableStatement.setDate(2, employeeSalarySlip.getMonthYear());
			callableStatement.execute();
			callableStatement.close();
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public boolean insertSalarySlip()
	{
		int result = 0;
		try
		{
			DatabaseConnector dbConnector = DatabaseConnector.getInstance();
			CallableStatement callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_INSERT_STATEMENT);
			callableStatement.setString(1, employeeSalarySlip.getEmployeeId());
			callableStatement.setDate(2, employeeSalarySlip.getMonthYear());
			callableStatement.setBlob(3, employeeSalarySlip.getSalarySlip());

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