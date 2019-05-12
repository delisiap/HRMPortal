DELIMITER $$
CREATE PROCEDURE `update_employee`(
	IN emp_id varchar(10),
	IN emp_phno long,
	IN emp_aemail varchar(100),
	IN emp_address varchar(200)
)
BEGIN
	UPDATE EMPLOYEE_INFO
	SET PHONE_NO=emp_phno, ALTERNATE_EMAIL_ID=emp_aemail, ADDRESS=emp_address
	WHERE EMPLOYEE_ID=emp_id;
END$$
DELIMITER ;