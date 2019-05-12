package com.hrm.rest.uploaddocuments;


import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.exceptionhandling.HRMExceptions;
import com.hrm.rest.model.EmployeeCTC;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeCTCUpload
{
	private MultipartFile file;
	private String empId;
	private String empDOJ;

	public MultipartFile getFile()
	{
		return file;
	}

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

	public String getEmpId()
	{
		return empId;
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

	public String getEmpDOJ()
	{
		return empDOJ;
	}

	public void setEmpDOJ(String empDOJ)
	{
		this.empDOJ = empDOJ;
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
			Logger logger = Logger.getInstance();
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		return blob;
	}

	public int getYear()
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		try
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date date = sdf.parse(this.empDOJ);
			cal.setTime(date);
			year = cal.get(Calendar.YEAR);
		}
		catch (Exception e)
		{
			new HRMExceptions(e);
		}
		return year;

	}

	public EmployeeCTC getEmployeeCTC()
	{
		EmployeeCTC employeeCTC = new EmployeeCTC();
		employeeCTC.setEmployeeId(getEmpId());
		employeeCTC.setCtcYear(getYear());
		employeeCTC.setCtc(getBlob());
		return employeeCTC;
	}

	public HashMap<String,String> validateCTC() throws HRMExceptions
	{
		HashMap<String,String> resultSet= new HashMap<String,String>();
		EmployeeCTC employeeCTC = getEmployeeCTC();
		IValidateDocuments employeeCTCValidate = new EmployeeCTCValidate(employeeCTC);
		try
		{
			resultSet=employeeCTCValidate.validateDocument();
			return resultSet;
		}
		catch (Exception e)
		{
			throw new HRMExceptions("Error Validating Employee CTC",e);
		}
	}
}