package com.hrm.rest.dbaccessor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Locale;

import com.hrm.rest.controller.LeaveMessageConstants;
import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.utility.Constants;
import com.hrm.rest.validationlogic.LeaveValidation;

public class EmployeeLeaveInfoAccessor
{
	public static final String DB_TABLE_NAME = Constants.EMPLOYEE_LEAVE_INFO;
	public static final String DB_ID = "ID";
	public static final String DB_EMPLOYEE_ID = "EMPLOYEE_ID";
	public static final String DB_START_DATE = "START_DATE";
	public static final String DB_END_DATE = "END_DATE";
	public static final String DB_STATUS = "STATUS";

	private EmployeeLeaveInfo employeeLeaveInfo;

	public EmployeeLeaveInfoAccessor(EmployeeLeaveInfo employeeLeaveInfo)
	{
		this.employeeLeaveInfo = employeeLeaveInfo;
	}

	public String formatDateBeforeSave(Date date)
	{
		String dateformattype = LeaveMessageConstants.DATEFORMTTYPE.getValue();
		DateFormat dateformatter = new SimpleDateFormat(dateformattype, Locale.ENGLISH);
		return dateformatter.format(date);
	}

	public static boolean isLeaveStatusReject(String statusValue)
	{
		statusValue = statusValue.toLowerCase();
		String rejectStatus = LeaveMessageConstants.LEAVEREJECTVAL.getValue();
		rejectStatus = rejectStatus.toLowerCase();
		return statusValue.equals(rejectStatus);
	}

	public boolean updateEmployoeePendingLeave()
	{
		Logger logger = Logger.getInstance();
		Boolean isUpdateComplete = false;
		try
		{
			updateEmployeeLeaveStatus();
			if(isLeaveStatusReject(employeeLeaveInfo.getStatus()))
			{
				updateRemainingleaves();
				isUpdateComplete = true;
			}
		}
		catch (Exception e)
		{
			logger.error("Error in updating pending employee leave.", EmployeeLeaveInfoAccessor.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
		}
		return isUpdateComplete;
	}

	public boolean updateEmployeeLeaveStatus()throws Exception
	{
		Logger logger = Logger.getInstance();
		Boolean isUpdateSuccessful = false;
		try
		{
			DatabaseConnector dbConnector = DatabaseConnector.getInstance();
			CallableStatement callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLEAVE_UPDATE_LEAVESTATUS_STATEMENT);
			callableStatement.setInt(1, employeeLeaveInfo.getId());
			callableStatement.setString(2, employeeLeaveInfo.getEmployeeId());
			callableStatement.setString(3, employeeLeaveInfo.getStatus());
			ResultSet resultSet = callableStatement.executeQuery();
			isUpdateSuccessful = resultSet != null;
			closeConnection(callableStatement);
		}
		catch (Exception e)
		{
			logger.error("Error in updating employee leave status to database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			throw e;
		}
		return isUpdateSuccessful;
	}

	public String getRemainingLeaveCount() throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		int leaveCount;
		String remainingLeaves = null;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLEAVE_SELECT_REMAININGLEAVECOUNT_STATEMENT);
			callableStatement.setString(1, employeeLeaveInfo.getEmployeeId());
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			leaveCount = callableStatement.getInt(2);
			remainingLeaves = String.valueOf(leaveCount);
			closeConnection(callableStatement);
		}
		catch (Exception e)
		{
			logger.error("Error in fetching pending employee leave details from database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			throw e;
		}
		return remainingLeaves;
	}

	public ArrayList<HashMap<String, String>> getEmployeeLeaveDetails() throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		ResultSet result;
		ArrayList<HashMap<String, String>> finaloutputList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLEAVE_SELECT_LEAVEDETAILS_STATEMENT);
			callableStatement.setString(1, employeeLeaveInfo.getEmployeeId());
			result= callableStatement.executeQuery();
			while (result.next())
			{
				hashMap = new HashMap<String, String>();
				Date fromDate = result.getDate(3);
				Date endDate = result.getDate(4);
				String status = result.getString(5);

				hashMap.put(DB_START_DATE, fromDate.toString());
				hashMap.put(DB_END_DATE, endDate.toString());
				hashMap.put(DB_STATUS, status.toString());

				finaloutputList.add(hashMap);
			}
			closeConnection(callableStatement);
			return finaloutputList;
		}
		catch (Exception e)
		{
			logger.error(e, EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			throw e;
		}
	}

	public HashMap<String, String> getEmployeeNamesForID()
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		ResultSet result;
		HashMap<String, String> hashMap= new HashMap<String, String>();
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLEAVE_SELECT_EMPDETAILS_STATEMENT);
			result= callableStatement.executeQuery();
			while (result.next())
			{
				hashMap.put(result.getString(1), result.getString(2));
			}
			closeConnection(callableStatement);
		}
		catch (Exception e)
		{
			logger.error("Error in fetching pending employee leave details from database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
		}
		return hashMap;
	}

	public ArrayList<HashMap<String, String>> fetchPendingEmpLeaveDetails(HashMap<String, String> mapEmployeeNames) throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		ResultSet result;
		ArrayList<HashMap<String, String>> finaloutputList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLEAVE_SELECT_PENDINGLEAVE_STATEMENT);
			callableStatement.setString(1, LeaveMessageConstants.NEWLEAVEVAL.getValue());
			result= callableStatement.executeQuery();
			while (result.next())
			{
				hashMap = new HashMap<String, String>();

				hashMap.put(DB_ID, result.getString(1));
				hashMap.put(DB_EMPLOYEE_ID, result.getString(2));
				hashMap.put("EMPLOYEE_NAME", mapEmployeeNames.get(result.getString(2)));
				hashMap.put(DB_START_DATE, result.getDate(3).toString());
				hashMap.put(DB_END_DATE, result.getDate(4).toString());
				hashMap.put(DB_STATUS, result.getString(5));

				finaloutputList.add(hashMap);
			}
			closeConnection(callableStatement);
			return finaloutputList;
		}
		catch (Exception e)
		{
			logger.error("Error in fetching pending employee leave details from database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			throw e;
		}
	}

	public ArrayList<HashMap<String, String>> fetchEmpLeaveDetails() throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbConnector;
		CallableStatement callableStatement;
		ArrayList<HashMap<String, String>> finaloutputList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hashMap;
		try
		{
			dbConnector = DatabaseConnector.getInstance();
			callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLEAVE_SELECT_LEAVEDETAILS_STATEMENT);
			callableStatement.setString(1, employeeLeaveInfo.getEmployeeId());
			ResultSet result = callableStatement.executeQuery();
			while (result.next())
			{
				hashMap = new HashMap<String, String>();

				hashMap.put(DB_START_DATE, result.getDate(3).toString());
				hashMap.put(DB_END_DATE, result.getDate(4).toString());
				hashMap.put(DB_STATUS, result.getString(5));

				finaloutputList.add(hashMap);
			}
			closeConnection(callableStatement);
		}
		catch (Exception e)
		{
			logger.error("Error in fetching employee leave details from database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			throw e;
		}
		return finaloutputList;
	}

	public java.sql.Date convertToSqlDate(java.util.Date date)
	{
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	public boolean insertLeaveDetails() throws Exception
	{
		try
		{
			if(insertLeaveToDB())
			{
				updateRemainingleaves();
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.error("Error in updating leave details to database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			return false;
		}
	}

	public void updateRemainingleaves()throws Exception
	{
		try
		{
			ArrayList<HashMap<String, String>> appliedLeaves = fetchEmpLeaveDetails();
			LeaveValidation leavevalidation = new LeaveValidation(employeeLeaveInfo);
			long remainingLeaves = leavevalidation.calculateRemainingLeaves(appliedLeaves);
			updateRemainingleavesInDB(remainingLeaves);
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.error("Error in updating leave details to database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
		}
	}

	public boolean updateRemainingleavesInDB(long remainingLeaves)
	{
		try
		{
			DatabaseConnector dbConnector = DatabaseConnector.getInstance();
			CallableStatement callableStatement = dbConnector.getStatement(DatabaseConstants.EMPLEAVE_UPDATE_REMAININGLEAVE_STATEMENT);
			callableStatement.setString(1, employeeLeaveInfo.getEmployeeId());
			callableStatement.setInt(2,(int)remainingLeaves);
			ResultSet resultSet = callableStatement.executeQuery();
			closeConnection(callableStatement);
			return resultSet != null;
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.error("Error in updating remaining leaves to database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertLeaveToDB() throws Exception
	{
		try
		{
			DatabaseConnector dbConnector = DatabaseConnector.getInstance();
			String insertStatement = DatabaseConstants.EMPLEAVE_INSERT_LEAVE_STATEMENT;
			CallableStatement callableStatement = dbConnector.getStatement(insertStatement);
			callableStatement.setString(1, employeeLeaveInfo.getEmployeeId());
			callableStatement.setDate(2, convertToSqlDate(employeeLeaveInfo.getStartDate()));
			callableStatement.setDate(3, convertToSqlDate(employeeLeaveInfo.getEndDate()));
			callableStatement.setString(4, employeeLeaveInfo.getStatus());
			ResultSet resultSet = callableStatement.executeQuery();
			closeConnection(callableStatement);
			return resultSet != null;
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.error("Error in inserting leave details to database.", EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection(CallableStatement connectionName)
	{
		try
		{
			connectionName.close();
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.error(e, EmployeeLeaveInfo.class.getName(), Logger.Location.ALL);
		}
	}
}