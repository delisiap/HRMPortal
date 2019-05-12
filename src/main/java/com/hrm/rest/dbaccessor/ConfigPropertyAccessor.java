package com.hrm.rest.dbaccessor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.ConfigProperty;
import com.hrm.rest.model.ModelFactory;

public class ConfigPropertyAccessor
{
	private ArrayList<ConfigProperty> configProperties;

	public ConfigPropertyAccessor(ArrayList<ConfigProperty> configProperties)
	{
		this.configProperties = configProperties;
	}

	public void getConfigPropertiesFromDatabase() throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbconnector = DatabaseConnector.getInstance();
		ModelFactory modelFactory = ModelFactory.getInstance();
		try
		{
			CallableStatement callableStatement = dbconnector.getStatement(DatabaseConstants.CONFIGPROPERTY_GET_STATEMENT);
			ResultSet resultSet = callableStatement.executeQuery();
			while (resultSet.next())
			{
				String propertyName = resultSet.getString(1);
				String propertyValue = resultSet.getString(2);
				ConfigProperty configProperty = modelFactory.makeConfigProperty(propertyName, propertyValue);
				configProperties.add(configProperty);
			}
			callableStatement.close();
		}
		catch (Exception e)
		{
			logger.error("Error in fetching config properties.", this.getClass().getName(), Logger.Location.ALL);
			throw e;
		}
	}

	public void setConfigPropertiesInDatabase() throws Exception
	{
		Logger logger = Logger.getInstance();
		DatabaseConnector dbconnector = DatabaseConnector.getInstance();
		try
		{
			Iterator<ConfigProperty> configIterator = configProperties.iterator();
			while(configIterator.hasNext())
			{
				ConfigProperty configProperty = configIterator.next();
				CallableStatement callableStatement = dbconnector.getStatement(DatabaseConstants.CONFIGPROPERTY_UPDATE_STATEMENT);
				callableStatement.setString(1, configProperty.getPropertyName());
				callableStatement.setString(2, configProperty.getPropertyValue());
				callableStatement.execute();
				callableStatement.close();
			}
		}
		catch (Exception e)
		{
			logger.error("Error in updating config properties.", this.getClass().getName(), Logger.Location.ALL);
			throw e;
		}
	}
}