package com.hrm.rest.uploaddocuments;

import java.util.HashMap;

public interface IValidateDocuments
{
	public boolean validateempid(HashMap<String,String> result);

	public boolean validateYear(HashMap<String,String> result);

	public boolean validateBlob(HashMap<String,String> result);

	public HashMap<String,String> validateDocument();
}