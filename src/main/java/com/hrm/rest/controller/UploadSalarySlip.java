package com.hrm.rest.controller;

import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeSalarySlipAccessor;
import com.hrm.rest.exceptionhandling.HRMExceptions;
import com.hrm.rest.uploaddocuments.EmployeeSalaryUpload;
import com.hrm.rest.utility.Constants;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/profile")
public class UploadSalarySlip
{
	@ResponseBody
	@RequestMapping(value = "/uploadSalarySlip", method = RequestMethod.POST)
	public Map<String, String> uploadSalarySlip(EmployeeSalaryUpload salaryupload, HttpServletResponse response) throws Exception
	{
		DatabaseAccessorFactory databaseAccessorFactory;
		EmployeeSalarySlipAccessor employeeSalarySlipAccessor;
		HashMap<String, String> validationResult = new HashMap<String, String>();
		Map<String, String> resultstatus = new HashMap<>();
		Logger logger = Logger.getInstance();

		try
		{
			String employeeId = salaryupload.getEmpId();
			databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			validationResult = salaryupload.validateSalarySlip();
			boolean result = false;

			if (validationResult.get("IsValid").equals("true"))
			{
				employeeSalarySlipAccessor = databaseAccessorFactory.makeEmployeeSalarySlipAccessor(salaryupload.getEmployeeSalarySlip());
				result = employeeSalarySlipAccessor.insertSalarySlip();
				if (result)
				{
					resultstatus.put(Constants.RESPONSE_STATUS, Constants.RESPONSE_STATUS);
					logger.info("Uploaded Salary Slip for the employee " + employeeId, this.getClass().getName(), Logger.Location.ALL);
				}
				else
				{
					resultstatus.put(Constants.RESPONSE_STATUS, Constants.RESPONSE_FAIL);
					new HRMExceptions("Error Uploading Salary Slip");
					logger.info("Error Uploading Salary Slip for " + employeeId, this.getClass().getName(), Logger.Location.ALL);
				}
			}
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		return resultstatus;
	}
}
