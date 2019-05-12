package com.hrm.rest.controller;

import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.employee.EmployeeHelper;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.role.Role;
import com.hrm.rest.role.Roles;
import com.hrm.rest.utility.Constants;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/admin")
public class Admin
{
	@RequestMapping(value = "/addemployee", method = RequestMethod.POST)
	public Map<String, Object> addEmployee(@RequestParam(value = "employeeName", required = true) String employeeName,
			@RequestParam(value = "roleName", required = true) String roleName,
			@RequestParam(value = "dateOfBirth", required = true) String dateOfBirth,
			@RequestParam(value = "dateOfJoining", required = true) String dateOfJoining,
			@RequestParam(value = "phoneNo", required = true) String phoneNo,
			@RequestParam(value = "emailId") String emailId,
			@RequestParam(value = "alternateEmailId") String alternateEmailId,
			@RequestParam(value = "noOfLeaves") String leaves, @RequestParam(value = "employeeId") String employeeId,
			@RequestParam(value = "address") String address)
	{
		Logger logger = Logger.getInstance();
		ModelFactory modelFactory = ModelFactory.getInstance();
		Map<String, Object> res = new HashMap<String, Object>();
		try
		{
			int noOfLeaves = Integer.parseInt(leaves);
			Role role = EmployeeHelper.getRoleByRoleName(roleName);
			EmployeeInfo emp = modelFactory.makeEmployeeInfo(employeeName, employeeId, emailId, phoneNo, address,
					alternateEmailId, role.getRoleId(), dateOfJoining, dateOfBirth, noOfLeaves);

			EmployeeHelper.addEmployee(emp);

			res = Response.createResponse(Constants.RESPONSE_SUCCESS);
			logger.info("Adding employee with id: " + employeeId, this.getClass().getName(), Logger.Location.ALL);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			res = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/removeemployee", method = RequestMethod.POST)
	public Map<String, Object> removeEmployee(@RequestParam(value = "employeeId", required = true) String employeeId)
	{
		Logger logger = Logger.getInstance();
		ModelFactory modelFactory = ModelFactory.getInstance();
		Map<String, Object> res = new HashMap<String, Object>();
		try
		{
			EmployeeInfo emp = modelFactory.makeEmptyEmployeeInfo();
			emp.setEmployeeId(employeeId);

			EmployeeHelper.deleteEmployee(emp);

			logger.info("Deleting employee with id: " + employeeId, this.getClass().getName(), Logger.Location.ALL);
			res = Response.createResponse(Constants.RESPONSE_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			res = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/getrolenames", method = RequestMethod.POST)
	public Map<String, Object> getRoleNames()
	{
		Logger logger = Logger.getInstance();
		Map<String, Object> res = new HashMap<String, Object>();
		try
		{
			Roles roles = Roles.getInstance();
			res = Response.createResponse(Constants.RESPONSE_SUCCESS, roles.getRoleNames());
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			res = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
		}
		return res;
	}
}