package com.hrm.rest.model;

import java.util.Date;

public class EmployeeInfo
{
	private String employeeName;
	private String employeeId;
	private String emailId;
	private long phoneNo;
	private String address;
	private String alternateEmailId;
	private int roleId;
	private Date dateOfJoining;
	private Date dateOfBirth;
	private int noOfLeaves;

	public int getNoOfLeaves()
	{
		return noOfLeaves;
	}

	public String getAddress()
	{
		return address;
	}

	public String getAlternateEmailId()
	{
		return alternateEmailId;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public String getEmployeeId()
	{
		return employeeId;
	}

	public Date getDateOfJoining()
	{
		return dateOfJoining;
	}

	public String getEmployeeName()
	{
		return employeeName;
	}

	public long getPhoneNo()
	{
		return phoneNo;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setNoOfLeaves(int noOfLeaves)
	{
		this.noOfLeaves = noOfLeaves;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setAlternateEmailId(String alternateEmailId)
	{
		this.alternateEmailId = alternateEmailId;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}

	public void setDateOfJoining(Date dateOfJoining)
	{
		this.dateOfJoining = dateOfJoining;
	}

	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = Long.parseLong(phoneNo);
	}

	public void setRoleId(int roleId) throws Exception
	{
		this.roleId = roleId;
	}
}