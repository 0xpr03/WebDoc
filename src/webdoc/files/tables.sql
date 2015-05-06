/* SQL File for the table creation DO NOT edit this, unless you know what you're doing! */
/* lines starting with this are IGNORES! completely */
/* "IF NOT EXISTS" is REQUIRED! */
/* DO NOT MODIFY! exact pattern matching */
/* Table partner / Partner */
CREATE TABLE IF NOT EXISTS `partner` (
 `PartnerID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `birthday` date NOT NULL,
 `firstname` varchar(20) NOT NULL,
 `secondname` varchar(20) NOT NULL,
 `title` int(11) NOT NULL,
 `comment` varchar(100) DEFAULT NULL,
 PRIMARY KEY (`PartnerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table addresses / Adresse */
CREATE TABLE IF NOT EXISTS `addresses` (
 `AddressID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `plc` int(11) NOT NULL,
 `toponym` varchar(20) NOT NULL,
 `housenr` smallint(6) NOT NULL,
 `street` varchar(20) NOT NULL,
 `addition` varchar(50) NOT NULL,
 PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.2';
/* Table roles / Rollen */
CREATE TABLE IF NOT EXISTS `roles` (
 `RoleID` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `role` varchar(15) NOT NULL,
 PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table communicatontype / Kommunikationsart */
CREATE TABLE IF NOT EXISTS `communicatontype` (
 `CommunicationID` int(10) unsigned NOT NULL,
 `name` varchar(20) NOT NULL,
 PRIMARY KEY (`CommunicationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table telecommunication / Telekommunikation */
CREATE TABLE IF NOT EXISTS `telecommunication` (
 `CommunicationID` int(10) unsigned NOT NULL,
 `areacode` varchar(10) NOT NULL,
 `number` varchar(20) NOT NULL,
 `TeleID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 PRIMARY KEY (`TeleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.2';
/* Table email / Email */
CREATE TABLE IF NOT EXISTS `email` (
 `CommunicationID` int(11) NOT NULL,
 `MailID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `mail` varchar(40) NOT NULL,
 PRIMARY KEY (`MailID`),
 KEY `CommunicationID` (`CommunicationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table partnerroles / Rollen */
CREATE TABLE IF NOT EXISTS `partnerroles` (
 `PartnerID` int(10) unsigned NOT NULL,
 `RoleID` int(10) unsigned NOT NULL,
 PRIMARY KEY (`PartnerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table race / Rasse */
CREATE TABLE IF NOT EXISTS `race` (
 `RaceID` int(11) NOT NULL AUTO_INCREMENT,
 `race` varchar(20) NOT NULL,
 PRIMARY KEY (`RaceID`),
 KEY `race` (`race`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.3';
/* Table animal / Tier */
CREATE TABLE IF NOT EXISTS `animal` (
 `AnimalID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `Name` varchar(50) NOT NULL,
 `Callname` varchar(20) NOT NULL,
 `identification` varchar(30) NOT NULL,
 `coatcolor` varchar(20) NOT NULL,
 `weight` double NOT NULL,
 `birthdate` date NOT NULL,
 `gender` tinyint(1) NOT NULL,
 `PictureID` int(10) unsigned NOT NULL,
 `RaceID` int(10) unsigned NOT NULL,
 `comment` varchar(100) DEFAULT NULL,
 PRIMARY KEY (`AnimalID`),
 KEY `Callname` (`Callname`),
 KEY `Name` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.7';
/* Table Anamnese / anamnesis */
CREATE TABLE IF NOT EXISTS `anamnesis` (
 `AnamnesisID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `AnimalID` int(10) unsigned NOT NULL,
 `purpose` varchar(25) NOT NULL,
 `keeping` varchar(50) NOT NULL,
 `possesionsince` date NOT NULL,
 `origin` varchar(50) NOT NULL,
 `familystrchanges` text NOT NULL,
 `abroadstays` varchar(250) NOT NULL,
 `attitudeconspicuity` varchar(250) NOT NULL,
 `behaviourmodification` varchar(250) NOT NULL,
 `injurys` text NOT NULL,
 `scars` text NOT NULL,
 PRIMARY KEY (`AnamnesisID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table Beziehungen / relationship */
CREATE TABLE IF NOT EXISTS `relationship` (
 `RoleID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `PartnerID` int(10) unsigned NOT NULL,
 `AnimalID` int(10) unsigned NOT NULL,
 PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table BehandlungTier / animalthreatment */
CREATE TABLE IF NOT EXISTS `animalthreatment` (
 `TreatmentID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `AnimalID` int(10) unsigned NOT NULL,
 `RoleID` int(10) unsigned NOT NULL,
 `amount` tinyint(4) NOT NULL,
 `date` date NOT NULL,
 `time` time NOT NULL,
 `comment` varchar(250) NOT NULL,
 PRIMARY KEY (`TreatmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';