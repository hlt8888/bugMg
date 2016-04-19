/*
 Navicat Premium Data Transfer

 Source Server         : aaa
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : localhost
 Source Database       : bugmanage

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : utf-8

 Date: 04/19/2016 11:10:19 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `m_bug`
-- ----------------------------
DROP TABLE IF EXISTS `m_bug`;
CREATE TABLE `m_bug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  `m_user_id` int(11) DEFAULT NULL COMMENT 'bug指定处理人',
  `createddate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) DEFAULT NULL COMMENT '创建人',
  `status` tinyint(4) DEFAULT '1' COMMENT '处理状态：1、新建；2、正在处理；3、处理完毕；3、无法处理；4、错误的bug提交；',
  `action` int(11) DEFAULT NULL,
  `repair` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_message`
-- ----------------------------
DROP TABLE IF EXISTS `m_message`;
CREATE TABLE `m_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `m_bug_id` int(11) DEFAULT NULL,
  `m_user_id` int(11) DEFAULT NULL,
  `message` varchar(4000) DEFAULT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_user`
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL COMMENT '姓名',
  `email` varchar(55) DEFAULT NULL COMMENT 'email邮件',
  `pwd` varchar(55) DEFAULT NULL COMMENT '密码',
  `createddate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
