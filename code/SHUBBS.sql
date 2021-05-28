/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.22 : Database - shubbs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shubbs` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `shubbs`;

/*Table structure for table `ban` */

DROP TABLE IF EXISTS `ban`;

CREATE TABLE `ban` (
  `user_id` int NOT NULL,
  `out_time` date NOT NULL,
  PRIMARY KEY (`user_id`,`out_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ban` */

insert  into `ban`(`user_id`,`out_time`) values (1,'2021-05-18'),(2,'2021-05-18'),(4,'2021-05-29');

/*Table structure for table `block` */

DROP TABLE IF EXISTS `block`;

CREATE TABLE `block` (
  `block_id` int NOT NULL AUTO_INCREMENT,
  `block_name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `block_level` int NOT NULL,
  `block_number` int NOT NULL,
  PRIMARY KEY (`block_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `block` */

insert  into `block`(`block_id`,`block_name`,`block_level`,`block_number`) values (1,'修改后的板块2',2,1),(2,'板块2',2,0),(3,'板块3',1,1);

/*Table structure for table `invitecode` */

DROP TABLE IF EXISTS `invitecode`;

CREATE TABLE `invitecode` (
  `invite_code_id` int NOT NULL AUTO_INCREMENT,
  `invite_code_content` char(16) NOT NULL,
  `invite_code_state` int NOT NULL,
  `user1_id` int NOT NULL,
  `user2_id` int DEFAULT NULL,
  `invite_code_date` date NOT NULL,
  PRIMARY KEY (`invite_code_id`),
  KEY `user1_id` (`user1_id`),
  KEY `user2_id` (`user2_id`),
  CONSTRAINT `invitecode_ibfk_1` FOREIGN KEY (`user1_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `invitecode_ibfk_2` FOREIGN KEY (`user2_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `invitecode` */

insert  into `invitecode`(`invite_code_id`,`invite_code_content`,`invite_code_state`,`user1_id`,`user2_id`,`invite_code_date`) values (1,'ASDFGHJ',1,1,2,'2021-05-12'),(2,'AAAAAAA',0,1,NULL,'2021-05-03');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `message_user1` int NOT NULL,
  `message_user2` int NOT NULL,
  `message_send_time` date NOT NULL,
  `message_content` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `message_user1` (`message_user1`),
  KEY `message_user2` (`message_user2`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`message_user1`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`message_user2`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `message` */

insert  into `message`(`message_id`,`message_user1`,`message_user2`,`message_send_time`,`message_content`) values (1,1,2,'2021-05-12','瞅我干啥'),(2,2,1,'2021-05-12','瞅你咋地'),(5,4,1,'2021-05-12','hello'),(6,1,2,'2021-05-12','你好');

/*Table structure for table `page` */

DROP TABLE IF EXISTS `page`;

CREATE TABLE `page` (
  `text_id` int NOT NULL,
  `page_floor` int NOT NULL,
  `page_content` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int NOT NULL,
  `page_retime` date NOT NULL,
  PRIMARY KEY (`text_id`,`page_floor`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `page_ibfk_1` FOREIGN KEY (`text_id`) REFERENCES `text` (`text_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `page_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `page` */

insert  into `page`(`text_id`,`page_floor`,`page_content`,`user_id`,`page_retime`) values (1,1,'红火火恍恍惚惚',1,'2021-04-28'),(1,2,'woc nb',1,'2021-05-20'),(1,3,'ffffff',4,'2021-05-19'),(2,1,'一给我里giaogiao',1,'2021-05-19');

/*Table structure for table `text` */

DROP TABLE IF EXISTS `text`;

CREATE TABLE `text` (
  `text_id` int NOT NULL AUTO_INCREMENT,
  `text_title` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` int NOT NULL,
  `text_status` int NOT NULL,
  `text_create_time` date NOT NULL,
  `block_id` int NOT NULL,
  PRIMARY KEY (`text_id`),
  KEY `user_id` (`user_id`),
  KEY `text_ibfk_2` (`block_id`),
  CONSTRAINT `text_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `text_ibfk_2` FOREIGN KEY (`block_id`) REFERENCES `block` (`block_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `text` */

insert  into `text`(`text_id`,`text_title`,`user_id`,`text_status`,`text_create_time`,`block_id`) values (1,'红红火火恍恍惚惚',1,1,'2021-05-12',1),(2,'一给我里giaogiao',4,4,'2021-05-11',1),(6,'我的帖子',4,4,'2021-05-04',1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_password` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_level` int NOT NULL,
  `user_create_time` date NOT NULL,
  `user_image_path` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `user_state` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_password`,`user_level`,`user_create_time`,`user_image_path`,`user_state`) values (1,'111','654321',2,'2021-05-12','kkk',0),(2,'111','654321',1,'2021-05-12','kkk',0),(4,'111','654321',0,'2021-05-12','kkk',1),(5,'111','654321',0,'2021-05-16','kkk',0),(6,'user5','654321',0,'2021-05-16','path',0),(7,'user4','654321',0,'2021-05-16','path',0),(8,'user4','654321',0,'2021-05-19','path',0),(9,'newuser','123456',0,'2021-05-27','path',0);

/*!50106 set global event_scheduler = 1*/;

/* Event structure for event `auto_release_user` */

/*!50106 DROP EVENT IF EXISTS `auto_release_user`*/;

DELIMITER $$

/*!50106 CREATE DEFINER=`root`@`localhost` EVENT `auto_release_user` ON SCHEDULE EVERY 1 DAY STARTS '2021-05-27 18:47:49' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
	    update user set user_state = 0 WHERE user.`user_id` IN 
	    (
		SELECT ban.`user_id` FROM ban GROUP BY ban.`user_id` HAVING MAX(ban.`out_time`) < (SELECT NOW())
		
	    );
	END */$$
DELIMITER ;

/* Procedure structure for procedure `banuser` */

/*!50003 DROP PROCEDURE IF EXISTS  `banuser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `banuser`(IN userId INTEGER,in outTime Date)
BEGIN
	insert into ban(user_id,out_time) values(userId,outTime);
	update user set user.`user_state` = 1 where user.`user_id` = userId;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
