package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Blob;

import com.hrm.rest.controller.SessionController;
import com.hrm.rest.dblayer.DatabaseConnector;
import com.hrm.rest.model.EmployeeSalarySlip;
import com.mysql.jdbc.CallableStatement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class EmployeeSalarySlipAccessorTest
{

	@Mock
	private SessionController sessionController;

	@Mock
	DatabaseConnector dbConnector;

	@Mock
	CallableStatement callableStatement;

	@Mock
	EmployeeSalarySlip employeeSalarySlip;

	@Mock
	Blob returnBlob;


	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_SELECT_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.execute()).thenReturn(true);
		Mockito.when(callableStatement.getBlob(3)).thenReturn(returnBlob);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			Blob blob = employeeSalarySlipAccessor.getEmployeeSalarySlip();
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
	public void getEmployeeCTCBlobTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_SELECT_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.execute()).thenReturn(true);
		Mockito.when(callableStatement.getBlob(3)).thenReturn(null);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			Blob blob = employeeSalarySlipAccessor.getEmployeeSalarySlip();
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
	public void getEmployeeCTCUpdateTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_UPDATE_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.execute()).thenReturn(true);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			employeeSalarySlipAccessor.updateEmployeeSalarySlip();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInvalidUpdateBlobTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_UPDATE_STATEMENT)).thenReturn(null);
		Mockito.when(callableStatement.execute()).thenReturn(true);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			employeeSalarySlipAccessor.updateEmployeeSalarySlip();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCDeleteTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_DELETE_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.execute()).thenReturn(true);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			employeeSalarySlipAccessor.deleteEmployeeSalarySlip();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);

	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInvalidDeleteBlobTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);
		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);
		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_DELETE_STATEMENT)).thenReturn(null);
		Mockito.when(callableStatement.execute()).thenReturn(true);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			employeeSalarySlipAccessor.deleteEmployeeSalarySlip();
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}

	@Test
	@PrepareForTest({DatabaseConnector.class, SessionController.class})
	public void getEmployeeCTCInsertTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_INSERT_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.executeUpdate()).thenReturn(1);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			boolean row_update = employeeSalarySlipAccessor.insertSalarySlip();
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
	public void getEmployeeCTCInsertInvalidTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_INSERT_STATEMENT)).thenReturn(callableStatement);
		Mockito.when(callableStatement.executeUpdate()).thenReturn(0);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			boolean row_update = employeeSalarySlipAccessor.insertSalarySlip();
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
	public void getEmployeeCTCInsertNullTest() throws Exception
	{
		PowerMockito.mockStatic(DatabaseConnector.class);

		Mockito.when(DatabaseConnector.getInstance()).thenReturn(dbConnector);

		Mockito.when(dbConnector.getStatement(DatabaseConstants.EMPLOYEESALARYSLIP_INSERT_STATEMENT)).thenReturn(null);
		Mockito.when(callableStatement.executeUpdate()).thenReturn(0);

		Exception expectedexception =null;
		try
		{
			EmployeeSalarySlipAccessor employeeSalarySlipAccessor = new EmployeeSalarySlipAccessor(employeeSalarySlip);
			boolean row_update = employeeSalarySlipAccessor.insertSalarySlip();
			assertFalse(row_update);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNotNull(expectedexception);
	}
}