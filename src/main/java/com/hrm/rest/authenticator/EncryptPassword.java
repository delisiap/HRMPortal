package com.hrm.rest.authenticator;

import java.security.MessageDigest;
import com.hrm.rest.controller.Logger;
public class EncryptPassword
{
	private String password;
	private String hashedPassword;

	public EncryptPassword(String password)
	{
		setPassword(password);
	}

	private void generateHash()
	{
		StringBuilder hash = new StringBuilder();
		Logger logger = Logger.getInstance();
		try
		{
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(this.password.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int index = 0; index < hashedBytes.length; index++)
			{
				byte b = hashedBytes[index];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		}
		catch (Exception e)
		{
			logger.error(e, this.getClass().getName(), Logger.Location.ALL);
		}
		setHashedPassword(hash.toString());
	}

	public Boolean validatePassword(String dbPassword)
	{
		return dbPassword.equals(getHashedPassword());
	}

	public String getHashedPassword()
	{
		return this.hashedPassword;
	}

	public String getPassword()
	{
		return password;
	}

	private void setHashedPassword(String hashedPassword)
	{
		this.hashedPassword = hashedPassword;
	}

	public void setPassword(String password)
	{
		this.password = password;
		generateHash();
	}
}