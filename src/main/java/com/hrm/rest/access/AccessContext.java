package com.hrm.rest.access;

public class AccessContext
{
	private static AccessContext accessContext;
	private IAccessStrategy accessStrategy;

	public AccessContext(IAccessStrategy accessStrategy)
	{
		this.accessStrategy = accessStrategy;
	}

	public static AccessContext getInstance(IAccessStrategy accessStrategy)
	{
		if (accessContext == null)
		{
			accessContext = new AccessContext(accessStrategy);
		}
		else
		{
			accessContext.changeStrategy(accessStrategy);
		}
		return accessContext;
	}

	public void changeStrategy(IAccessStrategy accessStrategy)
	{
		this.accessStrategy = accessStrategy;
	}

	public String processUrl(String inputUrl)
	{
		return accessStrategy.processUrl(inputUrl);
	}
}