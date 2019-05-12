package com.hrm.rest.authenticator;

import java.util.HashMap;

import com.hrm.rest.HRMContext;
import com.hrm.rest.controller.Logger;
import com.hrm.rest.controller.SessionController;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeLoginAccessor;
import com.hrm.rest.exceptionhandling.ExceptionMessages;
import com.hrm.rest.exceptionhandling.HRMExceptions;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.ModelFactory;

import org.springframework.stereotype.Component;

@Component
public class Authenticator
{
	public boolean processLogin(String empId, String password) throws InvalidUserException
	{
		SessionController sessionController = HRMContext.getSessionController();
		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
		ModelFactory modelFactory = ModelFactory.getInstance();
		HashMap<String,String> result = new HashMap<String,String>();
		Logger logger = Logger.getInstance();
		boolean valid = false;
		boolean logIn =false;
		try
		{
			UserValidate userValidate = new UserValidate(empId,password);
			result = userValidate.validateUser();
			if(result.get("IsValid").equals("true"))
			{
				EmployeeLogin employeeLogin = modelFactory.makeEmptyEmployeeLogin();
				employeeLogin.setEmployeeId(empId);
				EmployeeLoginAccessor employeeLoginAccessor = databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin);
				String dbpassword = employeeLoginAccessor.fetchPasswordFromDatabase();
				EncryptPassword encryptPassword = new EncryptPassword(password);
				valid = encryptPassword.validatePassword(dbpassword);
				logIn= true;
			}
			else
			{
				logIn =false;
				throw new HRMExceptions(ExceptionMessages.INVALID_PASSWORD);
			}
		}
		catch (Exception e)
		{
			logIn =false;
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		if (valid)
		{
			sessionController.processLogin(empId);
			logger.info("User with id " + empId + " logged in.", this.getClass().getName(), Logger.Location.ALL);
		}
		else
		{
			logger.info("User with id " + empId + " failed to login.", this.getClass().getName(), Logger.Location.ALL);
			throw new InvalidUserException();
		}
		return logIn;
	}
}