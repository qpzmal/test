-- 建表语句

-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.23 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 fcf_vu 的数据库结构
CREATE DATABASE IF NOT EXISTS `fcf_vu` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fcf_vu`;


-- 导出  表 fcf_vu.base_adtype 结构
CREATE TABLE IF NOT EXISTS `base_adtype` (
  `id` int(11) unsigned NOT NULL COMMENT '广告类型编码',
  `name` varchar(30) NOT NULL COMMENT '广告类型名字',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL DEFAULT '无' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告类型';

-- 正在导出表  fcf_vu.base_adtype 的数据：~0 rows (大约)
DELETE FROM `base_adtype`;
/*!40000 ALTER TABLE `base_adtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_adtype` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_area 结构
CREATE TABLE IF NOT EXISTS `base_area` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '公司名字',
  `parent_id` int(11) NOT NULL COMMENT '父级公司',
  `code` varchar(10) NOT NULL COMMENT '公司编号,用于生成项目号',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '无' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域（公司）信息';

-- 正在导出表  fcf_vu.base_area 的数据：~0 rows (大约)
DELETE FROM `base_area`;
/*!40000 ALTER TABLE `base_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_area` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_buy_order 结构
CREATE TABLE IF NOT EXISTS `base_buy_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `order_num` varchar(15) NOT NULL COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL COMMENT '项目补充编号',
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `type` tinyint(3) unsigned NOT NULL COMMENT '项目类型：1，框架 2,单采（下拉）',
  `order_date` datetime NOT NULL COMMENT '项目日期',
  `amount` decimal(18,2) NOT NULL COMMENT '项目总金额',
  `sign_company_name` varchar(50) NOT NULL COMMENT '签约公司名称',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `start_date` datetime NOT NULL COMMENT '执行开始日期',
  `end_date` datetime NOT NULL COMMENT '执行结束日期',
  `person_id` int(11) NOT NULL COMMENT '项目负责人ID',
  `launch_media` tinyint(3) NOT NULL COMMENT '投放媒体:1,智能电视2,应用3,户外4,内容商',
  `status` tinyint(3) DEFAULT '1' COMMENT '执行状态：1，未开始； 2，进行中；3，已结束',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '付款比例',
  `user_id` int(11) NOT NULL,
  `area_id` int(11) NOT NULL COMMENT '所属区域Id',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒介采购合同';

-- 正在导出表  fcf_vu.base_buy_order 的数据：~0 rows (大约)
DELETE FROM `base_buy_order`;
/*!40000 ALTER TABLE `base_buy_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_buy_order` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_dept 结构
CREATE TABLE IF NOT EXISTS `base_dept` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门Id',
  `name` varchar(100) NOT NULL COMMENT '部门名字',
  `parent_id` int(11) NOT NULL COMMENT '上级部门',
  `sort` int(10) NOT NULL COMMENT '排序（升序）',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `area_id` int(11) NOT NULL COMMENT '所属区域Id',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_30` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门架构';

-- 正在导出表  fcf_vu.base_dept 的数据：~0 rows (大约)
DELETE FROM `base_dept`;
/*!40000 ALTER TABLE `base_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_dept` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_execute_order 结构
CREATE TABLE IF NOT EXISTS `base_execute_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `order_num` varchar(15) NOT NULL COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL COMMENT '项目补充编号',
  `type` tinyint(1) NOT NULL COMMENT '项目类型：1，框架 2,单采（下拉）',
  `order_date` datetime NOT NULL COMMENT '项目日期',
  `delivery_area_ids` varchar(255) NOT NULL COMMENT '投放地区编号，多个地区用逗号隔开',
  `delivery_area_names` varchar(255) NOT NULL COMMENT '投放地区名称，多个地区用逗号隔开',
  `area_id` int(11) NOT NULL COMMENT '公司区域编号，连接vu_Area表（如北京公司）',
  `person_sales_id` int(11) NOT NULL COMMENT '销售负责人ID',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '项目总金额',
  `public_rebate` decimal(5,4) NOT NULL COMMENT '对公返点',
  `private_rebate` decimal(5,4) NOT NULL COMMENT '对私返点',
  `tax_amount` decimal(18,2) DEFAULT '0.00' COMMENT '含税金额',
  `tax_free_amount` decimal(18,0) DEFAULT '0' COMMENT '不含税金额',
  `sign_type` tinyint(3) NOT NULL COMMENT '签约类型 1,4A公司 2,直客（下拉）',
  `sign_company_name` varchar(50) NOT NULL COMMENT '签约公司名称',
  `custom_industry_id` tinyint(3) NOT NULL COMMENT '客户行业 :1,汽车2,化妆品3,快消4,金融5,旅游6,教育7,游戏8,生活9,IT（下拉）',
  `custom_name` varchar(50) NOT NULL COMMENT '客户名称',
  `monitor_ids` varchar(50) NOT NULL COMMENT '客户要求监测编号:1,秒针2,Admaster3,记刻（下拉菜单，可多选，多选用逗号隔开)',
  `monitor_names` varchar(100) NOT NULL COMMENT '客户要求监测名称',
  `start_date` datetime NOT NULL COMMENT '投放开始日期',
  `end_date` datetime NOT NULL COMMENT '投放结束日期',
  `person_leader_id` int(11) NOT NULL COMMENT '项目负责人ID',
  `our_monitor_name` varchar(50) NOT NULL COMMENT '我方监测',
  `report_type_id` varchar(50) NOT NULL COMMENT '报告类型Id',
  `status` tinyint(3) DEFAULT '1' COMMENT '执行状态：1，未开始； 2，进行中；3，已结束',
  `person_payee_id` int(11) NOT NULL COMMENT '收款负责人',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '回款比例',
  `user_id` int(11) NOT NULL COMMENT '登录用户id',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户需求单';

-- 正在导出表  fcf_vu.base_execute_order 的数据：~0 rows (大约)
DELETE FROM `base_execute_order`;
/*!40000 ALTER TABLE `base_execute_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_execute_order` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_industry 结构
CREATE TABLE IF NOT EXISTS `base_industry` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '行业id',
  `name` varchar(255) NOT NULL COMMENT '行业名称',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行业管理表';

-- 正在导出表  fcf_vu.base_industry 的数据：~0 rows (大约)
DELETE FROM `base_industry`;
/*!40000 ALTER TABLE `base_industry` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_industry` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_monitor_request 结构
CREATE TABLE IF NOT EXISTS `base_monitor_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '检测公司名字 ：1，秒针2，Admaster3，记刻',
  `link` varchar(300) NOT NULL COMMENT '检测链接',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='客户要求监测';

-- 正在导出表  fcf_vu.base_monitor_request 的数据：~1 rows (大约)
DELETE FROM `base_monitor_request`;
/*!40000 ALTER TABLE `base_monitor_request` DISABLE KEYS */;
INSERT INTO `base_monitor_request` (`id`, `name`, `link`, `item_status`, `create_time`, `update_time`, `remark`) VALUES
	(1, '秒针', 'http://www.baidu.com', 0, '2017-06-25 18:15:35', '2017-06-25 18:15:35', '我是备注，哈哈哈哈哈');
/*!40000 ALTER TABLE `base_monitor_request` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_person 结构
CREATE TABLE IF NOT EXISTS `base_person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '公司人员Id',
  `name` varchar(10) NOT NULL COMMENT '人名',
  `area_id` int(11) NOT NULL COMMENT '所属区域（公司）Id',
  `mobile` varchar(30) NOT NULL COMMENT '手机',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `email` varchar(60) NOT NULL COMMENT '邮箱',
  `dept_id` int(11) NOT NULL COMMENT '部门id',
  `state` int(11) DEFAULT '0' COMMENT '状态 0，正常 1，停用',
  `parent_id` int(11) NOT NULL COMMENT '上级领导',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司人员表';

-- 正在导出表  fcf_vu.base_person 的数据：~0 rows (大约)
DELETE FROM `base_person`;
/*!40000 ALTER TABLE `base_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_person` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_region 结构
CREATE TABLE IF NOT EXISTS `base_region` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '地区Id',
  `name` varchar(128) NOT NULL COMMENT '地区名称',
  `type` int(1) NOT NULL COMMENT '地区类型\n            1=省份\n            2=市',
  `parent_id` int(11) NOT NULL COMMENT '上级地区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区管理';

-- 正在导出表  fcf_vu.base_region 的数据：~0 rows (大约)
DELETE FROM `base_region`;
/*!40000 ALTER TABLE `base_region` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_region` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_reporttype 结构
CREATE TABLE IF NOT EXISTS `base_reporttype` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '报告类型名字：1，日报2，周报3，月报4，年报5，结案报告',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告';

-- 正在导出表  fcf_vu.base_reporttype 的数据：~0 rows (大约)
DELETE FROM `base_reporttype`;
/*!40000 ALTER TABLE `base_reporttype` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_reporttype` ENABLE KEYS */;


-- 导出  表 fcf_vu.base_tv 结构
CREATE TABLE IF NOT EXISTS `base_tv` (
  `id` int(11) unsigned NOT NULL COMMENT '厂商编码',
  `name` varchar(30) NOT NULL COMMENT '厂家名字',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='厂商管理';

-- 正在导出表  fcf_vu.base_tv 的数据：~0 rows (大约)
DELETE FROM `base_tv`;
/*!40000 ALTER TABLE `base_tv` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_tv` ENABLE KEYS */;


-- 导出  表 fcf_vu.oa_notify 结构
CREATE TABLE IF NOT EXISTS `oa_notify` (
  `id` int(11) unsigned NOT NULL COMMENT '编号',
  `type` int(1) NOT NULL COMMENT '类型  1: 短信 2：邮件 3：微信',
  `title` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `content` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '内容',
  `files` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '附件（只适用于邮件）',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`item_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知内容管理';

-- 正在导出表  fcf_vu.oa_notify 的数据：~0 rows (大约)
DELETE FROM `oa_notify`;
/*!40000 ALTER TABLE `oa_notify` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_notify` ENABLE KEYS */;


-- 导出  表 fcf_vu.oa_notify_record 结构
CREATE TABLE IF NOT EXISTS `oa_notify_record` (
  `id` int(11) unsigned NOT NULL COMMENT '编号',
  `notify_id` int(11) NOT NULL COMMENT '通知通告ID',
  `user_ids` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '接受人Id,多个人可以用逗号隔开',
  `user_names` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '接受人名字,多个人可以用逗号隔开',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `oa_notify_record_notify_id` (`notify_id`),
  KEY `oa_notify_record_user_id` (`user_ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知发送记录';

-- 正在导出表  fcf_vu.oa_notify_record 的数据：~0 rows (大约)
DELETE FROM `oa_notify_record`;
/*!40000 ALTER TABLE `oa_notify_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_notify_record` ENABLE KEYS */;


-- 导出  表 fcf_vu.sys_dict 结构
CREATE TABLE IF NOT EXISTS `sys_dict` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `value` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '数据值',
  `label` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `description` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`item_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- 正在导出表  fcf_vu.sys_dict 的数据：~0 rows (大约)
DELETE FROM `sys_dict`;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;


-- 导出  表 fcf_vu.sys_log 结构
CREATE TABLE IF NOT EXISTS `sys_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `content` longtext COMMENT '变更内容，包含更新前和更新后的内容',
  `ip` varchar(255) NOT NULL COMMENT 'ip地址',
  `operation` varchar(255) NOT NULL COMMENT '操作URL',
  `operator` varchar(255) NOT NULL COMMENT '操作者',
  `parameter` longtext COMMENT '参数',
  `type` int(11) NOT NULL COMMENT '1.save,2.update,3delete',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  fcf_vu.sys_log 的数据：~0 rows (大约)
DELETE FROM `sys_log`;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;


-- 导出  表 fcf_vu.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `parent_id` int(11) NOT NULL COMMENT 'parent_id',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `url` varchar(512) NOT NULL COMMENT 'url',
  `type` int(11) NOT NULL COMMENT '1.菜单资源\\r\\n            2.普通资源',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `sort` int(11) NOT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_menu_parent` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单资源表';

-- 正在导出表  fcf_vu.sys_menu 的数据：~0 rows (大约)
DELETE FROM `sys_menu`;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- 导出  表 fcf_vu.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `is_system` bit(1) NOT NULL COMMENT '是否系统内置',
  `sort` int(11) NOT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';

-- 正在导出表  fcf_vu.sys_role 的数据：~0 rows (大约)
DELETE FROM `sys_role`;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 fcf_vu.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '资源',
  KEY `index_rolemenu_role` (`role_id`) USING BTREE,
  KEY `index_rolemenu_menu` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';

-- 正在导出表  fcf_vu.sys_role_menu 的数据：~0 rows (大约)
DELETE FROM `sys_role_menu`;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;


-- 导出  表 fcf_vu.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `mobile` varchar(255) NOT NULL COMMENT '手机',
  `phone` varchar(255) NOT NULL COMMENT '联系电话',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `dept` varchar(255) NOT NULL COMMENT '部门名称',
  `lockedDate` datetime NOT NULL COMMENT '锁定时间',
  `login_fail_count` int(11) NOT NULL COMMENT '登录失败次数',
  `sort` int(11) NOT NULL COMMENT '排序',
  `dept_id` int(10) NOT NULL COMMENT '部门id',
  `last_login_time` bigint(20) NOT NULL COMMENT '最后登录时间',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用；9删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_admin_loginname` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- 正在导出表  fcf_vu.sys_user 的数据：~0 rows (大约)
DELETE FROM `sys_user`;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


-- 导出  表 fcf_vu.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `admins` int(11) NOT NULL COMMENT '用户id',
  `roles` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`admins`,`roles`),
  KEY `index_adminrole_role` (`roles`) USING BTREE,
  KEY `index_adminrole_admin` (`admins`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- 正在导出表  fcf_vu.sys_user_role 的数据：~0 rows (大约)
DELETE FROM `sys_user_role`;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
