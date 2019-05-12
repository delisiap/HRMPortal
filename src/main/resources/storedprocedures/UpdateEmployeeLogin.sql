DELIMITER $$
CREATE PROCEDURE `update_login_details`(
	IN emp_id varchar(10),
	IN new_password_hash varchar(200)
)
BEGIN
	UPDATE EMPLOYEE_LOGIN
	SET PASSWORD_HASH=new_password_hash
	WHERE EMPLOYEE_ID=emp_id;
END$$
DELIMITER ;