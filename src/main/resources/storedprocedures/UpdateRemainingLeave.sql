DELIMITER $$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `update_employee_remainingleave`(
	IN emp_id varchar(10),
    IN employee_leaves int(11)
)
BEGIN
	UPDATE EMPLOYEE_INFO
    SET NO_OF_LEAVES=employee_leaves
    WHERE EMPLOYEE_ID=emp_id;
END$$
DELIMITER ;
