package com.hrm.rest.validationlogic;

import java.util.HashMap;

import com.hrm.rest.controller.LeaveMessageConstants;
import com.hrm.rest.model.EmployeeLeaveInfo;

public class ValidateEmpID implements IValidate
{
	private IValidate nextinproperty;

	@Override
	public void checknextproperty(IValidate nextproperty)
	{
		this.nextinproperty = nextproperty;
	}

	@Override
	public HashMap<String,String> Validate(EmployeeLeaveInfo property)
	{
		HashMap<String,String> resultSet = new HashMap<>();
		try
		{
			boolean isEmployeeIdValid = validateempid(property,resultSet);

			if(isEmployeeIdValid && nextinproperty!=null)
			{
				resultSet.putAll(nextinproperty.Validate(property));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultSet;
	}

	public boolean validateempid(EmployeeLeaveInfo employeeLeaveInfo,HashMap<String,String> resultstatus)
	{
		String empID = employeeLeaveInfo.getEmployeeId();
		if (LeaveValidationHelper.isempty(empID))
		{
			resultstatus.put("IsValid","false");
			String errorMsg = LeaveMessageConstants.MSGMISSINGEMPID.getValue();
			LeaveValidationHelper.addErrorToResultSet(errorMsg, resultstatus);
			return false;
		}
		return true;
	}
}