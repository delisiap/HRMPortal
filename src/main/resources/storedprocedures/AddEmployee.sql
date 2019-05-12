DELIMITER $$
CREATE PROCEDURE `add_employee`(
	IN emp_id varchar(10),
	IN emp_name varchar(255),
	IN emp_role_id int,
	IN emp_dob date,
	IN emp_doj date,
	IN emp_phno long,
	IN emp_email varchar(100),
	IN emp_aemail varchar(100),
	IN emp_address varchar(200),
	IN emp_leaves int
)
BEGIN
	insert into EMPLOYEE_INFO (EMPLOYEE_NAME,ROLE_ID,DATE_OF_BIRTH,DATE_OF_JOINING,PHONE_NO,EMAIL_ID,ALTERNATE_EMAIL_ID,ADDRESS,EMPLOYEE_ID,NO_OF_LEAVES) 
	values (emp_name ,emp_role_id,emp_dob,emp_doj,emp_phno,emp_email,emp_aemail,emp_address,emp_id,emp_leaves);
END$$
DELIMITER ;