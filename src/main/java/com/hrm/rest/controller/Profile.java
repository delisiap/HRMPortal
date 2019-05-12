package com.hrm.rest.controller;

import java.util.HashMap;

import com.hrm.rest.HRMContext;
import com.hrm.rest.employee.EmployeeHelper;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.utility.Constants;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/profile")
public class Profile
{
	@RequestMapping(value = "/getemployeeinfo", method = RequestMethod.POST)
	public HashMap<String, Object> getEmployeeInfo(
		@RequestParam(value = "employeeId", required = false) String employeeId)
	{
		Logger logger = Logger.getInstance();
		HashMap<String, Object> response = new HashMap<String, Object>();
		try
		{
			if(null == employeeId)
			{
				SessionController sessionController = HRMContext.getSessionController();
				employeeId = sessionController.getLoggedInUserId();
			}
			logger.info("Getting employee details for employee: " + employeeId, this.getClass().getName(), Logger.Location.ALL);
			EmployeeInfo employeeInfo = EmployeeHelper.getEmployeeInfo(employeeId);
			response = Response.createResponse(Constants.RESPONSE_SUCCESS, employeeInfo);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			response = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/updateemployeeinfo", method = RequestMethod.POST)
	public HashMap<String, Object> updateEmployeeInfo(
		@RequestParam(value = "employeeId", required = false) String employeeId,
		@RequestParam(value = "phoneNo", required = false) String phoneNo,
		@RequestParam(value = "alternateEmailId", required = false) String alternateEmailId,
		@RequestParam(value = "address", required = false) String address)
	{
		Logger logger = Logger.getInstance();
		HashMap<String, Object> response = new HashMap<String, Object>();
		try
		{
			ModelFactory modelFactory = ModelFactory.getInstance();
			EmployeeInfo employeeInfo = modelFactory.makeEmptyEmployeeInfo();

			if(null == employeeId)
			{
				SessionController sessionController = HRMContext.getSessionController();
				employeeId = sessionController.getLoggedInUserId();
			}
			employeeInfo.setEmployeeId(employeeId);
			employeeInfo.setPhoneNo(phoneNo);
			employeeInfo.setAlternateEmailId(alternateEmailId);
			employeeInfo.setAddress(address);
			logger.info("Updating employee details for employee: " + employeeId, this.getClass().getName(), Logger.Location.ALL);
			EmployeeHelper.updateEmployeeInfo(employeeInfo);
			response = Response.createResponse(Constants.RESPONSE_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			response = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
		}
		return response;
	}
}