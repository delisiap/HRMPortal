package com.hrm.rest.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrm.rest.employee.EmployeeHelper;
import com.hrm.rest.role.Role;
import com.hrm.rest.utility.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sessionController")
public class SessionController
{
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private HttpServletResponse httpServletResponse;

	private static final String EMP_ID = "EMP_ID";
	private static final String USER_LOGGED_IN = "USER_LOGGED_IN";
	private static final String ADMIN_LOGGED_IN = "ADMIN_LOGGED_IN";
	private static final String WEB_ADMIN_LOGGED_IN = "WEB_ADMIN_LOGGED_IN";

	public HttpSession getSession()
	{
		return httpSession;
	}

	public boolean isUserLoggedIn()
	{
		// Do not log in this method. Logging uses this method.
		String userLoggedIn = (String) httpSession.getAttribute(SessionController.USER_LOGGED_IN);
		return Constants.TRUE.equals(userLoggedIn);
	}

	public boolean isAdminLoggedIn()
	{
		boolean userLoggedIn = this.isUserLoggedIn();
		String adminLoggedIn = (String) httpSession.getAttribute(SessionController.ADMIN_LOGGED_IN);

		return userLoggedIn && Constants.TRUE.equals(adminLoggedIn);
	}

	public boolean isWebAdminLoggedIn()
	{
		boolean userLoggedIn = this.isUserLoggedIn();
		String webAdminLoggedIn = (String) httpSession.getAttribute(SessionController.WEB_ADMIN_LOGGED_IN);

		return userLoggedIn && Constants.TRUE.equals(webAdminLoggedIn);
	}

	public void processLogin(String empId)
	{
		try
		{
			String adminUser = Constants.FALSE;
			String webAdmin = Constants.FALSE;
			if (empId.equals(Constants.DEFAULT_ADMIN_USERID))
			{
				webAdmin = Constants.TRUE;
			}
			else
			{
				Role role = EmployeeHelper.getRoleByEmployeeId(empId);
				if (role.getIsAdmin())
				{
					adminUser = Constants.TRUE;
				}
			}
			httpSession.setAttribute(SessionController.EMP_ID, empId);
			httpSession.setAttribute(SessionController.USER_LOGGED_IN, Constants.TRUE);
			httpSession.setAttribute(ADMIN_LOGGED_IN, adminUser);
			httpSession.setAttribute(WEB_ADMIN_LOGGED_IN, webAdmin);
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.fatal(e, this.getClass().getName(), Logger.Location.ALL);
			redirect(Constants.ERROR_HTML);
		}
	}

	public void processLogout()
	{
		redirect(Constants.LOGIN_HTML);
		httpSession.invalidate();
	}

	public String getLoggedInUserId()
	{
		// Do not log in this method. Logging uses this method.
		if (this.isUserLoggedIn())
		{
			return (String) httpSession.getAttribute(SessionController.EMP_ID);
		}
		return null;
	}

	public void redirect(String location)
	{
		try
		{
			httpServletResponse.setHeader("Redirect-Url", location);
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.fatal(e, this.getClass().getName(), Logger.Location.ALL);
		}
	}

	public void httpRedirect(String location)
	{
		try
		{
			httpServletResponse.sendRedirect(location);
		}
		catch (Exception e)
		{
			Logger logger = Logger.getInstance();
			logger.fatal(e, this.getClass().getName(), Logger.Location.ALL);
		}
	}
}