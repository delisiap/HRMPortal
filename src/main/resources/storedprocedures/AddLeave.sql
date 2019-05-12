DELIMITER $$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `add_leave`(
	IN emp_id varchar(10),
	IN emp_startdate date,
	IN emp_enddate date,
	IN emp_status varchar(10)
)
BEGIN
	insert into EMPLOYEE_LEAVE_INFO (EMPLOYEE_ID,START_DATE,END_DATE,STATUS) 
	values (emp_id,emp_startdate,emp_enddate,emp_status);
END$$
DELIMITER ;
