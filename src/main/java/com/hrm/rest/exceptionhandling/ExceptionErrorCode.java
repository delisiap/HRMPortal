package com.hrm.rest.exceptionhandling;

public class ExceptionErrorCode
{
	public static final int SUCCESS = 200;
	public static final int ACCEPTED = 202;

	public static final int REDIRECT_URL = 300;
	public static final int TEMPORARY_REDIRECT = 307;

	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int FORBIDDEN = 403;
	public static final int NOTFOUND = 404;
	public static final int METHOD_NOT_ALLOWED = 405;
	public static final int REQUEST_TIMEOUT = 408;
	public static final int TOOMANYREQUEST = 429;
	public static final int METHOD_FAILURE = 420;
	public static final int LOGIN_TIMEOUT = 440;
	public static final int NO_RESPONSE = 444;

	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int BAD_GATEWAY = 501;
}