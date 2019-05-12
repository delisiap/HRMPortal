package com.hrm.rest.employee;

import java.util.Iterator;

import com.hrm.rest.authenticator.EncryptPassword;
import com.hrm.rest.dbaccessor.DatabaseAccessorFactory;
import com.hrm.rest.dbaccessor.EmployeeInfoAccessor;
import com.hrm.rest.dbaccessor.EmployeeLoginAccessor;
import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.model.EmployeeLogin;
import com.hrm.rest.model.ModelFactory;
import com.hrm.rest.role.Role;
import com.hrm.rest.role.Roles;
import com.hrm.rest.utility.DatabaseProperties;

public class EmployeeHelper
{
	public static Role getRoleByEmployeeId(String employeeId) throws Exception
	{
		EmployeeInfo employeeInfo = getEmployeeInfo(employeeId);
		Roles roles = Roles.getInstance();
		Iterator<Role> rolesIterator = roles.getRoles().iterator();
		boolean roleMatched = false;
		int roleId = employeeInfo.getRoleId();
		Role role = null;

		while (rolesIterator.hasNext())
		{
			role = (Role) rolesIterator.next();
			if (role.getRoleId() == roleId)
			{
				roleMatched = true;
				break;
			}
		}
		if (!roleMatched)
		{
			throw new InvalidUserRoleException();
		}
		return role;
	}

	public static Role getRoleByRoleName(String roleName) throws Exception
	{
		Roles roles = Roles.getInstance();
		Iterator<Role> rolesIterator = roles.getRoles().iterator();
		boolean roleMatched = false;
		Role role = null;

		while (rolesIterator.hasNext())
		{
			role = (Role) rolesIterator.next();
			if (role.getRoleName().equals(roleName))
			{
				roleMatched = true;
				break;
			}
		}
		if (!roleMatched)
		{
			throw new InvalidRoleNameException();
		}
		return role;
	}

	public static Role getRoleByRoleId(int roleId) throws Exception
	{
		Roles roles = Roles.getInstance();
		Iterator<Role> rolesIterator = roles.getRoles().iterator();
		boolean roleMatched = false;
		Role role = null;

		while (rolesIterator.hasNext())
		{
			role = (Role) rolesIterator.next();
			if (role.getRoleId() == roleId)
			{
				roleMatched = true;
				break;
			}
		}
		if (!roleMatched)
		{
			throw new InvalidRoleIdException();
		}
		return role;
	}

	public static EmployeeInfo getEmployeeInfo(String employeeId) throws Exception
	{
		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
		EmployeeInfo employeeInfo = new ExtendedEmployeeInfo();
		employeeInfo.setEmployeeId(employeeId);
		EmployeeInfoAccessor employeeInfoAccessor = databaseAccessorFactory.makeEmployeeInfoAccessor(employeeInfo);
		employeeInfoAccessor.getEmployeeInfo();
		return employeeInfo;
	}

	public static EmployeeInfo updateEmployeeInfo(EmployeeInfo employeeInfo) throws Exception
	{
		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
		EmployeeInfoAccessor employeeInfoAccessor = databaseAccessorFactory.makeEmployeeInfoAccessor(employeeInfo);
		employeeInfoAccessor.updateEmployeeInfo();
		return employeeInfo;
	}

	public static void addEmployee(EmployeeInfo employeeInfo) throws Exception
	{
		DatabaseProperties databaseProperties = DatabaseProperties.getInstance();
		ModelFactory modelFactory = ModelFactory.getInstance();
		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
		EmployeeInfoAccessor employeeInfoAccessor = databaseAccessorFactory.makeEmployeeInfoAccessor(employeeInfo);
		employeeInfoAccessor.addEmployee();

		EmployeeLogin employeeLogin = modelFactory.makeEmptyEmployeeLogin();
		employeeLogin.setEmployeeId(employeeInfo.getEmployeeId());
		String password = databaseProperties.getProperty(DatabaseProperties.DEFAULT_USER_PASSWORD);
		EncryptPassword encryptPassword = new EncryptPassword(password);
		employeeLogin.setPassword(encryptPassword.getHashedPassword());

		EmployeeLoginAccessor employeeLoginAccessor = databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin);
		employeeLoginAccessor.addEmployeeLogin();
	}

	public static void deleteEmployee(EmployeeInfo employeeInfo) throws Exception
	{
		ModelFactory modelFactory = ModelFactory.getInstance();
		DatabaseAccessorFactory databaseAccessorFactory = DatabaseAccessorFactory.getInstance();
		EmployeeInfoAccessor employeeInfoAccessor = databaseAccessorFactory.makeEmployeeInfoAccessor(employeeInfo);
		employeeInfoAccessor.deleteEmployee();

		EmployeeLogin employeeLogin = modelFactory.makeEmptyEmployeeLogin();
		employeeLogin.setEmployeeId(employeeInfo.getEmployeeId());

		EmployeeLoginAccessor employeeLoginAccessor = databaseAccessorFactory.makeEmployeeLoginAccessor(employeeLogin);
		employeeLoginAccessor.deleteEmployeeLogin();
	}
}