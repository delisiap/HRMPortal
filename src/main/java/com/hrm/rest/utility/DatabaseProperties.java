package com.hrm.rest.utility;

import java.util.ArrayList;
import java.util.Iterator;

import com.hrm.rest.dbaccessor.ConfigPropertyAccessor;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.exceptionhandling.ExceptionErrorCode;
import com.hrm.rest.exceptionhandling.HRMExceptions;
import com.hrm.rest.model.ConfigProperty;

public class DatabaseProperties
{
	public static final String DEFAULT_USER_PASSWORD = "DEFAULT_USER_PASSWORD";

	private static DatabaseProperties databaseProperties;
	private ArrayList<ConfigProperty> properties;

	public DatabaseProperties() throws HRMExceptions
	{
		try
		{
			readPropertiesFromDatabase();
		}
		catch (Exception e)
		{
			throw new HRMExceptions("Failed to initialize the properties class.", ExceptionErrorCode.INTERNAL_SERVER_ERROR, e);
		}
	}

	private void readPropertiesFromDatabase() throws Exception
	{
		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
		properties = new ArrayList<ConfigProperty>();
		ConfigPropertyAccessor configPropertyAccessor = databaseAccessorFactory.makeConfigPropertyAccessor(properties);
		configPropertyAccessor.getConfigPropertiesFromDatabase();
	}

	public static DatabaseProperties getInstance() throws HRMExceptions
	{
		if (databaseProperties == null)
		{
			databaseProperties = new DatabaseProperties();
		}
		return databaseProperties;
	}

	public ArrayList<ConfigProperty> getProperties()
	{
		return new ArrayList<ConfigProperty>(properties);
	}

	public void setProperties(ArrayList<ConfigProperty> configProperties) throws HRMExceptions
	{
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			ConfigPropertyAccessor configPropertyAccessor = databaseAccessorFactory.makeConfigPropertyAccessor(configProperties);
			configPropertyAccessor.setConfigPropertiesInDatabase();
			readPropertiesFromDatabase();
		}
		catch (Exception e)
		{
			throw new HRMExceptions("Failed to set the properties.", ExceptionErrorCode.INTERNAL_SERVER_ERROR, e);
		}
	}

	public String getProperty(String propertyName) throws HRMExceptions
	{
		String propertyValue = "";
		Iterator<ConfigProperty> propertiesIterator = properties.iterator();
		while(propertiesIterator.hasNext())
		{
			ConfigProperty configProperty = propertiesIterator.next();
			if(configProperty.getPropertyName().equals(propertyName))
			{
				propertyValue = configProperty.getPropertyValue();
			}
		}
		if(propertyValue.isEmpty())
		{
			throw new HRMExceptions("Invalid propertyName provided.", ExceptionErrorCode.INTERNAL_SERVER_ERROR);
		}
		return propertyValue;
	}
}