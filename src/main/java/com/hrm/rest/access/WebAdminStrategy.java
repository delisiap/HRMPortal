package com.hrm.rest.access;

import java.util.ArrayList;

import com.hrm.rest.HRMContext;
import com.hrm.rest.controller.SessionController;
import com.hrm.rest.utility.Constants;

public class WebAdminStrategy implements IAccessStrategy
{
	private ArrayList<String> allowedPages;

	public WebAdminStrategy()
	{
		allowedPages = new ArrayList<String>();
		allowedPages.add(Constants.WEB_ADMIN_HOMEPAGE_HTML);
		allowedPages.add(Constants.WEB_ADMIN_ADD_EMPLOYEE_HTML);
		allowedPages.add(Constants.WEB_ADMIN_REMOVE_EMPLOYEE_HTML);
		allowedPages.add(Constants.WEB_ADMIN_CONFIGURATION_HTML);
		allowedPages.add(Constants.ERROR_HTML);
	}

	@Override
	public String processUrl(String inputUrl)
	{
		SessionController sessionController = HRMContext.getSessionController();
		String ret = inputUrl;
		if (inputUrl.equals(Constants.LOGIN_HTML) || inputUrl.equals(Constants.ROOT_HTML) || allowedPages.indexOf(inputUrl) < 0)
		{
			ret = Constants.WEB_ADMIN_HOMEPAGE_HTML;
		}
		if (!inputUrl.equals(ret))
		{
			sessionController.httpRedirect(ret);
		}
		return ret;
	}
}