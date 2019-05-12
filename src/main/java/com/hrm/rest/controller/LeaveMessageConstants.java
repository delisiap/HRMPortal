package com.hrm.rest.controller;

public enum LeaveMessageConstants
{
	DATEFORMTTYPE("yyyy/MM/dd"),
	UIDATEFORMATTYPE("yyyy-MM-dd"),
	SQLDATEFORMATTYPE("yyyy-MM-dd"),

	LEAVEDETAILSVALID("true"),
	LEAVEDETAILSINVALID("false"),

	LEAVEREJECTVAL("REJECT"),
	LEAVEAPPROVEVAL("APPROVE"),
	NEWLEAVEVAL("NEW"),

	STATUSKEY("status"),
	MESSAGEKEY("message"),
	DATAKEY("data"),
	ERRORMESSAGEKEY("error"),

	SUCCESSVALUE("success"),
	FAILVALUE("failure"),

	MSGSUCCESSLEAVE("Leave applied successfully."),

	MSGMISSINGEMPID("Employee ID is missing."),
	MSGOLDFROMDATE("From date should be greater than currrent date"),
	MSGFROMDATENEXTYEAR("You can apply leaves only for current year."),
	MSGFROMDATEINVALID("From date is invalid."),
	MSGFROMDATEEXCEEDSTODATE("From date exceeds to date"),
	MSGEXCEEDDAYSPREFIX("Leave can be applied only for a maximum of "),
	MSGEXCEEDDAYSSUFFIX(" days"),
	MSGENDDATEINVALID("To date is invalid."),
	MSGFAILEDTOAPPLY("Failed to apply for leave"),
	MSGOVERLAPPINGDATES("Check for applied leaves. Your leave dates are overlapping.");

	private String value;

	private LeaveMessageConstants(String val)
	{
		this.value = val;
	}

	public String getValue()
	{
		return this.value;
	}
}