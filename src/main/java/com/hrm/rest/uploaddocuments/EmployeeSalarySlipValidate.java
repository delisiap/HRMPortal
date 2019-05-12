package com.hrm.rest.uploaddocuments;

import java.sql.Blob;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import com.hrm.rest.model.EmployeeSalarySlip;

public class EmployeeSalarySlipValidate implements IValidateDocuments
{
	EmployeeSalarySlip employeeSalarySlip;

	public boolean isnull(String value)
	{
		return (value == null);
	}

	public EmployeeSalarySlipValidate(EmployeeSalarySlip employeeSalarySlip)
	{
		this.employeeSalarySlip = employeeSalarySlip;
	}

	public boolean validateempid(HashMap<String,String> resultstatus)
	{
		String empID = employeeSalarySlip.getEmployeeId();
		if (isnull(empID)||empID.isEmpty())
		{
			addErrorToResultSet(UploadDocumentConstants.INVALID_EMPLOYEE_ID, resultstatus);
			return false;
		}
		return true;
	}

	public boolean validateYear(HashMap<String,String> resultstatus)
	{
		Date monthYear = employeeSalarySlip.getMonthYear();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(monthYear);
		int formYear = calendar.get(Calendar.YEAR);
		boolean validYear = false;
		if(formYear == Calendar.getInstance().get(Calendar.YEAR))
		{
			validYear = true;
		}
		return 	validYear;
	}

	public boolean validateBlob(HashMap<String,String> resultstatus)
	{
		Blob blob = employeeSalarySlip.getSalarySlip();
		boolean isBlobValid=false;
		try
		{
			if(blob.length()>0)
			{
				isBlobValid=true;
			}
		}
		catch(Exception e)
		{
			isBlobValid=false;
		}
		return isBlobValid;
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

	public HashMap<String,String> validateDocument()
	{
		HashMap<String,String> resultSet = new HashMap<String,String>();
		resultSet.put("IsValid", "false");
		Boolean isEmpIDValid = validateempid(resultSet);
		Boolean isYeadValid = validateYear(resultSet);
		Boolean isBlobValid = validateBlob(resultSet);

		if(isEmpIDValid&&isYeadValid&&isBlobValid)
		{
			resultSet.put("IsValid", "true");
		}
		else
		{
			resultSet.put("IsValid", "false");
		}
		return resultSet;
	}
}