DELIMITER $$
CREATE PROCEDURE `get_employee`(
	IN emp_id varchar(10),
	OUT emp_name varchar(100),
	OUT emp_role_id int,
	OUT emp_dob date,
	OUT emp_doj date,
	OUT emp_phno long,
	OUT emp_email varchar(100),
	OUT emp_aemail varchar(100),
	OUT emp_address varchar(200),
	OUT emp_leaves int
)
BEGIN
	SELECT EMPLOYEE_NAME,ROLE_ID,DATE_OF_BIRTH,DATE_OF_JOINING,PHONE_NO,EMAIL_ID,ALTERNATE_EMAIL_ID,ADDRESS,NO_OF_LEAVES
	INTO emp_name,emp_role_id,emp_dob,emp_doj,emp_phno,emp_email,emp_aemail,emp_address,emp_leaves
	FROM EMPLOYEE_INFO WHERE EMPLOYEE_INFO.EMPLOYEE_ID=emp_id;
END$$
DELIMITER ;