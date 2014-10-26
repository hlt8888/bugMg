/*
MySQL - 5.6.13-enterprise-commercial-advanced : Database - bugmanage
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bugmanage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bugmanage`;

/*Table structure for table `m_bug` */

DROP TABLE IF EXISTS `m_bug`;

CREATE TABLE `m_bug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  `m_user_id` int(11) DEFAULT NULL COMMENT 'bug指定处理人',
  `createddate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) DEFAULT NULL COMMENT '创建人',
  `status` tinyint(4) DEFAULT '1' COMMENT '处理状态：1、新建；2、正在处理；3、处理完毕；3、无法处理；4、错误的bug提交；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `m_message` */

DROP TABLE IF EXISTS `m_message`;

CREATE TABLE `m_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `m_bug_id` int(11) DEFAULT NULL,
  `m_user_id` int(11) DEFAULT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `m_user` */

DROP TABLE IF EXISTS `m_user`;

CREATE TABLE `m_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL COMMENT '姓名',
  `email` varchar(55) DEFAULT NULL COMMENT 'email邮件',
  `pwd` varchar(55) DEFAULT NULL COMMENT '密码',
  `createddate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
