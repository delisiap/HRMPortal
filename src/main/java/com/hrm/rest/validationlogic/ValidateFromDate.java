package com.hrm.rest.validationlogic;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.hrm.rest.controller.LeaveMessageConstants;
import com.hrm.rest.model.EmployeeLeaveInfo;

public class ValidateFromDate implements IValidate
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
			boolean isFromDateValid = validatefromdate(property,resultSet);

			if(isFromDateValid && nextinproperty!=null)
			{
				resultSet.putAll(nextinproperty.Validate(property));
			}
		}
		catch (ParseException e)
		{
			// Handle Parse Exception Here
			return resultSet;
		}
		catch (Exception e)
		{
			throw e;
		}
		return resultSet;
	}

	public boolean validatefromdate(EmployeeLeaveInfo employeeLeaveInfo,HashMap<String,String> resultstatus) throws ParseException
	{
		Boolean isdatevalid = LeaveValidationHelper.validatedate(employeeLeaveInfo.getStartDate());
		if (isdatevalid)
		{
			// Date type is valid
			Date fromDate = employeeLeaveInfo.getStartDate();
			Date currentDate = LeaveValidationHelper.formatDate(new Date());

			if (LeaveValidationHelper.getDayDifference(fromDate,currentDate) < 0)
			{
				// from date is before currentdate
				LeaveValidationHelper.addErrorToResultSet(LeaveMessageConstants.MSGOLDFROMDATE.getValue(), resultstatus);
				return false;
			}

			Calendar calfromdate = Calendar.getInstance();
			Calendar calcurrentdate = Calendar.getInstance();
			calfromdate.setTime(fromDate);
			calcurrentdate.setTime(currentDate);

			if (calfromdate.get(Calendar.YEAR) > calcurrentdate.get(Calendar.YEAR))
			{
				// Next year dates can not be set.
				LeaveValidationHelper.addErrorToResultSet(LeaveMessageConstants.MSGFROMDATENEXTYEAR.getValue(), resultstatus);
				return false;
			}
			return true;
		}
		LeaveValidationHelper.addErrorToResultSet(LeaveMessageConstants.MSGFROMDATEINVALID.getValue(), resultstatus);
		return false;
	}
}