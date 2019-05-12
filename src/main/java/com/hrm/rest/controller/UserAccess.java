package com.hrm.rest.controller;

import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.HRMContext;
import com.hrm.rest.authenticator.Authenticator;
import com.hrm.rest.authenticator.EncryptPassword;
import com.hrm.rest.authenticator.InvalidUserException;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLoginAccessor;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.utility.Constants;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserAccess
{
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestParam(value = "empId", required = true) String empId,
			@RequestParam(value = "password", required = true) String password)
	{
		Map<String, Object> status = new HashMap<String, Object>();
		Logger logger = Logger.getInstance();
		try
		{
			Authenticator authenticator = new Authenticator();
			authenticator.processLogin(empId, password);
			status = Response.createResponse(Constants.RESPONSE_SUCCESS);
		}
		catch (InvalidUserException e)
		{
			status = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		catch (Exception e)
		{
			status = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		return status;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout()
	{
		SessionController sessionController = HRMContext.getSessionController();
		Logger logger = Logger.getInstance();
		try
		{
			if(sessionController.isUserLoggedIn())
			{
				String empId = sessionController.getLoggedInUserId();
				logger.info("Employee with id " + empId + " is logging out", this.getClass().getName(), Logger.Location.ALL);
				sessionController.processLogout();
			}
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public HashMap<String, Object> changePassword(
		@RequestParam(value = "password", required = true) String password,
		@RequestParam(value = "newPassword", required = true) String newPassword)
	{
		Logger logger = Logger.getInstance();
		HashMap<String, Object> response = new HashMap<String, Object>();
		try
		{
			Authenticator authenticator = new Authenticator();
			ModelFactory modelFactory = ModelFactory.getInstance();
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeLogin employeeLogin = modelFactory.makeEmptyEmployeeLogin();
			SessionController sessionController = HRMContext.getSessionController();
			EncryptPassword encryptPassword = new EncryptPassword(newPassword);
			EmployeeLoginAccessor employeeLoginAccessor;
			String employeeId = sessionController.getLoggedInUserId();

			//Verify whether given password is valid or not.
			authenticator.processLogin(employeeId, password);
			//If given password is valid, set newPassword as password for employee with given employee Id.
			employeeLogin.setEmployeeId(employeeId);
			employeeLogin.setPassword(encryptPassword.getHashedPassword());

			employeeLoginAccessor = databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin);
			employeeLoginAccessor.updateEmployeeLogin();
			response = Response.createResponse(Constants.RESPONSE_SUCCESS);
		}
		catch (InvalidUserException e)
		{
			response = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		catch (Exception e)
		{
			response = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		return response;
	}
}