/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50087
Source Host           : 127.0.0.1:3306
Source Database       : money

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2019-05-24 12:45:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `account` varchar(30) default NULL,
  `password` varchar(30) default NULL,
  `age` varchar(30) default NULL,
  `photo` longblob,
  `role` varchar(20) default '1',
  `address` varchar(255) default NULL,
  `tel` varchar(255) default NULL,
  `idk` varchar(255) default NULL,
  `state` varchar(255) default '0',
  `yx` varchar(255) default NULL,
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 31744 kB';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '1', null, null, '0', null, null, null, '0', null);
INSERT INTO `admin` VALUES ('3', '吴帆', '1', '24', '', '1', '安徽省蚌埠市', '13053038996', '340322199511011101', '0', null);
INSERT INTO `admin` VALUES ('8', '李欣', '1', '32', '', '1', '安徽省六安市', '13955435263', '340322198712204212', '0', null);
INSERT INTO `admin` VALUES ('6', '李阳', '1', '35', '', '1', '安徽省蚌埠市', '17756552658', '340322198801201320', '0', null);
INSERT INTO `admin` VALUES ('7', '欧杨', '1', '33', '', '1', '安徽省蚌埠市', '13055235689', '340322199512204212', '0', null);
INSERT INTO `admin` VALUES ('9', '李明', '1', '24', '', '2', '安徽省六安市', '13022356412', '340322199512204212', '0', null);
INSERT INTO `admin` VALUES ('10', '李明依', '1', '25', '', '2', '安徽省黄山市', '13055265462', '340322199512204212', '0', null);
INSERT INTO `admin` VALUES ('11', '刘飞', '1', '35', '', '2', '安徽省合肥市', '15543657689', '340322199512204212', '0', null);

-- ----------------------------
-- Table structure for gonggao
-- ----------------------------
DROP TABLE IF EXISTS `gonggao`;
CREATE TABLE `gonggao` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL COMMENT '标题',
  `content` varchar(255) default NULL COMMENT '内容',
  `fname` varchar(255) default NULL COMMENT '发布人',
  `fdate` varchar(20) default NULL COMMENT '发布时间',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 31744 kB';

-- ----------------------------
-- Records of gonggao
-- ----------------------------
INSERT INTO `gonggao` VALUES ('2', '召开“互联网+”创新创业大', '5月15日下午，皖西学院在教学楼106召开了第五届“互联网+”大学生创新创业大赛启动会。', 'admin', '2019-05-18 10:55:17');
INSERT INTO `gonggao` VALUES ('3', '调研红色文化建设', '5月15日，校党委委员、副校长杨成升率队赴临沂大学调研红色文化建设', 'admin', '2019-05-18 11:13:34');
INSERT INTO `gonggao` VALUES ('4', '召开图书馆用人动员会', '校图书馆用人制度改革工作组在图书馆会议室召开了图书馆部门负责人聘任与员工轮岗工作动员会', 'admin', '2019-05-18 11:14:03');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL auto_increment,
  `dlr` varchar(255) default NULL COMMENT '登录人',
  `dlsj` varchar(255) default NULL COMMENT '登录时间',
  `dlip` varchar(255) default NULL COMMENT '登录ip',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 31744 kB';

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('19', '李明', '2019-05-18 11:27:00', '10.130.56.61');
INSERT INTO `log` VALUES ('18', '吴帆', '2019-05-18 11:23:59', '10.130.56.61');
INSERT INTO `log` VALUES ('17', 'admin', '2019-05-18 11:21:59', '10.130.56.61');
INSERT INTO `log` VALUES ('16', '刘飞', '2019-05-18 11:21:19', '10.130.56.61');
INSERT INTO `log` VALUES ('15', '李明依', '2019-05-18 11:19:36', '10.130.56.61');
INSERT INTO `log` VALUES ('14', '李明', '2019-05-18 11:16:43', '10.130.56.61');
INSERT INTO `log` VALUES ('13', 'admin', '2019-05-18 10:59:47', '10.130.56.61');
INSERT INTO `log` VALUES ('12', 'admin', '2019-05-18 10:57:44', '10.130.56.61');
INSERT INTO `log` VALUES ('11', '吴帆', '2019-05-18 10:56:52', '10.130.56.61');
INSERT INTO `log` VALUES ('20', '吴帆', '2019-05-18 11:29:28', '10.130.56.61');
INSERT INTO `log` VALUES ('21', '欧杨', '2019-05-18 11:30:18', '10.130.56.61');
INSERT INTO `log` VALUES ('22', '李明', '2019-05-18 11:30:56', '10.130.56.61');
INSERT INTO `log` VALUES ('23', 'admin', '2019-05-20 18:49:01', '10.130.56.61');
INSERT INTO `log` VALUES ('24', '李明', '2019-05-20 19:13:26', '10.130.56.61');
INSERT INTO `log` VALUES ('25', 'admin', '2019-05-23 21:53:47', '10.130.56.61');
INSERT INTO `log` VALUES ('26', 'admin', '2019-05-23 22:56:47', '10.130.56.61');
INSERT INTO `log` VALUES ('27', 'admin', '2019-05-24 10:56:39', '10.130.56.61');
INSERT INTO `log` VALUES ('28', '李明', '2019-05-24 10:57:28', '10.130.56.61');
INSERT INTO `log` VALUES ('29', 'admin', '2019-05-24 10:59:29', '10.130.56.61');
INSERT INTO `log` VALUES ('30', '李明', '2019-05-24 11:02:16', '10.130.56.61');
INSERT INTO `log` VALUES ('31', 'admin', '2019-05-24 11:03:07', '10.130.56.61');
INSERT INTO `log` VALUES ('32', 'admin', '2019-05-24 11:04:05', '10.130.56.61');
INSERT INTO `log` VALUES ('33', '李明', '2019-05-24 11:05:15', '10.130.56.61');
INSERT INTO `log` VALUES ('34', 'admin', '2019-05-24 11:06:16', '10.130.56.61');
INSERT INTO `log` VALUES ('35', 'admin', '2019-05-24 11:11:21', '10.130.56.61');
INSERT INTO `log` VALUES ('36', '李明', '2019-05-24 11:11:36', '10.130.56.61');
INSERT INTO `log` VALUES ('37', 'admin', '2019-05-24 11:11:56', '10.130.56.61');
INSERT INTO `log` VALUES ('38', '李明', '2019-05-24 11:13:40', '10.130.56.61');
INSERT INTO `log` VALUES ('39', 'admin', '2019-05-24 11:14:20', '10.130.56.61');
INSERT INTO `log` VALUES ('40', '李明依', '2019-05-24 11:15:07', '10.130.56.61');
INSERT INTO `log` VALUES ('41', 'admin', '2019-05-24 11:15:31', '10.130.56.61');

-- ----------------------------
-- Table structure for lxr
-- ----------------------------
DROP TABLE IF EXISTS `lxr`;
CREATE TABLE `lxr` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '联系人姓名',
  `tel` varchar(255) default NULL COMMENT '电话',
  `content` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 31744 kB';

-- ----------------------------
-- Records of lxr
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL COMMENT '标题',
  `content` varchar(255) default NULL COMMENT '内容',
  `fname` varchar(255) default NULL COMMENT '发布人',
  `fdate` varchar(20) default NULL COMMENT '发布时间',
  `lx` varchar(255) default NULL COMMENT '消息类型',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 31744 kB';

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '系统错误', '近期由于网络问题导致系统错误问题已经排查解决，请大家放心使用。', 'admin', '2019-05-18 11:05:03', '简明消息');
INSERT INTO `message` VALUES ('3', '项目评审结果', '现将我校2018年第二学期高层次人才科研启动经费资助项目评审结果予以公示', 'admin', '2019-05-18 11:10:11', '长消息');
INSERT INTO `message` VALUES ('4', '素质拓展活动招募', '活动主题：团队建设\r\n\r\n活动形式：户外素质拓展\r\n\r\n招募对象：渴望加强团队意识的同学', 'admin', '2019-05-18 11:10:54', '短消息');

-- ----------------------------
-- Table structure for order1
-- ----------------------------
DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1` (
  `id` int(11) NOT NULL auto_increment,
  `kid` int(11) default NULL COMMENT '客户id',
  `aid` int(11) default NULL COMMENT '员工id',
  `ddrq` varchar(255) default NULL COMMENT '订单日期',
  `remarks` varchar(255) default NULL COMMENT '备注',
  `state` varchar(255) default NULL,
  `money` varchar(255) default NULL,
  `xm` varchar(255) default NULL,
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 31744 kB';

-- ----------------------------
-- Records of order1
-- ----------------------------
INSERT INTO `order1` VALUES ('7', null, '9', '2019-05-13', '会议', '300', '1000', '课题研究经费管理系统的开发');
INSERT INTO `order1` VALUES ('3', null, '10', '2019-01-02', '', '3300', '3300', '关于材料的进一步研究');
INSERT INTO `order1` VALUES ('4', null, '11', '2017-05-23', '', '2000', '5000', '关于体育项目的研究');
INSERT INTO `order1` VALUES ('5', null, '9', '2019-05-22', '器材购买', '500', '1000', '课题研究经费管理系统的开发');
INSERT INTO `order1` VALUES ('8', null, '9', '2019-05-29', '课题研究经费系统的开发', '200', '200', '会议');
INSERT INTO `order1` VALUES ('9', null, '10', '2019-05-15', '', '300', '300', '会议');

-- ----------------------------
-- Table structure for qtgs
-- ----------------------------
DROP TABLE IF EXISTS `qtgs`;
CREATE TABLE `qtgs` (
  `id` int(11) NOT NULL auto_increment,
  `gsmc` varchar(255) default NULL,
  `cjsl` varchar(255) default NULL,
  `cjje` varchar(255) default NULL,
  `lname` varchar(255) default NULL COMMENT '录入人',
  `lrsj` varchar(255) default NULL COMMENT '录入时间',
  PRIMARY KEY  USING BTREE (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 31744 kB';

-- ----------------------------
-- Records of qtgs
-- ----------------------------
INSERT INTO `qtgs` VALUES ('1', '课题研究经费管理系统的开发', '10000', '刘飞', '吴帆', '2019-05-18 11:25:42');
INSERT INTO `qtgs` VALUES ('2', '关于报告的进一步研究', '4000', '', '吴帆', '2019-05-18 11:30:02');
INSERT INTO `qtgs` VALUES ('3', '有关材料的深层研究', '8000', '', '欧杨', '2019-05-18 11:30:46');
