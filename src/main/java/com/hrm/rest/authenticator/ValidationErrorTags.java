package com.hrm.rest.authenticator;

public enum ValidationErrorTags
{
	STATUS("status"),
	MESSAGE("message"),
	ERROR("error");

	private String name;
	ValidationErrorTags(String string)
	{
		name=string;
	}

	public String getErrorTags()
	{
		return name;
	}

	public static ValidationErrorTags getErrorTags(final String name)
	{
		for (ValidationErrorTags tag : ValidationErrorTags.values())
		{
			if (name.equals(tag.toString()))
			{
				return tag;
			}
		}
		return null;
	}

	@Override
	public String toString()
	{
		return name;
	}
}