package com.hrm.rest.model;

import static org.junit.Assert.assertEquals;

import com.hrm.rest.dblayer.DatabaseConnector;

import org.junit.Test;

public class DatabaseConnectorTest {
	@Test
	public void employeeLoginTestCases() {

		DatabaseConnector dbConnector = DatabaseConnector.getInstance();

		assertEquals(true, dbConnector != null);
		assertEquals(false, dbConnector == null);

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
