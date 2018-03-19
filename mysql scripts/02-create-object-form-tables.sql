DROP SCHEMA IF EXISTS `quality-inspect-web`;

CREATE SCHEMA `quality-inspect-web`;

use `quality-inspect-web`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `object_detail`;

CREATE TABLE `object_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maker` varchar(45) DEFAULT NULL,
  `description` text DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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




-- need sample data for objects + details(2), forms(2 per object) and some steps for every form





SET FOREIGN_KEY_CHECKS = 1;
