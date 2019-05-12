package com.hrm.rest.dblayer;

import com.hrm.rest.ConfigurationProperties;
import com.hrm.rest.controller.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;

public class DatabaseConnector
{
	private static DatabaseConnector databaseConnector = null;

	public static DatabaseConnector getInstance()
	{
		if (databaseConnector == null)
		{
			databaseConnector = new DatabaseConnector();
		}
		return databaseConnector;
	}

	private Connection getConnection()
	{
		Connection conn = null;
		Logger logger = Logger.getInstance();
		try
		{
			String datasourceUrl = ConfigurationProperties.getProperty("app.datasource.url");
			String username = ConfigurationProperties.getProperty("app.datasource.username");
			String password = ConfigurationProperties.getProperty("app.datasource.password");

			Class.forName(ConfigurationProperties.getProperty("app.datasource.driver"));
			conn = DriverManager.getConnection(datasourceUrl, username, password);

			logger.info("Creating a new database connection.", this.getClass().getName(), Logger.Location.CONSOLE);
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.CONSOLE);
		}

		return conn;
	}

	public CallableStatement getStatement(String Query) throws Exception
	{
		Logger logger = Logger.getInstance();
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = this.getConnection().prepareCall(Query);
		}
		catch (Exception e)
		{
			logger.info("Error creating callable statement.", this.getClass().getName(), Logger.Location.CONSOLE);
			throw e;
		}
		return callableStatement;
	}

	public PreparedStatement preparedCall(String Query) throws Exception
	{
		Logger logger = Logger.getInstance();
		PreparedStatement callableStatement = null;
		try
		{
			callableStatement = this.getConnection().prepareCall(Query);
		}
		catch (Exception e)
		{
			logger.info("Error creating callable statement.", this.getClass().getName(), Logger.Location.CONSOLE);
			throw e;
		}
		return callableStatement;
	}

	public ResultSet executeQuery(String query) throws Exception
	{
		Logger logger = Logger.getInstance();
		Connection conn = this.getConnection();
		ResultSet resultSet = null;
		if (conn != null)
		{
			try
			{
				Statement statement = conn.createStatement();
				boolean success = statement.execute(query);
				logger.info("Executing query.", this.getClass().getName(), Logger.Location.CONSOLE);

				if (success)
				{
					resultSet = statement.getResultSet();
				}
			}
			catch (Exception e)
			{
				logger.error("Error in database query.", this.getClass().getName(), Logger.Location.CONSOLE);
				throw e;
			}
			finally
			{
				try
				{
					logger.info("Closing database connection.", this.getClass().getName(), Logger.Location.CONSOLE);
					conn.close();
				}
				catch (Exception e)
				{
					logger.error(e, this.getClass().getName(), Logger.Location.CONSOLE);
				}
			}
		}
		return resultSet;
	}
}