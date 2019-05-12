DELIMITER $$
CREATE PROCEDURE `get_ctc`(
	IN employee_id varchar(10),
	IN ctc_year int(11),
	OUT emp_ctc blob
)
BEGIN
	SELECT CTC INTO emp_ctc
	FROM EMPLOYEE_CTC
	WHERE EMPLOYEE_CTC.EMPLOYEE_ID=employee_id AND EMPLOYEE_CTC.CTC_YEAR = ctc_year;
END$$
DELIMITER ;