DELIMITER $$
CREATE PROCEDURE `get_password`(
	IN employee_id varchar(255),
	OUT pw_hash varchar(255)
)
BEGIN
	SELECT password_hash INTO pw_hash FROM EMPLOYEE_LOGIN WHERE EMPLOYEE_LOGIN.EMPLOYEE_ID=employee_id;
END$$
DELIMITER ;