package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertNull;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.ConfigProperty;
import com.hrm.rest.model.ModelFactory;
import com.mysql.jdbc.CallableStatement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ConfigPropertyAccessorTest
{
	@Mock
	DatabaseConnector dbConnector;

	@Mock
	CallableStatement callableStatement;

	@Mock
	private Logger logger;

	@Mock
	private ModelFactory modelFactory;

	@Mock
	ResultSet resultSet;

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class, ModelFactory.class})
	public void getConfigPropertiesFromDatabaseTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			PowerMockito.mockStatic(ModelFactory.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(ModelFactory.getInstance()).thenReturn(modelFactory);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.CONFIGPROPERTY_GET_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.executeQuery()).thenReturn(resultSet);
			Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
		
			ArrayList<ConfigProperty> configProperties = new ArrayList<ConfigProperty>();
			ConfigPropertyAccessor configPropertyAccessor = new ConfigPropertyAccessor(configProperties);
			configPropertyAccessor.getConfigPropertiesFromDatabase();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void setConfigPropertiesFromDatabaseTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.CONFIGPROPERTY_GET_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.executeQuery()).thenReturn(resultSet);
		
			ArrayList<ConfigProperty> configProperties = new ArrayList<ConfigProperty>();
			ConfigPropertyAccessor configPropertyAccessor = new ConfigPropertyAccessor(configProperties);
			configPropertyAccessor.getConfigPropertiesFromDatabase();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}
}