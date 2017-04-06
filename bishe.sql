/*
Navicat MySQL Data Transfer

Source Server         : 20161111
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : bishe

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-04-06 21:30:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类';

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `desc` text COMMENT '商品描述',
  `image` varchar(255) DEFAULT NULL,
  `uid` int(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0上架1下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of item
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `mobile` int(250) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0未激活，1已激活',
  `createtime` date DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL COMMENT '激活码 激活后为空',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1115 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1111', '11', '790827705@qq.com', '123', '123', '1', null, null);
INSERT INTO `user` VALUES ('1112', 'aaa', 'aaa@shop.com', '4545', '123', null, '2017-02-01', null);
INSERT INTO `user` VALUES ('1113', '4564', '55@qq.com', '123', '123', null, '2017-02-01', null);
INSERT INTO `user` VALUES ('1114', 'test ', 'test', '123', '123456', '1', null, null);
