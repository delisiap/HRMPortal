DELIMITER $$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `update_employee_leavestatus`(
	IN autoid int(11),
    IN emp_id varchar(10),
    IN emp_status varchar(10)
)
BEGIN
	UPDATE EMPLOYEE_LEAVE_INFO
    SET STATUS=emp_status
    WHERE Id=autoid AND EMPLOYEE_ID=emp_id;
END$$
DELIMITER ;
