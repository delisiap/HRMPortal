DELIMITER $$
CREATE PROCEDURE `add_role`(
	IN emp_id varchar(10),
	IN emp_role_id int,
	IN emp_role_name varchar(100)
)
BEGIN
	insert into EMPLOYEE_ROLES (ID,ROLE_ID,ROLE_NAME) 
	values (emp_id ,emp_role_id,emp_role_name,emp_doj,emp_phno,emp_email,emp_aemail,emp_address,emp_id,emp_leaves);
END$$
DELIMITER ;