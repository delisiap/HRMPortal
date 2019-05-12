package com.hrm.rest.exceptionhandling;

public class HRMExceptions extends Exception
{
	private static final long serialVersionUID = 1L;

	private String message;
	private int errorCode;
	private Throwable cause;

	public HRMExceptions()
	{
		super();
	}

	public HRMExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HRMExceptions(String message, Throwable cause)
	{
		super(message, cause);
	}

	public HRMExceptions(String message)
	{
		super(message);
	}

	public HRMExceptions(Throwable cause)
	{
		super(cause);
	}

	public HRMExceptions(String message , int errorCode)
	{
		this.message = message;
		this.errorCode =errorCode;
	}

	public HRMExceptions(String message, int errorCode, Throwable cause)
	{
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.cause = cause;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}

	public Throwable getCause()
	{
		return cause;
	}

	public void setCause(Throwable cause)
	{
		this.cause = cause;
	}

	@Override
	public String toString()
	{
		return "HRM Exceptions message=" + message + ", errorCode=" + errorCode + ", cause=" + cause + "]";
	}
}