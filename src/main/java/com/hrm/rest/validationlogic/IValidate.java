package com.hrm.rest.validationlogic;

import java.util.HashMap;
import com.hrm.rest.model.EmployeeLeaveInfo;

public interface IValidate
{
	void checknextproperty(IValidate nextproperty);
	HashMap<String,String> Validate(EmployeeLeaveInfo property);
}