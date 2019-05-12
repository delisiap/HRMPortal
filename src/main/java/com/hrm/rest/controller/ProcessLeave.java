package com.hrm.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLeaveInfoAccessor;
import com.hrm.rest.model.EmployeeLeaveInfo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/processLeave")
public class ProcessLeave
{
	HashMap<String, String> resultsubmitleave;
	HashMap<String, ArrayList<HashMap<String, String>>> resultviewleave;

	@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public HashMap<String, String> processLeave(@RequestBody EmployeeLeaveInfo employeeLeaveInfo, HttpServletResponse response) throws Exception
	{
		Logger logger = Logger.getInstance();
		resultsubmitleave = new HashMap<String, String>();
		String statuskey = LeaveMessageConstants.STATUSKEY.getValue();
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo);
			Boolean isUpdateSuccessful = employeeLeaveInfoAccessor.updateEmployoeePendingLeave();
			if(isUpdateSuccessful)
			{
				resultsubmitleave.put(statuskey, LeaveMessageConstants.SUCCESSVALUE.getValue());
			}
			else
			{
				resultsubmitleave.put(statuskey, LeaveMessageConstants.FAILVALUE.getValue());
			}
		}
		catch(Exception ex)
		{
			logger.error("Error Processing leave for the employee " + employeeLeaveInfo.getEmployeeId() , this.getClass().getName(), Logger.Location.ALL);
			ex.printStackTrace();
		}
		return resultsubmitleave;
	}

	@ResponseBody
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Map<String, ArrayList<HashMap<String, String>>> viewProcessLeave() throws Exception
	{
		Logger logger = Logger.getInstance();
		resultviewleave = new HashMap<String, ArrayList<HashMap<String, String>>>();
		String datakey = LeaveMessageConstants.DATAKEY.getValue();
		try
		{
			EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo);
			HashMap<String, String> mapEmployeeNames = employeeLeaveInfoAccessor.getEmployeeNamesForID();
			ArrayList<HashMap<String, String>> resultset = employeeLeaveInfoAccessor.fetchPendingEmpLeaveDetails(mapEmployeeNames);
			resultviewleave.put(datakey, resultset);
		}
		catch(Exception ex)
		{
			logger.error("Error showing pending leaves " , this.getClass().getName(), Logger.Location.ALL);
			ex.printStackTrace();
		}
		return resultviewleave;
	}
}