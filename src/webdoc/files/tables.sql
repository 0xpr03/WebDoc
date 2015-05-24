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
 `title` varchar(11) NOT NULL,
 `comment` varchar(100) DEFAULT NULL,
 PRIMARY KEY (`PartnerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table addresses / Adresse */
CREATE TABLE IF NOT EXISTS `addresses` (
 `PartnerRoleID` int(10) unsigned NOT NULL,
 `plc` int(11) NOT NULL,
 `city` varchar(20) NOT NULL,
 `district` varchar(20) NOT NULL,
 `housenr` smallint(6) NOT NULL,
 `street` varchar(20) NOT NULL,
 `addition` varchar(50) NOT NULL,
 PRIMARY KEY (`PartnerRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.3';
/* Table roles / Rollen */
CREATE TABLE IF NOT EXISTS `roles` (
 `RoleID` int(6) unsigned NOT NULL AUTO_INCREMENT,
 `role` varchar(15) NOT NULL,
 PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';
/* Table communicatontype / Kommunikationsart */
CREATE TABLE IF NOT EXISTS `communicationtype` (
 `CommunicationID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `name` varchar(20) NOT NULL,
 PRIMARY KEY (`CommunicationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.2';
/* Table telecommunication / Telekommunikation */
CREATE TABLE IF NOT EXISTS `telecommunication` (
 `CommunicationID` int(10) unsigned NOT NULL,
 `PartnerRoleID` int(10) unsigned NOT NULL,
 `number` varchar(20) NOT NULL,
 PRIMARY KEY (`CommunicationID`,`PartnerRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.4';
/* Table email / Email */
CREATE TABLE IF NOT EXISTS `email` (
 `PartnerRoleID` int(10) unsigned NOT NULL,
/* `MailID` int(10) unsigned NOT NULL AUTO_INCREMENT,*/
 `mail` varchar(40) NOT NULL,
 PRIMARY KEY (`PartnerRoleID`)
/* KEY `PartnerRoleID` (`PartnerRoleID`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.3';
/* Table partnerroles / Rollen */
CREATE TABLE IF NOT EXISTS `partnerroles` (
 `PartnerRoleID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `PartnerID` int(10) unsigned NOT NULL,
 `RoleID` int(10) unsigned NOT NULL,
 PRIMARY KEY (`PartnerRoleID`),
 KEY `PartnerID` (`PartnerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.2';
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
 `purpose` varchar(25) DEFAULT NULL,
 `keeping` varchar(50) DEFAULT NULL,
 `possesionsince` date DEFAULT NULL,
 `origin` varchar(50) DEFAULT NULL,
 `familystrchanges` text DEFAULT NULL,
 `abroadstays` varchar(250) DEFAULT NULL,
 `attitudeconspicuity` varchar(250) DEFAULT NULL,
 `injurys` text DEFAULT NULL,
 `scars` text DEFAULT NULL,
 `infectiousDisease` text DEFAULT NULL,
 `regularVaccinations` text DEFAULT NULL,
 `breathing` text DEFAULT NULL,
 `digestiveTract` text DEFAULT NULL,
 `endocrineSystem` text DEFAULT NULL,
 `hyperthyroidism` text DEFAULT NULL,
 `pancreas` text DEFAULT NULL,
 `ZNS` text DEFAULT NULL,
 `epileptiformAttacks` tinyint(1)  NOT NULL,
 `medication` text DEFAULT NULL,
 `x-ray` text DEFAULT NULL,
 `CT_MRT` text DEFAULT NULL,
 `mainproblem` text DEFAULT NULL,
 `descrPatientOwner` text DEFAULT NULL,
 `wasUndertaken` text DEFAULT NULL,
 `painSensitivity` tinyint(1)  NOT NULL,
 `patientHasPain` tinyint(1)  NOT NULL,
 `painkillerReaction` text DEFAULT NULL,
 `motionCausingPain` text DEFAULT NULL,
 `motorInterference` text DEFAULT NULL,
 `bodyPartUsagePossible` text DEFAULT NULL,
 `possibleWalkDistance` double DEFAULT NULL,
 `possibleWalkDuration` time DEFAULT NULL,
 `weatherDependent` tinyint(1)  NOT NULL,
 `cycleCorrelation` text DEFAULT NULL,
 `outlet` double DEFAULT NULL,
 `availableTimeCons` time DEFAULT NULL,
 `comment` text DEFAULT NULL,
 `circulation` text DEFAULT NULL,
 `insertDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `editDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`AnamnesisID`),
 KEY `AnimalID` (`AnimalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.4';
/* Table Beziehungen / relationship */
CREATE TABLE IF NOT EXISTS `relationship` (
 `PartnerID` int(10) unsigned NOT NULL,
 `AnimalID` int(10) unsigned NOT NULL,
 PRIMARY KEY (`PartnerID`,`AnimalID`),
 KEY `PartnerID` (`PartnerID`)
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
 PRIMARY KEY (`TreatmentID`),
 KEY `AnimalID` (`AnimalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='v0.1';