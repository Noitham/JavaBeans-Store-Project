

CREATE USER 'shopbeansusr'@'localhost' IDENTIFIED BY 'shopbeanspsw';


CREATE DATABASE shopbeandb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;


GRANT select,insert,update,delete ON shopbeandb.* TO 'shopbeansusr'@'localhost' WITH GRANT OPTION;


USE shopbeandb;

CREATE TABLE `category` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`code` VARCHAR(40) DEFAULT NULL UNIQUE,
`description` VARCHAR(40) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `product` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`code` VARCHAR(40) DEFAULT NULL,
`description` VARCHAR(40) DEFAULT NULL,
`cost_price` DOUBLE DEFAULT NULL,
`stock` DOUBLE DEFAULT NULL,
`min_stock` DOUBLE DEFAULT NULL,
`category` VARCHAR(40) NOT NULL,
FOREIGN KEY (`category`) REFERENCES `category` (`code`),
PRIMARY KEY (`id`)
) ENGINE=InnoDB;


INSERT INTO `category` (`id`, `code`, `description`) VALUES
(1, 'Prueba1', 'Prueba1'),
(2, 'TV' ,'Television'),
(3, 'Prueba3', 'Prueba3'),
(4, 'Prueba4', 'Prueba4'),
(5, 'Prueba5', 'Prueba5'),
(6, 'Prueba6', 'Prueba6'),
(7, 'Prueba7', 'Prueba7'),
(8, 'Prueba8', 'Prueba8'),
(9, 'Prueba9', 'Prueba9'),
(10, 'Prueba10', 'Prueba10'),
(11, 'Prueba11', 'Prueba11'),
(12, 'Prueba12', 'Prueba12');


INSERT INTO `product` (`id`, `code`, `description`, `cost_price`, `stock`,
`min_stock`, `category`) VALUES
(1, 'Prueba1', 'Prueba1', 1, 1, 1, 'Prueba1'),
(2, 'Prueba2', 'Prueba2', 2, 2, 2, 'Prueba1'),
(3, 'TV1', 'Televisor 4K', 950, 10, 3, 'TV'),
(4, 'TV2', 'Televisor FULLHD ', 550, 20, 6, 'TV'),
(5, 'Prueba5', 'Prueba5', 5, 5, 5, 'Prueba5'),
(6, 'Prueba6', 'Prueba6', 6, 6, 6, 'Prueba6'),
(7, 'Prueba7', 'Prueba7', 7, 7, 7, 'Prueba7'),
(8, 'Prueba8', 'Prueba8', 8, 8, 8, 'Prueba8'),
(9, 'Prueba9', 'Prueba9', 9, 9, 9, 'Prueba9'),
(10, 'Prueba10', 'Prueba10', 10, 10, 10, 'Prueba10'),
(11, 'Prueba11', 'Prueba11', 11, 11, 11, 'Prueba11'),
(12, 'Prueba12', 'Prueba12', 12, 12, 12, 'Prueba1'),
(13, 'Prueba13', 'Prueba13', 13, 13, 13, 'Prueba1'),
(14, 'Prueba14', 'Prueba14', 14, 14, 14, 'Prueba1'),
(15, 'Prueba15', 'Prueba15', 15, 15, 15, 'Prueba1'),
(16, 'Prueba16', 'Prueba16', 16, 16, 16, 'Prueba1'),
(17, 'Prueba17', 'Prueba17', 17, 17, 17, 'Prueba1');