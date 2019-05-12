package com.hrm.rest.controller;

import javax.servlet.http.HttpServletRequest;

import com.hrm.rest.HRMContext;
import com.hrm.rest.access.AccessContext;
import com.hrm.rest.access.AdminStrategy;
import com.hrm.rest.access.EmployeeStrategy;
import com.hrm.rest.access.IAccessStrategy;
import com.hrm.rest.access.WebAdminStrategy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController
{
	@GetMapping("/")
	public String rootMapping(HttpServletRequest httpServletRequest)
	{
		return urlMapping(httpServletRequest);
	}

	@GetMapping("/**.html")
	public String urlMapping(HttpServletRequest httpServletRequest)
	{
		String inputUrl = httpServletRequest.getRequestURI();
		IAccessStrategy accessStrategy = getAccessStrategy();
		AccessContext accessContext = AccessContext.getInstance(accessStrategy);
		// Adding substring for removing "/" because of a bug in thymeleaf.
		// https://github.com/spring-projects/spring-boot/issues/1744
		return accessContext.processUrl(inputUrl).substring(1);
	}

	private IAccessStrategy getAccessStrategy()
	{
		SessionController sessionController = HRMContext.getSessionController();
		IAccessStrategy ret = null;
		if(sessionController.isAdminLoggedIn())
		{
			ret = new AdminStrategy();
		}
		else if(sessionController.isWebAdminLoggedIn())
		{
			ret = new WebAdminStrategy();
		}
		else
		{
			ret = new EmployeeStrategy();
		}
		return ret;
	}
}