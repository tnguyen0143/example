DROP DATABASE IF EXISTS myRetailDb;

CREATE DATABASE myRetailDb;

CREATE USER 'test_user'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES
ON myRetailDb.*
TO 'test_user'@'localhost'
IDENTIFIED BY 'password'
WITH GRANT OPTION;

USE myRetailDb;

DROP TABLE IF EXISTS `price`;

CREATE TABLE `price` (
  `id` varchar(255) NOT NULL PRIMARY KEY,
  `price` double NOT NULL
);


LOCK TABLES `price` WRITE;
INSERT INTO `price` VALUES ('5543',13.37),('5555',199.99),('7563',148.99);
UNLOCK TABLES;


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(255) NOT NULL  PRIMARY KEY,
  `sku` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `last_updated` date DEFAULT NULL
);

LOCK TABLES `product` WRITE;
INSERT INTO `product` VALUES ('5543','IOL123','Optimus Prime','toys','2014-02-01'),
							 ('5555','AEX143','Stroller','baby','2014-05-23'),
							 ('7563','XYZ904','Sega Genesis','toys','1989-04-01');

UNLOCK TABLES;
