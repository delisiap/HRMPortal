package com.hrm.rest.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.HRMContext;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLeaveInfoAccessor;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.model.ModelFactory;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/leave")
public class ViewLeave
{
	@RequestMapping(value = "/viewLeaveStatus", method = RequestMethod.POST)
	public Map<String, ArrayList<HashMap<String, String>>> viewLeave()
	{
		HashMap<String, ArrayList<HashMap<String, String>>> resultviewleave = new HashMap<String, ArrayList<HashMap<String, String>>>();
		SessionController sessionController = HRMContext.getSessionController();
		String employeeId = sessionController.getLoggedInUserId();
		String datakey = LeaveMessageConstants.DATAKEY.getValue();
		Logger logger = Logger.getInstance();
		DatabaseAccessorFactory databaseAccessorFactory;
		ModelFactory modelFactory;
		EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor;

		try
		{
			databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			modelFactory = ModelFactory.getInstance();
			EmployeeLeaveInfo employeeLeaveInfo = modelFactory.makeEmptyEmployeeLeaveInfo();
			employeeLeaveInfo.setEmployeeId(employeeId);
			employeeLeaveInfoAccessor = databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo);
			ArrayList<HashMap<String, String>> employeeleavedetails=employeeLeaveInfoAccessor.fetchEmpLeaveDetails();
			resultviewleave.put(datakey,employeeleavedetails);
		}
		catch (Exception e)
		{
			logger.error("Error showing employee leaves " , this.getClass().getName(), Logger.Location.ALL);
			e.printStackTrace();
		}
		return resultviewleave;
	}
}