DELIMITER $$
CREATE PROCEDURE `add_login_details`(
	IN pwd_hash varchar(200) ,
	IN emp_id varchar(10)
)
BEGIN
	insert into EMPLOYEE_LOGIN (PASSWORD_HASH,EMPLOYEE_ID) values (pwd_hash,emp_id);
END$$
DELIMITER ;