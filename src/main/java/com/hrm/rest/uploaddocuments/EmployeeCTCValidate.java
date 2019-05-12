package com.hrm.rest.uploaddocuments;

import java.sql.Blob;
import java.util.HashMap;

import com.hrm.rest.model.EmployeeCTC;

public class EmployeeCTCValidate implements IValidateDocuments
{
	EmployeeCTC employeeCTC;

	public EmployeeCTCValidate(EmployeeCTC employeeCTC)
	{
		this.employeeCTC = employeeCTC;
	}

	public boolean isnull(String value)
	{
		return (value == null);
	}

	public boolean validateempid(HashMap<String,String> resultstatus)
	{
		String empID = employeeCTC.getEmployeeId();
		if (isnull(empID) || empID.isEmpty())
		{
			addErrorToResultSet(UploadDocumentConstants.INVALID_EMPLOYEE_ID, resultstatus);
			return false;
		}
		return true;
	}

	public boolean validateYear(HashMap<String,String> resultstatus)
	{
		int year = employeeCTC.getCtcYear();
		if(year > 2018 || year < 2000)
		{
			addErrorToResultSet(UploadDocumentConstants.INVALID_EMPLOYEECTC_YEAR, resultstatus);
			return false;
		}
		return true;
	}

	public boolean validateBlob(HashMap<String,String> resultstatus)
	{
		Blob blob = employeeCTC.getCtc();
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