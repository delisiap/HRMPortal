package com.hrm.rest.dbaccessor;

public class DatabaseConstants
{
	protected static final String EMPLOYEELOGIN_ADDLOGIN_STATEMENT = "{call add_login_details(?,?)}";
	protected static final String EMPLOYEELOGIN_DELETE_STATEMENT ="{call delete_login_details(?)}";
	protected static final String EMPLOYEELOGIN_GETPASSWORD_STATEMENT="{call get_password(?,?)}";
	protected static final String EMPLOYEELOGIN_UPDATE_STATEMENT="{call update_login_details(?,?)}";

	protected static final String EMPLOYEEINFO_INSERT_STATEMENT = "{call add_employee(?,?,?,?,?,?,?,?,?,?)}";
	protected static final String EMPLOYEEINFO_SELECT_STATEMENT = "{call get_employee(?,?,?,?,?,?,?,?,?,?)}";
	protected static final String EMPLOYEEINFO_UPDATE_STATEMENT = "{call update_employee(?,?,?,?)}";
	protected static final String EMPLOYEEINFO_DELETE_STATEMENT = "{call delete_employee(?)}";

	protected static final String EMPLOYEECTC_SELECT_STATEMENT = "{call get_ctc(?,?,?)}";
	protected static final String EMPLOYEECTC_INSERT_STATEMENT = "{call add_ctc(?,?,?)}";
	protected static final String EMPLOYEECTC_UPDATE_STATEMENT = "{call update_ctc(?,?,?)}";
	protected static final String EMPLOYEECTC_DELETE_STATEMENT = "{call delete_ctc(?,?,?)}";

	protected static final String EMPLOYEESALARYSLIP_INSERT_STATEMENT = "{call add_salary_slip(?,?,?)}";
	protected static final String EMPLOYEESALARYSLIP_SELECT_STATEMENT = "{call get_salary_slip(?,?,?)}";
	protected static final String EMPLOYEESALARYSLIP_UPDATE_STATEMENT = "{call update_salary_slip(?,?,?)}";
	protected static final String EMPLOYEESALARYSLIP_DELETE_STATEMENT = "{call delete_salary_slip(?,?,?)}";

	protected static final String EMPLEAVE_INSERT_LEAVE_STATEMENT = "{call add_leave(?,?,?,?)}";

	protected static final String EMPLEAVE_SELECT_EMPDETAILS_STATEMENT = "{call get_empname()}";
	protected static final String EMPLEAVE_SELECT_PENDINGLEAVE_STATEMENT = "{call get_leavedetails_bystatus(?)}";
	protected static final String EMPLEAVE_SELECT_REMAININGLEAVECOUNT_STATEMENT = "{call get_remainingleavecount(?,?)}";
	protected static final String EMPLEAVE_SELECT_LEAVEDETAILS_STATEMENT = "{call get_leavedetails_byemployee(?)}";
	protected static final String EMPLEAVE_UPDATE_REMAININGLEAVE_STATEMENT = "{call update_employee_remainingleave(?,?)}";
	protected static final String EMPLEAVE_UPDATE_LEAVESTATUS_STATEMENT = "{call update_employee_leavestatus(?,?,?)}";

	protected static final String LOGS_INSERT_STATEMENT = "{call insert_logs(?,?,?,?,?)}";

	protected static final String CONFIGPROPERTY_GET_STATEMENT = "{call get_configproperty()}";
	protected static final String CONFIGPROPERTY_UPDATE_STATEMENT = "{call update_configproperty(?,?)}";
}