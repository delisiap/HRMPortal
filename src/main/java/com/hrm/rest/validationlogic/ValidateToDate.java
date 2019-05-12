package com.hrm.rest.validationlogic;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import com.hrm.rest.controller.LeaveMessageConstants;
import com.hrm.rest.model.EmployeeLeaveInfo;

public class ValidateToDate implements IValidate
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
			boolean isToDateValid = validatetodate(property,resultSet);

			if(isToDateValid && nextinproperty!=null)
			{
				resultSet.putAll(nextinproperty.Validate(property));
			}
		}
		catch (ParseException e)
		{

			//Handle Parse Exception Here
			return resultSet;
		}
		catch (Exception e)
		{
			throw e;
		}
		return resultSet;
	}

	public boolean validatetodate(EmployeeLeaveInfo employeeLeaveInfo,HashMap<String,String> resultstatus) throws ParseException
	{
		Boolean isdatevalid = LeaveValidationHelper.validatedate(employeeLeaveInfo.getEndDate());
		if (isdatevalid)
		{
			Date toDate = employeeLeaveInfo.getEndDate();
			Date fromdate = employeeLeaveInfo.getStartDate();
			if (fromdate.compareTo(toDate) > 0)
			{
				LeaveValidationHelper.addErrorToResultSet(LeaveMessageConstants.MSGFROMDATEEXCEEDSTODATE.getValue(), resultstatus);
				return false;
			}
			long daydifference = LeaveValidationHelper.getDayDifference(toDate, fromdate);
			if (daydifference > employeeLeaveInfo.leavedatedifference)
			{
				String prefixErrorMsg = LeaveMessageConstants.MSGEXCEEDDAYSPREFIX.getValue();
				String suffixErrorMsg = LeaveMessageConstants.MSGEXCEEDDAYSSUFFIX.getValue();
				String errorMessage = prefixErrorMsg + employeeLeaveInfo.leavedatedifference + suffixErrorMsg;
				LeaveValidationHelper.addErrorToResultSet(errorMessage, resultstatus);
				return false;
			}
			return true;
		}
		LeaveValidationHelper.addErrorToResultSet(LeaveMessageConstants.MSGENDDATEINVALID.getValue(), resultstatus);
		return false;
	}
}
