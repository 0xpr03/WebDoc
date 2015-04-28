/* **** PROCEDURES ** DO NOT TOUCH! **********/
DROP PROCEDURE IF EXISTS `insertPatient`$$
CREATE PROCEDURE `insertPatient`(
	IN `param_name` VARCHAR(50) CHARSET utf8,
	IN `param_callname` VARCHAR(20) CHARSET utf8,
	IN `param_identification` VARCHAR(30) CHARSET utf8,
	IN `param_coatcolor` VARCHAR(20) CHARSET utf8,
	IN `param_weight` DOUBLE,
	IN `param_birthdate` VARCHAR(8) CHARSET utf8,
	IN `param_gender` BOOLEAN,
	IN `param_race` VARCHAR(20) CHARSET utf8,
	IN `param_comment` VARCHAR(100) CHARSET utf8,
	IN `param_pictureid` INTEGER UNSIGNED )
    MODIFIES SQL DATA
BEGIN
	DECLARE `var_rID` INTEGER;

	SELECT `RaceID` INTO var_rID FROM `race` WHERE `race` = param_race;

	IF FOUND_ROWS() = 0
	THEN
		INSERT INTO `race` (`race`) VALUES (param_race);
		SET var_rID = LAST_INSERT_ID();
	END IF;

	INSERT INTO `animal` (`Name`, `Callname`, `identification`, `coatcolor`, `weight`,`birthdate`, `gender`, `PictureID`, `RaceID`, `comment`) 
	VALUES (param_name, param_callname, param_identification, param_coatcolor, param_weight, param_birthdate, param_gender, param_pictureid, var_rID, param_comment);
END$$