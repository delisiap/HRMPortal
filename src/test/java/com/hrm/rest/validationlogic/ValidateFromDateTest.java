package com.hrm.rest.validationlogic;


import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.HashMap;

import com.hrm.rest.model.EmployeeLeaveInfo;

import org.junit.Test;


public class ValidateFromDateTest {
	@Test
	public void validateFromDateTestCases() throws ParseException{

		HashMap<String,String> resultstatus = new HashMap<String,String>();
		ValidateFromDate validatefromdate = new ValidateFromDate();

		EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();

		employeeLeaveInfo.setEmployeeId("employeeId");
		employeeLeaveInfo.setStartDate("2018-07-10");
		employeeLeaveInfo.setEndDate("2018-07-30");
		assertEquals(validatefromdate.validatefromdate(employeeLeaveInfo, resultstatus), false);
		
		employeeLeaveInfo.setEmployeeId("employeeId");
		employeeLeaveInfo.setStartDate("2019-10-10");
		employeeLeaveInfo.setEndDate("2019-10-12");
		assertEquals(validatefromdate.validatefromdate(employeeLeaveInfo, resultstatus), false);
		
		employeeLeaveInfo.setEmployeeId("employeeId");
		employeeLeaveInfo.setStartDate("2018-10-10");
		employeeLeaveInfo.setEndDate("2018-10-12");
		assertEquals(validatefromdate.validatefromdate(employeeLeaveInfo, resultstatus), true);
		
	}
}

