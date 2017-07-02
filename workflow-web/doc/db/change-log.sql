-- 数据库变动记录
-- 4.20170702 魏修改，流程相关表增加与activiti关联的process_instance_id字段
-- 3.20170628 兆华修改
-- 2.20170628 燕燕修改
-- 1.20170620 兆华建立

-- 20170702 魏修改
ALTER TABLE `base_buy_order`
	ADD COLUMN `process_instance_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '流程ID' AFTER `id`;
ALTER TABLE `base_execute_order`
	ADD COLUMN `process_instance_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '流程ID' AFTER `id`;
ALTER TABLE `sys_role`
	ADD COLUMN `activiti_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'activiti内部用名' AFTER `id`,
	ADD UNIQUE INDEX `activiti_name` (`activiti_name`);

-- 20170628 兆华修改
CREATE TABLE IF NOT EXISTS `base_financialindex` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `number` varchar(64) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '标签名',
  `value` varchar(30) NOT NULL DEFAULT '' COMMENT '数据值',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务指标管理';

CREATE TABLE IF NOT EXISTS `base_custom` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '客户名称(签约公司名称)',
  `custom_name` varchar(100) NOT NULL DEFAULT '' COMMENT '客户名称(产品所有者，广告主名)',
  `custom_industry_id` tinyint(2) unsigned NOT NULL DEFAULT 1 COMMENT '客户行业 :1,汽车2,化妆品3,快消4,金融5,旅游6,教育7,游戏8,生活9,IT（下拉）',
  `custom_type` tinyint(1) unsigned NOT NULL DEFAULT 1 COMMENT '类型 1,4A公司 2,直客（下拉）',
  `contacts` varchar(30) NOT NULL DEFAULT '' COMMENT '联系人',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  `email` varchar(60) NOT NULL DEFAULT '' COMMENT '地址',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户管理';


CREATE TABLE IF NOT EXISTS `base_media` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '媒体编码（对应广告曝光的厂商编码）',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '媒体名字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体管理';



CREATE TABLE IF NOT EXISTS `base_media_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '媒体类型名字',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体类型管理';


CREATE TABLE IF NOT EXISTS `base_order_cpm` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` int(11) unsigned NOT NULL COMMENT '订单id',
  `order_cpm_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '类型：1，客户需求CPM 2,执行排期CPM 3，第三方检测CPM',
  `media_id` int(11) unsigned NOT NULL COMMENT '媒体Id',
  `media_price` DECIMAL(18,2) NOT NULL DEFAULT 0 COMMENT '媒体单价',
  `first_price` DECIMAL(18,2) NOT NULL DEFAULT 0 COMMENT '媒体采购成本单价',
  `ad_type_id` tinyint(1) NOT NULL COMMENT '广告形式Id（一个单子有可能包含一种或几种广告投放形式）',
  `cpm` int(11) NOT NULL DEFAULT 0 COMMENT 'CPM数',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CPM明细表（每个项目对应的各个厂家的ＣＰＭ的量，包含三个阶段的，1，客户需求CPM 2,执行排期CPM 3，第三方监测CPM）';

CREATE TABLE `base_report` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(10) NOT NULL COMMENT '报告名字',
	`report_type_id` INT(11) UNSIGNED NOT NULL COMMENT '报告类型id',
   `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
   `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
   `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
   `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告';


ALTER TABLE `base_buy_order`
	ADD COLUMN `custom_id` INT UNSIGNED NOT NULL COMMENT '客户id' AFTER `sec_order_num`,
	DROP COLUMN `sign_company_name`,
	DROP COLUMN `launch_media`;
ALTER TABLE `base_execute_order`
	DROP COLUMN `sign_type`,
	DROP COLUMN `sign_company_name`,
	DROP COLUMN `custom_industry_id`,
	DROP COLUMN `custom_name`;
-- DROP TABLE `base_tv`;  -- 此语句暂不执行

