package com.hrm.rest.access;

import java.util.ArrayList;

import com.hrm.rest.HRMContext;
import com.hrm.rest.controller.SessionController;
import com.hrm.rest.utility.Constants;

public class AdminStrategy implements IAccessStrategy
{
	private ArrayList<String> allowedPages;

	public AdminStrategy()
	{
		allowedPages = new ArrayList<String>();
		allowedPages.add(Constants.ADMIN_HOMEPAGE_HTML);
		allowedPages.add(Constants.ADMIN_VIEW_PROFILE_HTML);
		allowedPages.add(Constants.ADMIN_APPLY_LEAVE_HTML);
		allowedPages.add(Constants.ADMIN_VIEW_LEAVE_STATUS_HTML);
		allowedPages.add(Constants.ADMIN_DOWNLOAD_CTC_HTML);
		allowedPages.add(Constants.ADMIN_DOWNLOAD_SALARY_SLIP_HTML);
		allowedPages.add(Constants.ADD_EMPLOYEE_HTML);
		allowedPages.add(Constants.REMOVE_EMPLOYEE_HTML);
		allowedPages.add(Constants.UPLOAD_CTC_HTML);
		allowedPages.add(Constants.UPLOAD_SALARY_SLIP_HTML);
		allowedPages.add(Constants.PROCESS_LEAVE_HTML);
		allowedPages.add(Constants.ERROR_HTML);
	}

	@Override
	public String processUrl(String inputUrl)
	{
		SessionController sessionController = HRMContext.getSessionController();
		String ret = inputUrl;
		if (inputUrl.equals(Constants.LOGIN_HTML) || inputUrl.equals(Constants.ROOT_HTML) || allowedPages.indexOf(inputUrl) < 0)
		{
			ret = Constants.ADMIN_HOMEPAGE_HTML;
		}
		if (!inputUrl.equals(ret))
		{
			sessionController.httpRedirect(ret);
		}
		return ret;
	}
}