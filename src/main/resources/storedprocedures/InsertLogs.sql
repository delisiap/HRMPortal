DELIMITER $$
CREATE PROCEDURE `insert_logs`(
	IN emp_id varchar(10),
	IN log_time datetime,
	IN log_level varchar(10),
	IN log_msg varchar(10000),
	IN class_name varchar(100)
)
BEGIN
	insert into LOGS (EMPLOYEE_ID,LOG_TIME,LOG_LEVEL,LOG_MESSAGE,CLASS_NAME) values (emp_id,log_time,log_level,log_msg,class_name);
END$$
DELIMITER ;