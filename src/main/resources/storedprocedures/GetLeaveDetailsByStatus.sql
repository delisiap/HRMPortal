DELIMITER $$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `get_leavedetails_bystatus`(
	IN employee_status varchar(10)
)
BEGIN
	SELECT * FROM EMPLOYEE_LEAVE_INFO WHERE STATUS=employee_status;
END$$
DELIMITER ;
