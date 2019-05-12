package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertNull;

import java.util.Date;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.Logs;
import com.mysql.jdbc.CallableStatement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class LogsAccessorTest
{
	@Mock
	DatabaseConnector dbConnector;

	@Mock
	CallableStatement callableStatement;

	@Mock
	private Logger logger;

	@Mock
	Logs logs;

	@Test
	@PrepareForTest({DatabaseConnector.class, Logger.class})
	public void logInDatabaseTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			PowerMockito.mockStatic(Logger.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(Logger.getInstance()).thenReturn(logger);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.LOGS_INSERT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.executeUpdate()).thenReturn(1);
			Mockito.when(logs.getLogTime()).thenReturn(new Date());

			LogsAccessor logsAccessor = new LogsAccessor(logs);
			logsAccessor.logInDatabase();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}
}