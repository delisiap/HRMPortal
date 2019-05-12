DELIMITER $$
CREATE PROCEDURE `add_ctc`(
	IN emp_id varchar(10),
	IN emp_ctc_yr int(11),
	IN emp_ctc longblob
)
BEGIN
	insert into EMPLOYEE_CTC (EMPLOYEE_ID,CTC_YEAR,CTC) 
	values (emp_id,emp_ctc_yr,emp_ctc);
END$$
DELIMITER ;