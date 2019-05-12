package com.hrm.rest.employee;

import com.hrm.rest.model.EmployeeInfo;
import com.hrm.rest.role.Role;

public class ExtendedEmployeeInfo extends EmployeeInfo
{
	private String roleName;

	public String getRoleName()
	{
		return roleName;
	}

	@Override
	public void setRoleId(int roleId) throws Exception
	{
		super.setRoleId(roleId);
		Role role = EmployeeHelper.getRoleByRoleId(roleId);
		this.roleName = role.getRoleName();
	}
}