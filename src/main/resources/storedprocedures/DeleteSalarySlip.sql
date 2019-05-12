DELIMITER $$
CREATE PROCEDURE `delete_salary_slip`(
	IN emp_id varchar(10),
	IN month_salary date
)
BEGIN
	DELETE FROM EMPLOYEE_SALARY_SLIP WHERE EMPLOYEE_ID=emp_id AND MONTH_YEAR=month_year;
END$$
DELIMITER ;