DROP SCHEMA IF EXISTS `quality-inspect-web`;

CREATE SCHEMA `quality-inspect-web`;

use `quality-inspect-web`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `item_detail`
--

DROP TABLE IF EXISTS `item_detail`;

CREATE TABLE `item_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maker` varchar(45) DEFAULT NULL,
  `description` text DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `item_detail_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `NAME_UNIQUE` (`name`),
  
  KEY `FK_DETAIL_idx` (`item_detail_id`),
  
  CONSTRAINT `FK_DETAIL` 
  FOREIGN KEY (`item_detail_id`) 
  REFERENCES `item_detail` (`id`) 
  
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
  `item_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `FORM_UNIQUE` (`name`, `item_id`),
  
  KEY `FK_item_idx` (`item_id`),
  
  CONSTRAINT `FK_item` 
  FOREIGN KEY (`item_id`) 
  REFERENCES `item` (`id`) 
  
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
-- sample data for items + details(2), forms(2 per item) and some steps for every form
--

LOCK TABLES `item_detail` WRITE;
/*!40000 ALTER TABLE `item_detail` DISABLE KEYS */;

INSERT INTO `item_detail` VALUES 
	(1,'ACME','This is normal item'),
	(2,'NOTME','This is odd item');

/*!40000 ALTER TABLE `item_detail` ENABLE KEYS */;

--
-- sample data for items
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` VALUES 
	(1,'Test item 1', 1),
	(2,'Test item X', 2);

/*!40000 ALTER TABLE `item` ENABLE KEYS */;

--
-- sample data for  forms(2 per item)
--

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;

INSERT INTO `form` VALUES 
	(1,'Daily', 'Standard daily inspection', 1),
	(2,'Annual', 'Inspection made once a year', 1),
    (3,'Fusion test', 'Special inspection for item X', 2),
	(4,'Anti-alien', 'Check if there are any dangerous aliens in item X', 2);

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




