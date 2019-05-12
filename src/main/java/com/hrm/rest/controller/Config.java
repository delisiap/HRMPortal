package com.hrm.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hrm.rest.HRMContext;
import com.hrm.rest.model.ConfigProperty;
import com.hrm.rest.utility.Constants;
import com.hrm.rest.utility.DatabaseProperties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/config")
public class Config
{
	private final static String PROPERTIES = "properties";

	@RequestMapping(value = "/getconfig", method = RequestMethod.POST)
	public Map<String, Object> getConfig()
	{
		Logger logger = Logger.getInstance();
		Map<String, Object> res;
		SessionController sessionController = HRMContext.getSessionController();
		DatabaseProperties databaseProperties;
		try
		{
			databaseProperties = DatabaseProperties.getInstance();

			res = Response.createResponse(Constants.RESPONSE_SUCCESS, databaseProperties);
			logger.info("Fetching configuration", this.getClass().getName(), Logger.Location.ALL);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			res = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
			sessionController.redirect(Constants.ERROR_HTML);
		}
		return res;
	}

	@RequestMapping(value = "/updateconfig", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateConfig(@RequestBody HashMap<String, ArrayList<ConfigProperty>> propertiesObject)
	{
		Logger logger = Logger.getInstance();
		Map<String, Object> res;
		SessionController sessionController = HRMContext.getSessionController();
		DatabaseProperties databaseProperties;
		try
		{
			databaseProperties = DatabaseProperties.getInstance();
			ArrayList<ConfigProperty> properties = propertiesObject.get(Config.PROPERTIES);
			databaseProperties.setProperties(properties);

			res = Response.createResponse(Constants.RESPONSE_SUCCESS, properties);
			logger.info("Updating configuration", this.getClass().getName(), Logger.Location.ALL);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
			res = Response.createResponse(Constants.RESPONSE_FAIL, e.getMessage());
			sessionController.redirect(Constants.ERROR_HTML);
		}
		return res;
	}
}