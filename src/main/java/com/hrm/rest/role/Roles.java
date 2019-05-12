package com.hrm.rest.role;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.hrm.rest.controller.Logger;
import com.hrm.rest.io.XMLReader;

@XmlRootElement(name = "roles")
public class Roles
{
	private static final String ROLES_FILE_NAME = "roles.xml";
	private static Roles rolesInstance = null;

	private ArrayList<Role> roles = new ArrayList<>();

	public static Roles getInstance() throws InvalidRolesConfigurationException
	{
		if (rolesInstance == null)
		{
			rolesInstance = Roles.initializeRoles();
		}
		return rolesInstance;
	}

	private static Roles initializeRoles() throws InvalidRolesConfigurationException
	{
		Roles ret = null;
		try
		{
			XMLReader xmlReader = new XMLReader(Roles.ROLES_FILE_NAME, Roles.class);
			ret = (Roles) xmlReader.readXML();
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.fatal(e, Roles.class.getName(), Logger.Location.ALL);
			throw new InvalidRolesConfigurationException();
		}
		return ret;
	}

	public ArrayList<Role> getRoles()
	{
		return roles;
	}

	public ArrayList<String> getRoleNames()
	{
		ArrayList<String> roleNames = new ArrayList<String>();
		Iterator<Role> roles = this.getRoles().iterator();
		while(roles.hasNext())
		{
			Role role = roles.next();
			roleNames.add(role.getRoleName());
		}
		return roleNames;
	}

	@XmlElement(name = "role")
	public void setRoles(ArrayList<Role> roles)
	{
		this.roles = roles;
	}

	public void add(Role role)
	{
		roles.add(role);
	}
}