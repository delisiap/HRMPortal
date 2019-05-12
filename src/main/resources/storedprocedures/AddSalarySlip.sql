DELIMITER $$
CREATE PROCEDURE `add_salary_slip`(
	IN emp_id varchar(10),
	IN month_year date,
	IN salary_slip longblob
)
BEGIN
	insert into EMPLOYEE_SALARY_SLIP(EMPLOYEE_ID, MONTH_YEAR, SALARY_SLIP)
	values (emp_id, month_year, salary_slip);
END$$
DELIMITER ;