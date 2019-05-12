package com.hrm.rest.access;

import java.util.ArrayList;

import com.hrm.rest.HRMContext;
import com.hrm.rest.controller.SessionController;
import com.hrm.rest.utility.Constants;

public class EmployeeStrategy implements IAccessStrategy
{
	private ArrayList<String> allowedPages;

	public EmployeeStrategy()
	{
		allowedPages = new ArrayList<String>();
		allowedPages.add(Constants.LOGIN_HTML);
		allowedPages.add(Constants.HOMEPAGE_HTML);
		allowedPages.add(Constants.APPLY_LEAVE_HTML);
		allowedPages.add(Constants.DOWNLOAD_CTC_HTML);
		allowedPages.add(Constants.DOWNLOAD_SALARY_SLIP_HTML);
		allowedPages.add(Constants.VIEW_PROFILE_HTML);
		allowedPages.add(Constants.VIEW_LEAVE_STATUS_HTML);
		allowedPages.add(Constants.ERROR_HTML);
	}

	@Override
	public String processUrl(String inputUrl)
	{
		SessionController sessionController = HRMContext.getSessionController();
		String ret = inputUrl;
		if (sessionController.isUserLoggedIn())
		{
			if(inputUrl.equals(Constants.LOGIN_HTML) || inputUrl.equals(Constants.ROOT_HTML) || allowedPages.indexOf(inputUrl) < 0)
			{
				ret = Constants.HOMEPAGE_HTML;
			}
		}
		else if(!ret.equals(Constants.ERROR_HTML))
		{
			ret = Constants.LOGIN_HTML;
		}
		if(!inputUrl.equals(ret))
		{
			sessionController.httpRedirect(ret);
		}
		return ret;
	}
}