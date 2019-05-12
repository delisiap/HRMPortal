DELIMITER $$
CREATE PROCEDURE `update_salary_slip`(
	IN emp_id varchar(10),
	IN month_year date,
	IN salary_slip longblob	
)
BEGIN
	UPDATE EMPLOYEE_SALARY_SLIP
	SET SALARY_SLIP=salary_slip
	WHERE EMPLOYEE_ID=emp_id AND MONTH_YEAR=month_year;
END$$
DELIMITER ;