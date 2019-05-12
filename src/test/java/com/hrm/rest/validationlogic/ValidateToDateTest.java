package com.hrm.rest.validationlogic;


import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.HashMap;

import com.hrm.rest.model.EmployeeLeaveInfo;

import org.junit.Test;


public class ValidateToDateTest {
	@Test
	public void validateToDateTestCases() throws ParseException{

		HashMap<String,String> resultstatus = new HashMap<String,String>();
		ValidateToDate validateToDate = new ValidateToDate();

		EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();

		employeeLeaveInfo.setEmployeeId("employeeId");
		employeeLeaveInfo.setStartDate("2010-10-10");
		employeeLeaveInfo.setEndDate("2010-10-12");
		assertEquals(validateToDate.validatetodate(employeeLeaveInfo, resultstatus), true);
		
		employeeLeaveInfo.setEmployeeId("employeeId");
		employeeLeaveInfo.setStartDate("2010-10-10");
		employeeLeaveInfo.setEndDate("2010-12-10");
		assertEquals(validateToDate.validatetodate(employeeLeaveInfo, resultstatus), false);
		
		employeeLeaveInfo.setEmployeeId("employeeId");
		employeeLeaveInfo.setStartDate("2010-10-10");
		employeeLeaveInfo.setEndDate("2010-19-1");
		assertEquals(validateToDate.validatetodate(employeeLeaveInfo, resultstatus), false);
	}
}

