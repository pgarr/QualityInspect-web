DROP SCHEMA IF EXISTS `quality-inspect-web`;

CREATE SCHEMA `quality-inspect-web`;

use `quality-inspect-web`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `object_detail`
--

DROP TABLE IF EXISTS `object_detail`;

CREATE TABLE `object_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maker` varchar(45) DEFAULT NULL,
  `description` text DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;

CREATE TABLE `object` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `object_detail_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `NAME_UNIQUE` (`name`),
  
  KEY `FK_DETAIL_idx` (`object_detail_id`),
  
  CONSTRAINT `FK_DETAIL` 
  FOREIGN KEY (`object_detail_id`) 
  REFERENCES `object_detail` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `form`
--

DROP TABLE IF EXISTS `form`;

CREATE TABLE `form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `object_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `FORM_UNIQUE` (`name`, `object_id`),
  
  KEY `FK_OBJECT_idx` (`object_id`),
  
  CONSTRAINT `FK_OBJECT` 
  FOREIGN KEY (`object_id`) 
  REFERENCES `object` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;

CREATE TABLE `step` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text DEFAULT NULL,
  `details` text DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `form_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_FORM_idx` (`form_id`),
  
  CONSTRAINT `FK_FORM` 
  FOREIGN KEY (`form_id`) 
  REFERENCES `form` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- sample data for objects + details(2), forms(2 per object) and some steps for every form
--

LOCK TABLES `object_detail` WRITE;
/*!40000 ALTER TABLE `object_detail` DISABLE KEYS */;

INSERT INTO `object_detail` VALUES 
	(1,'ACME','This is normal object'),
	(2,'NOTME','This is odd object');

/*!40000 ALTER TABLE `object_detail` ENABLE KEYS */;

--
-- sample data for objects
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;

INSERT INTO `object` VALUES 
	(1,'Test Object 1', 1),
	(2,'Test Object X', 2);

/*!40000 ALTER TABLE `object` ENABLE KEYS */;

--
-- sample data for  forms(2 per object)
--

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;

INSERT INTO `form` VALUES 
	(1,'Daily', 'Standard daily inspection', 1),
	(2,'Annual', 'Inspection made once a year', 1),
    (3,'Fusion test', 'Special inspection for object X', 2),
	(4,'Anti-alien', 'Check if there are any dangerous aliens in object X', 2);

/*!40000 ALTER TABLE `form` ENABLE KEYS */;

--
-- sample data for some steps for every form
--

LOCK TABLES `step` WRITE;
/*!40000 ALTER TABLE `step` DISABLE KEYS */;

INSERT INTO `step` VALUES 
	(1,'First step', 'do this', 1, 1),
	(2,'Second step', 'do that', 2, 1),
    (3,'Step number 1', 'do some stuff', 1, 2),
	(4,'Step number 2', 'do more stuff', 2, 2),
	(5,'Prepare fusion test', 'check parameters etc.', 1, 3),
	(6,'Make fusion test', 'lets rock!', 2, 3),
    (7,'Check exterior', 'Look for aliens outside', 1, 4),
	(8,'Check interior', 'Look for aliens inside', 2, 4);

/*!40000 ALTER TABLE `step` ENABLE KEYS */;

UNLOCK TABLES;




