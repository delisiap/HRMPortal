DELIMITER $$
CREATE PROCEDURE `update_ctc`(
	IN emp_id varchar(10),
	IN ctc_year int(5),
	IN ctc longblob
)
BEGIN
	UPDATE EMPLOYEE_CTC
	SET CTC=ctc
	WHERE EMPLOYEE_ID=emp_id AND CTC_YEAR=ctc_year;
END$$
DELIMITER ;