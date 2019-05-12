package com.hrm.rest.validationlogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.hrm.rest.controller.LeaveMessageConstants;
import com.hrm.rest.controller.Logger;
import com.hrm.rest.dbaccessor.EmployeeLeaveInfoAccessor;
import com.hrm.rest.model.EmployeeLeaveInfo;


public class ValidateOverlappingDates implements IValidate
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
			boolean isDatesNotOverLapping = validateoverlappingdates(property,resultSet);

			if(isDatesNotOverLapping && nextinproperty!=null)
			{
				resultSet.putAll(nextinproperty.Validate(property));
			}
		}
		catch (Exception e)
		{
		}
		return resultSet;
	}

	public boolean validateoverlappingdates(EmployeeLeaveInfo employeeLeaveInfo, HashMap<String,String> resultSet) throws Exception
	{
		Logger logger = Logger.getInstance();
		try
		{
			Date currentStartDate = employeeLeaveInfo.getStartDate();
			EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = new EmployeeLeaveInfoAccessor(employeeLeaveInfo);
			ArrayList<HashMap<String, String>> resultset = employeeLeaveInfoAccessor.getEmployeeLeaveDetails();

			DateFormat formatter = new SimpleDateFormat(LeaveMessageConstants.SQLDATEFORMATTYPE.getValue());
			String startDate = formatter.format(currentStartDate);

			String fromdate;

			for(HashMap<String,String> result: resultset)
			{
				fromdate = result.get("START_DATE");
				if(startDate.equals(fromdate))
				{
					resultSet.put("IsValid", "false");
					String errorMsg = LeaveMessageConstants.MSGOVERLAPPINGDATES.getValue();
					LeaveValidationHelper.addErrorToResultSet(errorMsg, resultSet);
					return false;
				}
			}
		}
		catch (Exception e)
		{
			logger.error("Error in validating for overlapping leaves.", ValidateOverlappingDates.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
		}
		return true;
	}
}
