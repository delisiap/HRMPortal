package com.hrm.rest.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hrm.rest.ConfigurationProperties;
import com.hrm.rest.HRMContext;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.LogsAccessor;
import com.hrm.rest.model.Logs;
import com.hrm.rest.model.ModelFactory;

public class Logger
{
	private enum Level
	{
		INFO, WARN, ERROR, FATAL, OFF;
	}

	public enum Location
	{
		ALL, CONSOLE, DATABASE
	}

	private static Logger logger = null;
	private static final String CONSOLE_FORMAT = "%s\t%s\t%s\t%s\t%s%n";

	private Level configuredLogLevel;

	Logger()
	{
		try
		{
			String configuredLogLevel = ConfigurationProperties.getProperty("app.loglevel");
			this.configuredLogLevel = Level.valueOf(configuredLogLevel);
		}
		catch (Exception e)
		{
			this.configuredLogLevel = Level.INFO;
			this.log(this.getClass().getName(), "Invalid logLevel provided. Setting log level as INFO.", Level.ERROR, Location.CONSOLE);
		}
	}

	public static Logger getInstance()
	{
		if (logger == null)
		{
			logger = new Logger();
		}
		return logger;
	}

	private void log(String className, String msg, Level logLevel, Location location)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(ConfigurationProperties.getProperty("app.datasource.dateformat"));
		SessionController sessionController = HRMContext.getSessionController();
		String empId = sessionController.getLoggedInUserId();
		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
		ModelFactory modelFactory = ModelFactory.getInstance();
		Date logTime = new Date();

		if (logLevel.ordinal() >= configuredLogLevel.ordinal())
		{
			if (location != Location.CONSOLE)
			{
				Logs log = modelFactory.makeLogs(empId, logTime, logLevel.toString(), msg, className);
				LogsAccessor logsAccessor = databaseAccessorFactory.makeLogsAccessor(log);
				logsAccessor.logInDatabase();
			}

			if (location != Location.DATABASE)
			{
				String datetime = dateFormat.format(logTime);
				System.out.printf(Logger.CONSOLE_FORMAT, datetime, logLevel.toString(), empId, className, msg);
			}
		}
	}

	private String getStackTrace(Exception e)
	{
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	public void info(String msg, String className, Location location)
	{
		this.log(className, msg, Level.INFO, location);
	}

	public void info(Exception e, String className, Location location)
	{
		this.log(className, this.getStackTrace(e), Level.INFO, location);
	}

	public void warn(String msg, String className, Location location)
	{
		this.log(className, msg, Level.WARN, location);
	}

	public void warn(Exception e, String className, Location location)
	{
		this.log(className, this.getStackTrace(e), Level.WARN, location);
	}

	public void error(String msg, String className, Location location)
	{
		this.log(className, msg, Level.ERROR, location);
	}

	public void error(Exception e, String className, Location location)
	{
		this.log(className, this.getStackTrace(e), Level.ERROR, location);
	}

	public void fatal(String msg, String className, Location location)
	{
		this.log(className, msg, Level.FATAL, location);
	}

	public void fatal(Exception e, String className, Location location)
	{
		this.log(className, this.getStackTrace(e), Level.FATAL, location);
	}
}