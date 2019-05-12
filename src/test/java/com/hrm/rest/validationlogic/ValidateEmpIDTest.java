package com.hrm.rest.validationlogic;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import com.hrm.rest.model.EmployeeLeaveInfo;

import org.junit.Test;

public class ValidateEmpIDTest 
{
	ValidateEmpID validateempid ;

	@Test
	public void validEmployeeIDTest()
	{
		EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
		employeeLeaveInfo.setEmployeeId("employeeId");

		validateempid = new ValidateEmpID();
		HashMap<String,String> resultstatus = new HashMap<String,String>();
	
		resultstatus.put("IsValid", "true");
		validateempid.validateempid(employeeLeaveInfo, resultstatus);
		assertEquals(resultstatus.get("IsValid"),"true");
	}

	
	@Test
	public void validateNullEmpID()
	{
		EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
		employeeLeaveInfo.setEmployeeId(null);

		validateempid = new ValidateEmpID();
		HashMap<String,String> resultstatus = new HashMap<String,String>();
	
		resultstatus.put("IsValid", "true");
		validateempid.validateempid(employeeLeaveInfo, resultstatus);
		assertEquals(resultstatus.get("IsValid"),"false");
	}

	@Test
	public void validateEmpIDTestCases() 
	{
		HashMap<String,String> resultstatus = new HashMap<String,String>();
		 validateempid = new ValidateEmpID();

		EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
		
		employeeLeaveInfo.setEmployeeId("employeeId");
		assertEquals(validateempid.validateempid(employeeLeaveInfo, resultstatus), true);
	}
}