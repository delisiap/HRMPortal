package com.hrm.rest.dbaccessor;

import java.util.ArrayList;

import com.hrm.rest.model.ConfigProperty;
import com.hrm.rest.model.EmployeeCTC;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.EmployeeSalarySlip;
import com.hrm.rest.model.Logs;

public class DatabaseAccessorFactory implements IDatabaseAccessorFactory
{
	private static DatabaseAccessorFactory databaseAccessorFactory = null;

	public static DatabaseAccessorFactory getInstance()
	{
		if (databaseAccessorFactory == null)
		{
			databaseAccessorFactory = new DatabaseAccessorFactory();
		}
		return databaseAccessorFactory;
	}

	@Override
	public EmployeeCTCAccessor makeEmployeeCTCAccessor(EmployeeCTC employeeCTC)
	{
		return new EmployeeCTCAccessor(employeeCTC);
	}

	@Override
	public EmployeeInfoAccessor makeEmployeeInfoAccessor(EmployeeInfo employeeInfo)
	{
		return new EmployeeInfoAccessor(employeeInfo);
	}

	@Override
	public EmployeeLeaveInfoAccessor makeEmployeeLeaveInfoAccessor(EmployeeLeaveInfo employeeLeaveInfo)
	{
		return new EmployeeLeaveInfoAccessor(employeeLeaveInfo);
	}

	@Override
	public EmployeeLoginAccessor makeEmployeeLoginAccessor(EmployeeLogin employeeLogin)
	{
		return new EmployeeLoginAccessor(employeeLogin);
	}

	@Override
	public EmployeeSalarySlipAccessor makeEmployeeSalarySlipAccessor(EmployeeSalarySlip employeeSalarySlip)
	{
		return new EmployeeSalarySlipAccessor(employeeSalarySlip);
	}

	@Override
	public LogsAccessor makeLogsAccessor(Logs logs)
	{
		return new LogsAccessor(logs);
	}

	@Override
	public ConfigPropertyAccessor makeConfigPropertyAccessor(ArrayList<ConfigProperty> configProperties)
	{
		return new ConfigPropertyAccessor(configProperties);
	}
}