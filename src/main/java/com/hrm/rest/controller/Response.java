package com.hrm.rest.controller;

import java.util.HashMap;
import com.hrm.rest.utility.Constants;

public class Response
{
	public static HashMap<String, Object> createResponse(String status)
	{
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put(Constants.RESPONSE_STATUS, status);
		return response;
	}

	public static HashMap<String, Object> createResponse(String status, Object data)
	{
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put(Constants.RESPONSE_STATUS, status);
		response.put(Constants.RESPONSE_DATA, data);
		return response;
	}
}