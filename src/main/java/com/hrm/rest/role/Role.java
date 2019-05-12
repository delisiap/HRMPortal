package com.hrm.rest.role;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "role")
public class Role
{
	private int roleId;
	private String roleRank;
	private String roleName;
	private boolean isAdmin;

	public int getRoleId()
	{
		return roleId;
	}

	@XmlElement(name="id")
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	@XmlElement(name="name")
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getRoleRank()
	{
		return roleRank;
	}

	@XmlElement(name="rank")
	public void setRoleRank(String roleRank)
	{
		this.roleRank = roleRank;
	}

	public boolean getIsAdmin()
	{
		return isAdmin;
	}

	@XmlElement(name="admin")
	public void setIsAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}

	public boolean isRoleValid()
	{
		// Role is valid if id and rank are an integer having values greater than zero
		// and name is string.
		if (null == roleName || roleName.isEmpty())
		{
			return false;
		}
		try
		{
			if (roleId <= 0)
			{
				return false;
			}
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		catch (NullPointerException e)
		{
			return false;
		}
		try
		{
			if (Integer.parseInt(roleRank) <= 0)
			{
				return false;
			}
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		catch (NullPointerException e)
		{
			return false;
		}
		return true;
	}
}