/* SQL File for the table creation DO NOT edit this, unless you know what you're doing! */
CREATE TABLE IF NOT EXISTS `partner` (
 `PartnerID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `birthday` date NOT NULL,
 `firstname` varchar(20) NOT NULL,
 `secondname` varchar(20) NOT NULL,
 `title` int(11) NOT NULL,
 `comment` varchar(100) NOT NULL,
 PRIMARY KEY (`PartnerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v1.0';
CREATE TABLE IF NOT EXISTS `addresses` (
 `AddressID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `plc` int(11) NOT NULL,
 `toponym` varchar(20) NOT NULL,
 `housenr` smallint(6) NOT NULL,
 `street` varchar(20) NOT NULL,
 `addition` varchar(50) NOT NULL,
 `comment` varchar(100) NOT NULL,
 PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v1.0';
CREATE TABLE IF NOT EXISTS `roles` (
 `RoleID` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `role` varchar(15) NOT NULL,
 PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v1.0';
