package com.hrm.rest.controller;

import java.sql.Blob;

import com.hrm.rest.HRMContext;
import com.hrm.rest.controller.Logger.Location;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeCTCAccessor;
import com.hrm.rest.dbaccessor.EmployeeSalarySlipAccessor;
import com.hrm.rest.model.EmployeeCTC;
import com.hrm.rest.model.EmployeeSalarySlip;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.utility.Constants;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/account")
public class Account
{
	@RequestMapping(value = "/downloadsalaryslip", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> downloadSalarySlip(@RequestParam(value = "monthYear", required = true) String monthYear)
	{
		Logger logger = Logger.getInstance();
		ResponseEntity<InputStreamResource> result = null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			ModelFactory modelFactory = ModelFactory.getInstance();
			SessionController sessionController = HRMContext.getSessionController();
			EmployeeSalarySlip empSalarySlip = modelFactory.makeEmptyEmployeeSalarySlip();
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor;

			empSalarySlip.setEmployeeId(sessionController.getLoggedInUserId());
			empSalarySlip.setMonthYear(monthYear);

			employeeSalarySlipAccessor = databaseAccessorFactory.makeEmployeeSalarySlipAccessor(empSalarySlip);
			Blob salarySlip = employeeSalarySlipAccessor.getEmployeeSalarySlip();
			InputStreamResource resource = new InputStreamResource(salarySlip.getBinaryStream());
			result = ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, Constants.CONTENT_DISPOSITION + Constants.SALARY_SLIP_FILE_NAME)
					.contentType(MediaType.parseMediaType(Constants.MEDIA_TYPE)).body(resource);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Location.ALL);
		}
		return result;
	}

	@RequestMapping(value = "/downloadctc", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> downloadCTC(@RequestParam(value = "year", required = true) String year)
	{
		Logger logger = Logger.getInstance();
		ResponseEntity<InputStreamResource> result = null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			ModelFactory modelFactory = ModelFactory.getInstance();
			SessionController sessionController = HRMContext.getSessionController();
			EmployeeCTC empCTC = modelFactory.makeEmptyEmployeeCTC();
			EmployeeCTCAccessor employeeCTCAccessor;

			databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			modelFactory = ModelFactory.getInstance();
			empCTC = modelFactory.makeEmployeeCTC(sessionController.getLoggedInUserId(), Integer.parseInt(year), null);
			employeeCTCAccessor = databaseAccessorFactory.makeEmployeeCTCAccessor(empCTC);
			Blob ctc = employeeCTCAccessor.getEmployeeCTC();
			InputStreamResource resource = new InputStreamResource(ctc.getBinaryStream());
			result = ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, Constants.CONTENT_DISPOSITION + Constants.CTC_FILE_NAME)
					.contentType(MediaType.parseMediaType(Constants.MEDIA_TYPE)).body(resource);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Location.ALL);
		}
		return result;
	}
}