package com.hrm.rest.controller;

import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.HRMContext;
import com.hrm.rest.controller.Logger.Location;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLeaveInfoAccessor;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.validationlogic.LeaveValidation;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/leave")
public class LeaveApplication
{
	HashMap<String, String> resultstatus;

	@ResponseBody
	@RequestMapping(value = "/applyLeave", method = RequestMethod.POST)
	public Map<String, String> applyLeave(@RequestBody EmployeeLeaveInfo employeeLeaveInfo) throws Exception
	{
		Logger logger = Logger.getInstance();
		resultstatus = new HashMap<String, String>();
		String statuskey = LeaveMessageConstants.STATUSKEY.getValue();
		String msgkey = LeaveMessageConstants.MESSAGEKEY.getValue();
		try
		{
			LeaveValidation leavevalidation = new LeaveValidation(employeeLeaveInfo);
			resultstatus = leavevalidation.validateLeaveDetails(resultstatus);
			String validateLeaveDetails = resultstatus.get("IsValid");

			if (validateLeaveDetails==LeaveMessageConstants.LEAVEDETAILSVALID.getValue())
			{
				DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
				EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo);
				employeeLeaveInfo.setStatus(LeaveMessageConstants.NEWLEAVEVAL.getValue());
				Boolean isLeaveApplied = employeeLeaveInfoAccessor.insertLeaveDetails();
				if (isLeaveApplied)
				{
					resultstatus.put(statuskey, LeaveMessageConstants.SUCCESSVALUE.getValue());
					resultstatus.put(msgkey, LeaveMessageConstants.MSGSUCCESSLEAVE.getValue());
				}
				else
				{
					resultstatus.put(statuskey, LeaveMessageConstants.FAILVALUE.getValue());
					resultstatus.put(msgkey, LeaveMessageConstants.MSGFAILEDTOAPPLY.getValue());
				}
			}
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Location.ALL);
		}
		return resultstatus;
	}

	@ResponseBody
	@RequestMapping(value = "/viewRemainingLeave", method = RequestMethod.POST)
	public Map<String, String> getRemainingLeave() throws Exception
	{
		EmployeeLeaveInfo employeeLeaveInfo = new EmployeeLeaveInfo();
		SessionController sessionController = HRMContext.getSessionController();
		String employeeId = sessionController.getLoggedInUserId();
		employeeLeaveInfo.setEmployeeId(employeeId);

		Logger logger = Logger.getInstance();
		resultstatus = new HashMap<String, String>();
		String statuskey = LeaveMessageConstants.STATUSKEY.getValue();
		String msgkey = LeaveMessageConstants.DATAKEY.getValue();
		String remainingLeaveCount;

		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(employeeLeaveInfo);
			remainingLeaveCount = employeeLeaveInfoAccessor.getRemainingLeaveCount();
			if(remainingLeaveCount==null)
			{
				resultstatus.put(statuskey, LeaveMessageConstants.FAILVALUE.getValue());
			}
			else
			{
				resultstatus.put(statuskey, LeaveMessageConstants.SUCCESSVALUE.getValue());
				resultstatus.put(msgkey, remainingLeaveCount);
			}
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Location.ALL);
		}
		return resultstatus;
	}
}