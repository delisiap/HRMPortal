package com.hrm.rest.exceptionhandling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.hrm.rest.exceptionhandling.HRMExceptions;
import org.junit.Test;

public class HRMExceptionsTest
{
	@Test
	public void HRMExceptionsTestCases()
	{
		HRMExceptions hrmexceptions = null;
		assertNull(hrmexceptions);
		
		hrmexceptions = new HRMExceptions();
		hrmexceptions.setMessage("Sample Exception");
		hrmexceptions.setErrorCode(404);
		hrmexceptions.setCause(new NullPointerException());
		
		String hrmexceptions_str = hrmexceptions.toString();
		assertNotNull(hrmexceptions);
		assertNotNull(hrmexceptions_str);
		String exp_value="HRM Exceptions message="+hrmexceptions.getMessage()+", errorCode="+hrmexceptions.getErrorCode()+", cause="+hrmexceptions.getCause().toString()+"]";
		
		boolean stringEq = false;
		if(hrmexceptions_str.equals(exp_value));
		{
			stringEq = true;
		}
		assertTrue(stringEq);

		HRMExceptions hrmex1= new HRMExceptions("Sample Exception",new NullPointerException(),true, true );
		HRMExceptions hrmex2 =new HRMExceptions("Sample Exception",new NullPointerException());
		HRMExceptions hrmex3 =new HRMExceptions("Sample Exception");
		HRMExceptions hrmex4 =new HRMExceptions(new NullPointerException());
		HRMExceptions hrmex5 =new HRMExceptions("Sample Exception",404);
		HRMExceptions hrmex6 =new HRMExceptions("Sample Exception",404,new NullPointerException());
		
		assertNotNull(hrmex1);
		assertNotNull(hrmex2);
		assertNotNull(hrmex3);
		assertNotNull(hrmex4);
		assertNotNull(hrmex5);
		assertNotNull(hrmex6);

		assertNotNull(hrmex1.toString());
		assertNotNull(hrmex2.toString());
		assertNotNull(hrmex3.toString());
		assertNotNull(hrmex4.toString());
		assertNotNull(hrmex5.toString());
		assertNotNull(hrmex6.toString());
		
		String hrmex1_val1=hrmex1.toString();
		String hrmex2_val2=hrmex2.toString();
		String hrmex3_val3=hrmex3.toString();
		String hrmex4_val4=hrmex4.toString();
		String hrmex5_val5=hrmex5.toString();
		String hrmex6_val6=hrmex6.toString();
		
		assertEquals(hrmex1_val1,"HRM Exceptions message=null, errorCode=0, cause=null]");
		assertEquals(hrmex2_val2,"HRM Exceptions message=null, errorCode=0, cause=null]");
		assertEquals(hrmex3_val3,"HRM Exceptions message=null, errorCode=0, cause=null]");
		assertEquals(hrmex4_val4,"HRM Exceptions message=null, errorCode=0, cause=null]");
		assertEquals(hrmex5_val5,"HRM Exceptions message=Sample Exception, errorCode=404, cause=null]");
		assertEquals(hrmex6_val6,"HRM Exceptions message=Sample Exception, errorCode=404, cause=java.lang.NullPointerException]");
	}
}

