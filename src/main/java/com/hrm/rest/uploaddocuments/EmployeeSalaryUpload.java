package com.hrm.rest.uploaddocuments;

import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.exceptionhandling.HRMExceptions;

import org.springframework.web.multipart.MultipartFile;

import com.hrm.rest.model.EmployeeSalarySlip;
import com.hrm.rest.utility.Constants;

import javax.sql.rowset.serial.SerialBlob;

public class EmployeeSalaryUpload
{
	private String empId;
	private MultipartFile file;
	private String empDOJ;

	public String getEmpDOJ()
	{
		return empDOJ;
	}

	public void setEmpDOJ(String empDOJ)
	{
		this.empDOJ = empDOJ;
	}

	public String getEmpId()
	{
		return empId;
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

	public MultipartFile getFile()
	{
		return file;
	}

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

	public Date toDateTime(String timestamp)
	{
		java.sql.Date sqlDate=null;
		try
		{
			SimpleDateFormat simpleDateFormat= new SimpleDateFormat(Constants.MONTH_YEAR_FORMAT_STRING);
			java.util.Date date = simpleDateFormat.parse(timestamp);
			sqlDate = new java.sql.Date(date.getTime());
		}
		catch (Exception e)
		{
			new HRMExceptions("Error Converting Timestamp to dateyear",e);
		}
		return sqlDate;
	}

	public Blob getBlob()
	{
		Blob blob = null;
		try
		{
			byte[] bytes = this.file.getBytes();
			blob = new SerialBlob(bytes);
		}
		catch(Exception e)
		{
			Logger logger =Logger.getInstance();
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		return blob;
	}

	public EmployeeSalarySlip getEmployeeSalarySlip() throws Exception
	{
		EmployeeSalarySlip employeeSalarySlip = new EmployeeSalarySlip();
		employeeSalarySlip.setEmployeeId(getEmpId());
		employeeSalarySlip.setMonthYear(this.empDOJ);
		employeeSalarySlip.setSalarySlip(this.getBlob());
		return employeeSalarySlip;
	}

	public HashMap<String,String> validateSalarySlip() throws HRMExceptions
	{
		HashMap<String,String> resultSet= new HashMap<String,String>();
		try
		{
			EmployeeSalarySlip employeeSalarySlip = getEmployeeSalarySlip();
			IValidateDocuments employeeCTCValidate = new EmployeeSalarySlipValidate(employeeSalarySlip);
			resultSet=employeeCTCValidate.validateDocument();
			return resultSet;
		}
		catch (Exception e)
		{
			throw new HRMExceptions("Error Validating Employee Salary Slip",e);
		}
	}
}