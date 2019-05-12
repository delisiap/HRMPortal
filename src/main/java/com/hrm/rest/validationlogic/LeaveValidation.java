package com.hrm.rest.validationlogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.hrm.rest.controller.LeaveMessageConstants;
import com.hrm.rest.dbaccessor.EmployeeLeaveInfoAccessor;
import com.hrm.rest.model.EmployeeLeaveInfo;

public class LeaveValidation
{
	public EmployeeLeaveInfo employeeLeaveInfo;

	public LeaveValidation(EmployeeLeaveInfo employeeLeaveInfo)
	{
		this.employeeLeaveInfo = employeeLeaveInfo;
	}

	public HashMap<String,String> validateLeaveDetails(HashMap<String,String> resultSet) throws Exception
	{
		IValidate validator = SetupValidatorChain();
		HashMap<String,String> validationResultSet = validator.Validate(employeeLeaveInfo);

		if(validationResultSet.size() > 0)
		{
			// if Size is greater than zero, an error has occured
			// set IsValid as false
			resultSet.putAll(validationResultSet);
			resultSet.put("IsValid",LeaveMessageConstants.LEAVEDETAILSINVALID.getValue());

		}
		else
		{
			// if result set has no validation errors
			//set IsValid as true
			resultSet.put("IsValid",LeaveMessageConstants.LEAVEDETAILSVALID.getValue());
		}
		return resultSet;
	}

	// Not a validation
	public long calculateRemainingLeaves(ArrayList<HashMap<String, String>> appliedLeaves) throws ParseException{
		Date startDate;
		Date endDate;
		int remainingLeavesCount = employeeLeaveInfo.leavedatedifference;
		String dateFormatType = LeaveMessageConstants.SQLDATEFORMATTYPE.getValue();
		try{
			for(HashMap<String, String> currentleave : appliedLeaves)
			{
				if(!EmployeeLeaveInfoAccessor.isLeaveStatusReject(currentleave.get("STATUS")))
				{
					startDate=new SimpleDateFormat(dateFormatType).parse(currentleave.get("START_DATE"));
					endDate=new SimpleDateFormat(dateFormatType).parse(currentleave.get("END_DATE"));
					remainingLeavesCount = remainingLeavesCount- ((int)LeaveValidationHelper.getDayDifference(endDate, startDate)+1);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return remainingLeavesCount;
	}

	public IValidate SetupValidatorChain()
	{
		try
		{
			ValidateEmpID root = new ValidateEmpID();
			ValidateFromDate validateFromDate = new ValidateFromDate();
			ValidateToDate validateToDate = new ValidateToDate();
			ValidateOverlappingDates validateoverlappingdates = new ValidateOverlappingDates();

			root.checknextproperty(validateFromDate);
			validateFromDate.checknextproperty(validateToDate);
			validateToDate.checknextproperty(validateoverlappingdates);

			return root;
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
}