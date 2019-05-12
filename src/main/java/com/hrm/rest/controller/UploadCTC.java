package com.hrm.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeCTCAccessor;
import com.hrm.rest.exceptionhandling.ExceptionErrorCode;
import com.hrm.rest.exceptionhandling.ExceptionMessages;
import com.hrm.rest.exceptionhandling.HRMExceptions;
import com.hrm.rest.uploaddocuments.EmployeeCTCUpload;
import com.hrm.rest.utility.Constants;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/profile")
public class UploadCTC
{
	@ResponseBody
	@RequestMapping(value = "/uploadCTC", method = RequestMethod.POST)
	public Map<String, String> uploadCTC(EmployeeCTCUpload ctcupload, HttpServletResponse response) throws HRMExceptions
	{
		Logger logger = Logger.getInstance();
		HashMap<String, String> resultstatus = new HashMap<String, String>();
		DatabaseAccessorFactory databaseAccessorFactory;
		EmployeeCTCAccessor employeeCTCAccessor;
		HashMap<String, String> resultSet = new HashMap<String, String>();

		try
		{
			String employeeId = ctcupload.getEmpId();
			databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			resultSet = ctcupload.validateCTC();
			boolean result = false;
			if (resultSet.get("IsValid").equals("true"))
			{
				employeeCTCAccessor = databaseAccessorFactory.makeEmployeeCTCAccessor(ctcupload.getEmployeeCTC());
				result = employeeCTCAccessor.insertCTC();
				if (result)
				{
					resultstatus.put(Constants.RESPONSE_STATUS, Constants.RESPONSE_SUCCESS);
					logger.info("Document Uploaded Successfully for " + employeeId, this.getClass().getName(),
							Logger.Location.ALL);
				}
				else
				{
					resultstatus.put(Constants.RESPONSE_STATUS, Constants.RESPONSE_FAIL);
					logger.info("Error Uploading CTC for " + employeeId, this.getClass().getName(), Logger.Location.ALL);
					throw new HRMExceptions(ExceptionMessages.INVALID_FORM, ExceptionErrorCode.BAD_REQUEST);
				}
			}
			else
			{
				resultstatus.put(Constants.RESPONSE_STATUS, Constants.RESPONSE_FAIL);
			}

		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		return resultstatus;
	}
}