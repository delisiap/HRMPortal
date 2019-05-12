DELIMITER $$
CREATE PROCEDURE `get_Salary_Slip`(
	IN employee_id varchar(10),
	IN month_year date,
	OUT emp_salary_slip blob
)
BEGIN
	SELECT SALARY_SLIP INTO emp_salary_slip
	FROM EMPLOYEE_SALARY_SLIP
	WHERE EMPLOYEE_SALARY_SLIP.EMPLOYEE_ID=employee_id AND EMPLOYEE_SALARY_SLIP.MONTH_YEAR = month_year;
END$$
DELIMITER ;