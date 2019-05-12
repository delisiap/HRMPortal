package com.hrm.rest.dbaccessor;

import java.util.ArrayList;

import com.hrm.rest.model.ConfigProperty;
import com.hrm.rest.model.EmployeeCTC;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.EmployeeSalarySlip;
import com.hrm.rest.model.Logs;

public interface IDatabaseAccessorFactory
{
	public EmployeeCTCAccessor makeEmployeeCTCAccessor(EmployeeCTC employeeCTC);

	public EmployeeInfoAccessor makeEmployeeInfoAccessor(EmployeeInfo employeeInfo);

	public EmployeeLeaveInfoAccessor makeEmployeeLeaveInfoAccessor(EmployeeLeaveInfo employeeLeaveInfo);

	public EmployeeLoginAccessor makeEmployeeLoginAccessor(EmployeeLogin employeeLogin);

	public EmployeeSalarySlipAccessor makeEmployeeSalarySlipAccessor(EmployeeSalarySlip employeeSalarySlip);

	public LogsAccessor makeLogsAccessor(Logs logs);

	public ConfigPropertyAccessor makeConfigPropertyAccessor(ArrayList<ConfigProperty> configProperties);
}