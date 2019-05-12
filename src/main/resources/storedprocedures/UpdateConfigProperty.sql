DROP PROCEDURE IF EXISTS `update_configproperty`
DELIMITER $$
CREATE PROCEDURE `update_configproperty`(
	IN PROPERTYNAME VARCHAR(255)
	IN PROPERTYVALUE VARCHAR(255)
)
BEGIN
	UPDATE CONFIG_PROPERTY
	SET PROPERTY_VALUE = PROPERTYVALUE
	WHERE PROPERTY_NAME = PROPERTYNAME;
END$$
DELIMITER ;