package com.hrm.rest.validationlogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.hrm.rest.controller.LeaveMessageConstants;

public class LeaveValidationHelper
{
	public static boolean isempty(String value)
	{
		if(isnull(value))
		{
			return true;
		}
		if(value.isEmpty())
		{
			return true;
		}
		return false;
	}

	public static boolean isnull(String value)
	{
		return value==null;
	}

	public static void addErrorToResultSet(String errorMsg, HashMap<String,String> resultstatus)
	{
		String currentMessage = "";
		String statusKey = LeaveMessageConstants.STATUSKEY.getValue();
		String errorMessageKey = LeaveMessageConstants.ERRORMESSAGEKEY.getValue();

		String failvalue = LeaveMessageConstants.FAILVALUE.getValue();

		if (resultstatus.get(statusKey) != failvalue)
		{
			resultstatus.put(statusKey, failvalue);
		}
		else
		{
			currentMessage = resultstatus.get(errorMessageKey) + " ";
		}
		resultstatus.put(errorMessageKey, currentMessage + errorMsg);
	}

	public static boolean validatedate(Date date)
	{
		if (date == null)
		{
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		try
		{
			cal.getTime();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public static long getDayDifference(Date endDate, Date startDate) throws ParseException
	{
		long datedifference = formatDate(endDate).getTime() - formatDate(startDate).getTime();
		long daydifference = datedifference / (1000 * 60 * 60 * 24);
		return daydifference;
	}

	// Not a validation
	public static Date formatDate(Date date) throws ParseException
	{
		DateFormat dateformatter = new SimpleDateFormat(LeaveMessageConstants.DATEFORMTTYPE.getValue());
		String dateString = dateformatter.format(date);
		Date parsedDate = dateformatter.parse(dateString);
		return parsedDate;
	}
}