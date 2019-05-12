package com.hrm.rest;

import com.hrm.rest.controller.SessionController;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class HRMContext implements ApplicationContextAware
{
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		HRMContext.applicationContext = applicationContext;
	}

	public static SessionController getSessionController()
	{
		return (SessionController) applicationContext.getBean("sessionController");
	}
}