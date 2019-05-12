package com.hrm.rest.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hrm.rest.controller.LeaveMessageConstants;

public class EmployeeLeaveInfo
{
	private String employeeId;
	private Date startDate;
	private Date endDate;
	private int id;
	private String status;

	public int leavedatedifference = 30;

	public String getEmployeeId()
	{
		return employeeId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public String getStatus()
	{
		return status;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public void setEndDate(String endDate) throws ParseException
	{
		Date date = new SimpleDateFormat(LeaveMessageConstants.UIDATEFORMATTYPE.getValue()).parse(endDate);
		this.endDate = date;
	}

	public void setStartDate(String startDate)throws ParseException
	{
		Date date = new SimpleDateFormat(LeaveMessageConstants.UIDATEFORMATTYPE.getValue()).parse(startDate);
		this.startDate = date;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}