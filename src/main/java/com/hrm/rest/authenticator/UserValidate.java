package com.hrm.rest.authenticator;

import java.util.HashMap;

import com.hrm.rest.model.EmployeeLogin;

public class UserValidate
{

	private EmployeeLogin employeeLogin;

	private static final String INVALID_EMPLOYEE_ID="Invalid Employee ID";
	private static final String NO_EMPLOYEE_PASSWORD="Enter Password ";

	public UserValidate(String employeeId, String password)
	{
		this.employeeLogin = new EmployeeLogin();
		employeeLogin.setEmployeeId(employeeId);
		employeeLogin.setPassword(password);
	}

	public boolean isnull(String value)
	{
		return (value == null);
	}


	public boolean validateempid(HashMap<String,String> resultstatus)
	{
		String empID = employeeLogin.getEmployeeId();
		if (isnull(empID)||empID.isEmpty())
		{
			addErrorToResultSet(INVALID_EMPLOYEE_ID, resultstatus);
			return false;
		}
		return true;
	}

	public boolean validateemppassword(HashMap<String,String> resultstatus)
	{
		String empPassword = employeeLogin.getPassword();
		if(isnull(empPassword)||empPassword.isEmpty())
		{
			addErrorToResultSet(NO_EMPLOYEE_PASSWORD, resultstatus);
			return false;
		}
		return true;
	}

	public HashMap<String,String> validateUser() throws Exception
	{
		HashMap<String,String> resultSet = new HashMap<String,String>();
		resultSet.put("IsValid", "false");
		Boolean isEmpIDValid = validateempid(resultSet);
		Boolean isPasswordValid = validateemppassword(resultSet);

		if(isEmpIDValid&&isPasswordValid)
		{
			resultSet.put("IsValid", "true");
		}
		return resultSet;
	}

	public void addErrorToResultSet(String errorMsg, HashMap<String,String> resultstatus)
	{
		String currentMessage = "";
		ValidationErrorTags validationErrorTags_status= ValidationErrorTags.getErrorTags("status");
		ValidationErrorTags validattinErrorTags_err = ValidationErrorTags.getErrorTags("error");
		ValidationErrorMessages validationErrorMessages_fail = ValidationErrorMessages.getErrorMessages("fail");

		if(resultstatus.get(validationErrorTags_status.toString()) != validationErrorMessages_fail.toString())
		{
			resultstatus.put(validationErrorTags_status.toString(), validationErrorMessages_fail.toString());
		}
		else
		{
			currentMessage = resultstatus.get(validattinErrorTags_err.toString())+" ";
		}
		resultstatus.put(errorMsg, currentMessage + errorMsg);
	}
}