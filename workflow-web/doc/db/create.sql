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
DROP TABLE IF EXISTS `base_adtype`;
CREATE TABLE IF NOT EXISTS `base_adtype` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '广告类型编码',
  `name` varchar(30) NOT NULL COMMENT '广告类型名字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) DEFAULT '无' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告形式类型';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_area 结构
DROP TABLE IF EXISTS `base_area`;
CREATE TABLE IF NOT EXISTS `base_area` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '' COMMENT '公司名字',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级公司',
  `level` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '公司层级',
  `code` varchar(10) NOT NULL DEFAULT '' COMMENT '公司编号,用于生成项目号',
  `room_pay` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '房租成本',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '无' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域（公司）信息';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_area_finance 结构
DROP TABLE IF EXISTS `base_area_finance`;
CREATE TABLE IF NOT EXISTS `base_area_finance` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '' COMMENT '公司名字',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '区域（公司）id，关联base_area表',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务结算的开始日期',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务结算的结束日期',
  `room_pay` decimal(18,2) DEFAULT '0.00' COMMENT '房租成本',
  `person_pay` decimal(18,2) DEFAULT '0.00' COMMENT '人员成本',
  `other_pay` decimal(18,2) DEFAULT '0.00' COMMENT '其他综合分摊费用',
  `cost_buy` decimal(18,2) DEFAULT '0.00' COMMENT '成本',
  `income` decimal(18,2) DEFAULT '0.00' COMMENT '净收入（订单额减去返点）',
  `tax` decimal(18,2) DEFAULT '0.00' COMMENT '税费',
  `sales_commission` decimal(18,2) DEFAULT '0.00' COMMENT '销售提成',
  `pay1` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `pay3` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `pay2` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '无' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域（公司）财务信息';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_buy_order 结构
DROP TABLE IF EXISTS `base_buy_order`;
CREATE TABLE IF NOT EXISTS `base_buy_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `process_instance_id` varchar(64) NOT NULL DEFAULT '' COMMENT '流程ID',
  `order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目补充编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '项目类型：1，框架 2,单采（下拉）',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目日期',
  `amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '项目总金额',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行开始日期',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行结束日期',
  `person_id` int(11) DEFAULT '0' COMMENT '项目负责人ID',
  `status` tinyint(3) DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '付款比例',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属区域Id',
  `wf_step` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '工作流步骤数',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `delivery_area_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区编号，多个地区用逗号隔开',
  `delivery_area_names` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区名称，多个地区用逗号隔开',
  `frame_id` int(11) DEFAULT '0' COMMENT '框架协议id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒介采购合同';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_buy_order_frame 结构
DROP TABLE IF EXISTS `base_buy_order_frame`;
CREATE TABLE IF NOT EXISTS `base_buy_order_frame` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `process_instance_id` varchar(64) NOT NULL DEFAULT '' COMMENT '流程ID',
  `order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目补充编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单日期',
  `amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '项目总金额',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行开始日期',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行结束日期',
  `person_id` int(11) DEFAULT '0' COMMENT '项目负责人ID',
  `status` tinyint(3) DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束',
  `delivery_area_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区编号，多个地区用逗号隔开',
  `delivery_area_names` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区名称，多个地区用逗号隔开',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '付款比例',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属区域Id',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒介采购合同';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_custom 结构
DROP TABLE IF EXISTS `base_custom`;
CREATE TABLE IF NOT EXISTS `base_custom` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '客户名称(签约公司名称)',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属上一级id',
  `custom_type` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '类型 1,4A公司 2,直客 3,广告主（下拉）',
  `contacts` varchar(30) NOT NULL DEFAULT '' COMMENT '联系人',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `email` varchar(60) NOT NULL DEFAULT '' COMMENT '地址',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_custom_finance 结构
DROP TABLE IF EXISTS `base_custom_finance`;
CREATE TABLE IF NOT EXISTS `base_custom_finance` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '' COMMENT '公司名字',
  `custom_id` int(11) NOT NULL DEFAULT '0' COMMENT '客户id，关联base_custom表',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务结算的开始日期',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '财务结算的结束日期',
  `room_pay` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '房租成本',
  `person_pay` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '人员成本',
  `other_pay` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '其他综合分摊费用',
  `cost_buy` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '分摊成本（加权平均计算）',
  `income` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '净收入（订单额-对公返点）',
  `tax` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '税费',
  `sales_commission` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '销售提成',
  `pay1` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `pay2` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `pay3` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '无' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户财务信息';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_dept 结构
DROP TABLE IF EXISTS `base_dept`;
CREATE TABLE IF NOT EXISTS `base_dept` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门Id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '部门名字',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '层次',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级部门',
  `sort` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `description` varchar(100) NOT NULL DEFAULT '' COMMENT '描述',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属区域Id',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_30` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门架构';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_execute_order 结构
DROP TABLE IF EXISTS `base_execute_order`;
CREATE TABLE IF NOT EXISTS `base_execute_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `process_instance_id` varchar(64) NOT NULL DEFAULT '' COMMENT '流程ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目补充编号',
  `type` tinyint(1) NOT NULL DEFAULT '2' COMMENT '项目类型：1，框架 2,单采（下拉）',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目日期',
  `delivery_area_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区编号，多个地区用逗号隔开',
  `delivery_area_names` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区名称，多个地区用逗号隔开',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '公司区域编号，连接vu_Area表（如北京公司）',
  `custom_sign_id` int(11) NOT NULL DEFAULT '0' COMMENT '签约公司id，关联客户表',
  `custom_adverser_id` int(11) NOT NULL DEFAULT '0' COMMENT '广告主id，关联custom表',
  `industry_id` int(11) NOT NULL DEFAULT '0' COMMENT '行业id，关连行业表',
  `person_sales_id` int(11) NOT NULL DEFAULT '0' COMMENT '销售负责人ID',
  `status` tinyint(3) DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '项目总金额',
  `public_rebate` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '对公返点',
  `private_rebate` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '对私返点',
  `tax_amount` decimal(18,2) DEFAULT '0.00' COMMENT '含税金额',
  `tax_free_amount` decimal(18,2) DEFAULT '0.00' COMMENT '不含税金额',
  `monitor_ids` varchar(50) NOT NULL DEFAULT '1' COMMENT '客户要求监测编号:1,秒针2,Admaster3,记刻（下拉菜单，可多选，多选用逗号隔开)',
  `monitor_names` varchar(100) NOT NULL DEFAULT '' COMMENT '客户要求监测名称',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '投放开始日期',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '投放结束日期',
  `person_leader_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目负责人ID',
  `our_monitor_name` varchar(50) NOT NULL DEFAULT '' COMMENT '我方监测',
  `report_type_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '报告类型Id',
  `signing_intention` varchar(30) DEFAULT NULL COMMENT '签约意向',
  `person_payee_id` int(11) NOT NULL DEFAULT '0' COMMENT '收款负责人',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '回款比例',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '登录用户id',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `frame_id` int(11) DEFAULT NULL COMMENT '框架协议id',
  `sign_type` tinyint(1) NOT NULL COMMENT '签约类型：1：代理2：直客',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户需求单';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_execute_order_frame 结构
DROP TABLE IF EXISTS `base_execute_order_frame`;
CREATE TABLE IF NOT EXISTS `base_execute_order_frame` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `process_instance_id` varchar(64) NOT NULL DEFAULT '' COMMENT '流程ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目补充编号',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目日期',
  `delivery_area_ids` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区编号，多个地区用逗号隔开',
  `delivery_area_names` varchar(255) NOT NULL DEFAULT '' COMMENT '投放地区名称，多个地区用逗号隔开',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '公司区域编号，连接vu_Area表（如北京公司）',
  `custom_sign_id` int(11) NOT NULL DEFAULT '0' COMMENT '签约公司id，关联客户表',
  `custom_adverser_id` int(11) NOT NULL DEFAULT '0' COMMENT '广告主id，关联custom表',
  `industry_id` int(11) NOT NULL DEFAULT '0' COMMENT '行业id，关连行业表',
  `person_sales_id` int(11) NOT NULL DEFAULT '0' COMMENT '销售负责人ID',
  `status` tinyint(3) DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '项目总金额',
  `public_rebate` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '对公返点',
  `private_rebate` decimal(5,4) NOT NULL DEFAULT '0.0000' COMMENT '对私返点',
  `tax_amount` decimal(18,2) DEFAULT '0.00' COMMENT '含税金额',
  `tax_free_amount` decimal(18,2) DEFAULT '0.00' COMMENT '不含税金额',
  `monitor_ids` varchar(50) NOT NULL DEFAULT '1' COMMENT '客户要求监测编号:1,秒针2,Admaster3,记刻（下拉菜单，可多选，多选用逗号隔开)',
  `monitor_names` varchar(100) NOT NULL DEFAULT '' COMMENT '客户要求监测名称',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '投放开始日期',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '投放结束日期',
  `person_leader_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目负责人ID',
  `our_monitor_name` varchar(50) NOT NULL DEFAULT '' COMMENT '我方监测',
  `report_type_id` varchar(50) NOT NULL DEFAULT '' COMMENT '报告类型Id',
  `signing_intention` varchar(30) DEFAULT '' COMMENT '签约意向',
  `person_payee_id` int(11) NOT NULL DEFAULT '0' COMMENT '收款负责人',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '回款比例',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '登录用户id',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `frame_id` int(11) DEFAULT '0' COMMENT '框架协议id',
  `sign_type` tinyint(1) NOT NULL COMMENT '签约类型：1：代理2：直客',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='框架协议';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_financialindex 结构
DROP TABLE IF EXISTS `base_financialindex`;
CREATE TABLE IF NOT EXISTS `base_financialindex` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `number` varchar(64) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '标签名',
  `value` varchar(30) NOT NULL DEFAULT '' COMMENT '数据值',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务指标管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_industry 结构
DROP TABLE IF EXISTS `base_industry`;
CREATE TABLE IF NOT EXISTS `base_industry` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '行业id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '行业名称',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行业管理表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_media 结构
DROP TABLE IF EXISTS `base_media`;
CREATE TABLE IF NOT EXISTS `base_media` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '媒体编码（对应广告曝光的厂商编码）',
  `type` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '媒体类型 与base_media_type关联',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '媒体名字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_media_type 结构
DROP TABLE IF EXISTS `base_media_type`;
CREATE TABLE IF NOT EXISTS `base_media_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '媒体类型名字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体类型管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_monitor 结构
DROP TABLE IF EXISTS `base_monitor`;
CREATE TABLE IF NOT EXISTS `base_monitor` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '检测公司名字 ：1，秒针2，Admaster3，记刻',
  `link` varchar(300) DEFAULT '' COMMENT '检测链接',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户要求监测';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_order_cpm 结构
DROP TABLE IF EXISTS `base_order_cpm`;
CREATE TABLE IF NOT EXISTS `base_order_cpm` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '订单id',
  `order_cpm_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型：1，客户需求CPM 2,执行排期CPM 3，第三方检测CPM 4，采购框架CPM 5，需求框架合同  6，表示单采CPM',
  `media_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '媒体Id',
  `media_price` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '媒体单价',
  `first_price` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '媒体采购成本单价',
  `ad_type_id` tinyint(1) NOT NULL DEFAULT '0' COMMENT '广告形式Id（一个单子有可能包含一种或几种广告投放形式）',
  `cpm` int(11) NOT NULL DEFAULT '0' COMMENT 'CPM数',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CPM明细表（每个项目对应的各个厂家的ＣＰＭ的量，包含三个阶段的，1，客户需求CPM 2,执行排期CPM 3，第三方监测CPM）';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_person 结构
DROP TABLE IF EXISTS `base_person`;
CREATE TABLE IF NOT EXISTS `base_person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '公司人员Id',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '人名',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属区域（公司）Id',
  `mobile` varchar(30) NOT NULL DEFAULT '' COMMENT '手机',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `email` varchar(60) NOT NULL DEFAULT '' COMMENT '邮箱',
  `dept_id` int(11) NOT NULL DEFAULT '0' COMMENT '部门id',
  `state` int(11) DEFAULT '0' COMMENT '状态 0，正常 1，停用',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级领导',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `pay2` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `person_pay` decimal(18,2) DEFAULT '0.00' COMMENT '人员成本',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司人员表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_region 结构
DROP TABLE IF EXISTS `base_region`;
CREATE TABLE IF NOT EXISTS `base_region` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '地区Id',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '地区名称',
  `type` int(1) DEFAULT '1' COMMENT '地区类型\\\\n            1=省份\\\\n            2=市',
  `parent_id` int(11) DEFAULT '0' COMMENT '上级地区',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_report 结构
DROP TABLE IF EXISTS `base_report`;
CREATE TABLE IF NOT EXISTS `base_report` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '报告名字',
  `report_type_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '报告类型id',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_report_type 结构
DROP TABLE IF EXISTS `base_report_type`;
CREATE TABLE IF NOT EXISTS `base_report_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '报告类型名字：1，日报2，周报3，月报4，年报5，结案报告',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.oa_notify 结构
DROP TABLE IF EXISTS `oa_notify`;
CREATE TABLE IF NOT EXISTS `oa_notify` (
  `id` int(11) unsigned NOT NULL COMMENT '编号',
  `type` int(1) NOT NULL COMMENT '类型  1: 短信 2：邮件 3：微信',
  `title` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `content` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '内容',
  `files` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '附件（只适用于邮件）',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`item_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知内容管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.oa_notify_record 结构
DROP TABLE IF EXISTS `oa_notify_record`;
CREATE TABLE IF NOT EXISTS `oa_notify_record` (
  `id` int(11) unsigned NOT NULL COMMENT '编号',
  `notify_id` int(11) NOT NULL DEFAULT '0' COMMENT '通知通告ID',
  `user_ids` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '接受人Id,多个人可以用逗号隔开',
  `user_names` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '接受人名字,多个人可以用逗号隔开',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `oa_notify_record_notify_id` (`notify_id`),
  KEY `oa_notify_record_user_id` (`user_ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知发送记录';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sequence 结构
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE IF NOT EXISTS `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  `dates` int(4) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_dict 结构
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE IF NOT EXISTS `sys_dict` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `value` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '数据值',
  `label` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `description` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`item_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_fuction 结构
DROP TABLE IF EXISTS `sys_fuction`;
CREATE TABLE IF NOT EXISTS `sys_fuction` (
  `id` int(11) unsigned NOT NULL COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `resource_id` int(11) DEFAULT '0' COMMENT '资源ID',
  `operate_type_id` int(11) DEFAULT '0' COMMENT '操作类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能列表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_log 结构
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE IF NOT EXISTS `sys_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `content` longtext COMMENT '变更内容，包含更新前和更新后的内容',
  `ip` varchar(255) NOT NULL DEFAULT '' COMMENT 'ip地址',
  `operation` varchar(255) DEFAULT '' COMMENT '操作URL',
  `operator` varchar(255) DEFAULT '0' COMMENT '操作者',
  `parameter` longtext COMMENT '参数',
  `type` int(11) NOT NULL COMMENT '1.save,2.update,3delete',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_menu 结构
DROP TABLE IF EXISTS `sys_menu`;
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

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_resource 结构
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE IF NOT EXISTS `sys_resource` (
  `id` int(11) unsigned NOT NULL COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `resource_type` int(11) DEFAULT NULL COMMENT '1:基础资源，2:系统资源，3:需求资源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `activiti_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'activiti内部用名',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `is_system` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否系统内置，0非系统内置，1系统内置',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_role_fuction 结构
DROP TABLE IF EXISTS `sys_role_fuction`;
CREATE TABLE IF NOT EXISTS `sys_role_fuction` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `function_id` int(11) DEFAULT NULL COMMENT '功能id',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用(未使用)',
  `creator_id` int(11) unsigned DEFAULT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned DEFAULT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` tinyint(11) DEFAULT '0' COMMENT '逻辑删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_function` (`role_id`,`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色功能列表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_role_menu 结构
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '资源',
  KEY `index_rolemenu_role` (`role_id`) USING BTREE,
  KEY `index_rolemenu_menu` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `mobile` varchar(255) DEFAULT '' COMMENT '手机',
  `phone` varchar(255) DEFAULT '' COMMENT '联系电话',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `dept` varchar(255) DEFAULT '' COMMENT '部门名称',
  `lockedDate` datetime DEFAULT NULL COMMENT '锁定时间',
  `login_fail_count` int(11) DEFAULT NULL COMMENT '登录失败次数',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `dept_id` int(10) DEFAULT NULL COMMENT '部门id',
  `last_login_time` bigint(20) DEFAULT NULL COMMENT '最后登录时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_admin_loginname` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `admins` int(11) NOT NULL COMMENT '用户id',
  `roles` int(11) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admins` (`admins`,`roles`),
  KEY `index_adminrole_role` (`roles`) USING BTREE,
  KEY `index_adminrole_admin` (`admins`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- 数据导出被取消选择。


-- 导出  函数 fcf_vu.currval 结构
DROP FUNCTION IF EXISTS `currval`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
DETERMINISTIC
  BEGIN
    DECLARE value INTEGER;
    SET value = 0;
    SELECT current_value INTO value
    FROM sequence
    WHERE name = seq_name and dates=(SELECT DATE_FORMAT(NOW(), '%Y') FROM DUAL);
    IF value = 0 THEN
      UPDATE sequence SET dates=(SELECT DATE_FORMAT(NOW(), '%Y') FROM DUAL)
      WHERE name = seq_name;
    END IF;
    RETURN value;
  END//
DELIMITER ;


-- 导出  函数 fcf_vu.nextval 结构
DROP FUNCTION IF EXISTS `nextval`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
DETERMINISTIC
  BEGIN
    DECLARE value INTEGER;
    SET value = currval(seq_name);
    UPDATE sequence
    SET current_value = current_value + increment
    WHERE name = seq_name;
    RETURN currval(seq_name);
  END//
DELIMITER ;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
