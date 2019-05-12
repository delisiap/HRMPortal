package com.hrm.rest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hrm.rest.controller.LeaveMessageConstants;

import org.junit.Test;

public class EmployeeLeaveInfoTest {
	@Test
	public void employeeLeaveInfoTestCases() throws ParseException{

		Exception expectedException=null;
		try
		{
			EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
			
			employeeLeaveInfo.setEmployeeId("E123");
			employeeLeaveInfo.setStartDate("2010-10-10");
			employeeLeaveInfo.setEndDate("2010-12-10");
			employeeLeaveInfo.setStatus("NEW");
			employeeLeaveInfo.setId(1);

			DateFormat dateformatter =new SimpleDateFormat(LeaveMessageConstants.UIDATEFORMATTYPE.getValue());
			
			Date startdate = dateformatter.parse("2010-10-10");
			Date enddate = dateformatter.parse("2010-12-10");
			
			assertEquals( "E123", employeeLeaveInfo.getEmployeeId());
			assertEquals( "NEW", employeeLeaveInfo.getStatus());
			assertEquals( 1, employeeLeaveInfo.getId());
			assertEquals(startdate, employeeLeaveInfo.getStartDate());
			assertEquals(enddate, employeeLeaveInfo.getEndDate());	
		}
		catch(Exception e)
		{
			expectedException=e;
		}
		assertNull(expectedException);
	}
}