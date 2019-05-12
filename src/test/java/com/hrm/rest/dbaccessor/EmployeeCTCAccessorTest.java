package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.hrm.rest.controller.SessionController;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeCTC;
import com.mysql.jdbc.CallableStatement;
import java.sql.Blob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class EmployeeCTCAccessorTest
{

	@Mock
	private SessionController sessionController;

	@Mock
	DatabaseConnector dbConnector;

	@Mock
	CallableStatement callableStatement;

	@Mock
	EmployeeCTC employeeCTC;

	@Mock
	Blob returnBlob;


	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_SELECT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.execute()).thenReturn(true);
			Mockito.when(callableStatement.getBlob(3)).thenReturn(returnBlob);
		
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			Blob blob = employeeCTCAccessor.getEmployeeCTC();
			assertNotNull(blob);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCBlobTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_SELECT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.execute()).thenReturn(true);
			Mockito.when(callableStatement.getBlob(3)).thenReturn(null);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			Blob blob = employeeCTCAccessor.getEmployeeCTC();
			assertNull(blob);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCUpdateTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_UPDATE_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.execute()).thenReturn(true);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			employeeCTCAccessor.updateEmployeeCTC();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInvalidUpdateBlobTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_UPDATE_STATEMENT)).thenReturn(null);
			Mockito.when(callableStatement.execute()).thenReturn(true);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			employeeCTCAccessor.updateEmployeeCTC();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCDeleteTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_DELETE_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.execute()).thenReturn(true);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			employeeCTCAccessor.deleteEmployeeCTC();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInvalidDeleteBlobTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_DELETE_STATEMENT)).thenReturn(null);
			Mockito.when(callableStatement.execute()).thenReturn(true);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			employeeCTCAccessor.deleteEmployeeCTC();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInsertTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_INSERT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.executeUpdate()).thenReturn(1);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			boolean row_update = employeeCTCAccessor.insertCTC();
			assertTrue(row_update);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInsertInvalidTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_INSERT_STATEMENT)).thenReturn(callableStatement);
			Mockito.when(callableStatement.executeUpdate()).thenReturn(0);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			boolean row_update = employeeCTCAccessor.insertCTC();
			assertFalse(row_update);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInsertNullTest()
	{
		Exception expectedexception =null;
		try
		{
			PowerMockito.mockStatic(DatabaseConnector.class);
			Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
			Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEECTC_INSERT_STATEMENT)).thenReturn(null);
			Mockito.when(callableStatement.executeUpdate()).thenReturn(0);
			EmployeeCTCAccessor employeeCTCAccessor = new EmployeeCTCAccessor(employeeCTC);
			boolean rorow_update = employeeCTCAccessor.insertCTC();
			assertFalse(rorow_update);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}
}