package com.hrm.rest.dbaccessor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.hrm.rest.model.EmployeeCTC;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.EmployeeLeaveInfo;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.EmployeeSalarySlip;
import com.hrm.rest.model.Logs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class DatabaseAccessorFactoryTest
{
	@Test
	public void makeEmployeeCTCAccessorTest()
	{
		Exception expectedexception =null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeCTCAccessor employeeCTCAccessor = databaseAccessorFactory.makeEmployeeCTCAccessor(new EmployeeCTC());
			assertNotNull(employeeCTCAccessor);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	public void makeEmployeeInfoAccessorTest()
	{
		Exception expectedexception =null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeInfoAccessor employeeInfoAccessor = databaseAccessorFactory.makeEmployeeInfoAccessor(new EmployeeInfo());
			assertNotNull(employeeInfoAccessor);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	public void makeEmployeeLeaveInfoAccessorTest()
	{
		Exception expectedexception =null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeLeaveInfoAccessor employeeLeaveInfoAccessor = databaseAccessorFactory.makeEmployeeLeaveInfoAccessor(new EmployeeLeaveInfo());
			assertNotNull(employeeLeaveInfoAccessor);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	public void makeEmployeeLoginAccessorTest()
	{
		Exception expectedexception =null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeLoginAccessor employeeLoginAccessor = databaseAccessorFactory.makeEmployeeLoginAccessor(new EmployeeLogin());
			assertNotNull(employeeLoginAccessor);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	public void makeEmployeeSalarySlipAccessorTest()
	{
		Exception expectedexception =null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			EmployeeSalarySlipAccessor employeeCTCAccessor = databaseAccessorFactory.makeEmployeeSalarySlipAccessor(new EmployeeSalarySlip());
			assertNotNull(employeeCTCAccessor);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	@Test
	public void makeLogsAccessorTest()
	{
		Exception expectedexception =null;
		try
		{
			DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
			LogsAccessor logAccessor = databaseAccessorFactory.makeLogsAccessor(new Logs());
			assertNotNull(logAccessor);
		}
		catch (Exception e)
		{
			expectedexception =e;
		}
		assertNull(expectedexception);
	}

	// @Test
	// public void makeConfigPropertyAccessorTest()
	// {
	// 	Exception expectedexception =null;
	// 	try
	// 	{
	// 		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
	// 		ConfigPropertyAccessor configPropertyAccessor = databaseAccessorFactory.makeConfigPropertyAccessor(new EmployeeCTC());
	// 		assertNotNull(configPropertyAccessor);
	// 	}
	// 	catch (Exception e)
	// 	{
	// 		expectedexception =e;
	// 	}
	// 	assertNull(expectedexception);
	// }
}