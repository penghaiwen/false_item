/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.17-log : Database - pro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pro`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(66) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `city` */

insert  into `city`(`id`,`name`) values 
(1,'江西');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '上级机构ID，一级机构为0',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='机构管理';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`id`,`name`,`parent_id`,`order_num`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_flag`) values 
(1,'海科技',0,1,1,'2020-05-08 14:19:15',1,'2020-05-08 15:21:43',0),
(2,'无间道',0,1,NULL,NULL,NULL,NULL,0),
(3,'阿里云',0,1,NULL,'2020-05-08 15:03:33',NULL,NULL,0),
(4,'测',0,1,NULL,'2020-05-08 15:06:24',NULL,NULL,0),
(5,'测222',0,1,NULL,'2020-05-08 15:07:20',NULL,NULL,0),
(6,'新增5',0,1,1,'2020-05-08 15:13:02',1,'2020-05-08 15:29:01',0),
(7,'测1',0,1,1,'2020-05-08 15:27:02',NULL,NULL,0),
(8,'测2',0,1,1,'2020-05-08 15:27:05',NULL,NULL,0),
(9,'测3',0,1,1,'2020-05-08 15:27:07',NULL,NULL,0),
(10,'测4',0,1,1,'2020-05-08 15:27:11',NULL,NULL,0),
(11,'测5',0,1,1,'2020-05-08 15:27:14',NULL,NULL,0),
(12,'测试一下',0,1,1,'2020-05-09 16:53:55',NULL,NULL,0);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `menu_name` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `menu_title` varchar(32) NOT NULL DEFAULT '' COMMENT '菜单标题',
  `menu_remark` varchar(50) DEFAULT NULL COMMENT '菜单备注',
  `perms_code` varchar(64) DEFAULT '' COMMENT '权限字段',
  `menu_url` varchar(150) NOT NULL DEFAULT '' COMMENT '请求链接',
  `keep_alive` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0-false 1-true',
  `menu_path` varchar(150) NOT NULL DEFAULT '' COMMENT '前端URL',
  `menu_icon` varchar(32) NOT NULL DEFAULT '' COMMENT '图标',
  `menu_sort` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序字段',
  `menu_level` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '菜单级别 1-9级',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态 0隐藏 1显示',
  `delete_flag` tinyint(1) unsigned DEFAULT '0' COMMENT '删除标志： 0-正常 -1-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`menu_name`,`menu_title`,`menu_remark`,`perms_code`,`menu_url`,`keep_alive`,`menu_path`,`menu_icon`,`menu_sort`,`menu_level`,`is_show`,`delete_flag`) values 
(2,0,'sys','系统管理','','','',1,'','',1,1,1,0),
(3,2,'menu','菜单管理',NULL,'','system/menu',1,'system/menu/index','',1,2,1,0),
(4,0,'from','表单管理',NULL,'','',0,'','',1,1,1,0),
(5,4,'custom','自定义表单',NULL,'','form/BaseForm',0,'form/BaseForm','',1,2,1,0),
(6,0,'err','错误管理',NULL,'','',0,'','',1,1,1,1),
(7,0,'user','用户管理',NULL,'','',0,'','',1,1,1,0),
(8,7,'dept','机构管理',NULL,'','user/dept',0,'user/dept','',1,2,1,0),
(9,7,'role','角色管理',NULL,'','user/role',0,'user/role','',2,2,1,0),
(10,7,'users','用户管理',NULL,'','user/users',1,'user/users','',3,2,1,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`remark`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_flag`) values 
(1,'管理员',NULL,'1','2020-05-08 17:15:29','1','2020-05-09 17:44:01',0),
(3,'运营',NULL,'1','2020-05-09 10:27:48','2','2020-05-09 17:44:57',0);

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与机构对应关系';

/*Data for the table `sys_role_dept` */

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values 
(3,1,2),
(4,1,3),
(5,1,4),
(6,1,5),
(7,1,7),
(8,1,8),
(9,1,9),
(10,1,10),
(17,3,7),
(18,3,8),
(19,3,9),
(20,3,10);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '用户姓名',
  `account` varchar(50) NOT NULL COMMENT '用户名（账号）',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`nick_name`,`account`,`password`,`user_type`,`email`,`mobile`,`status`,`dept_id`,`create_by`,`create_time`,`update_by`,`update_time`,`delete_flag`) values 
(1,'老默','admin','$2a$10$a8Ln4GFeu196X85X5NWd6e65RnJ1oupHk0JzjYIFo7V3KUQF/UGxq',0,'1515434159@qq.com','18675643817',1,2,1,'2020-04-30 11:32:41',1,'2020-05-09 16:08:47',0),
(2,'老默3号','18675643817','$2a$10$BnN25PTbGz9FRC6nZONi7u8.k07P1q07ZoBsZhI9JQX7cpXA6ctmq',1,'','18124006817',1,1,1,'2020-05-09 14:22:12',1,'2020-05-09 17:43:22',0),
(3,'老默三号','123456','$2a$10$QOEOAPw1xLRdg0h./8IEUOd2rwMx2rIL/7UmyeMmOw3wqTHFFICky',0,'','18675643817',NULL,1,1,'2020-05-09 14:23:19',NULL,NULL,1),
(4,'测试','789456','$2a$10$HayyhdhR2waJ0QJPIe/FCOkefL43o5uGTLLjLpxl14QaZViPBpTte',0,'','',1,1,1,'2020-05-09 15:40:26',NULL,NULL,1),
(6,'测试员','1234567','$2a$10$xCd7Qgs6jc8JjhF2v8Q6s.85F5VMyedtjyAAZMk9Bu57vDMIQudEm',1,'','18124006817',1,12,1,'2020-05-09 16:54:38',1,'2020-05-09 17:43:28',0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values 
(4,3,3),
(5,4,3),
(6,1,3),
(7,1,1),
(18,2,3),
(19,6,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
