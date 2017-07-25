-- 数据库变动记录
-- 5.20170706 魏，重新整理建表语句
-- 4.20170702 魏修改，流程相关表增加与activiti关联的process_instance_id字段
-- 3.20170628 兆华修改
-- 2.20170628 燕燕修改
-- 1.20170620 兆华建立


-- 20170724 weiqz
ALTER TABLE `base_media`
  CHANGE COLUMN `type` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '媒体类型 与base_media_type关联' AFTER `code`;

-- 20170720 weiqz
ALTER TABLE `base_order_cpm`
	CHANGE COLUMN `order_cpm_type` `order_cpm_type` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '类型：1，客户需求CPM 2,执行排期CPM 3，第三方检测CPM 4，采购框架CPM 5，需求框架合同  6，表示单采CPM' AFTER `order_id`;
ALTER TABLE `base_region`
	ADD COLUMN `del_flag` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除' AFTER `parent_id`;
ALTER TABLE `base_region`
	ADD COLUMN `item_status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用' AFTER `del_flag`;

-- 20170720 weiqz
ALTER TABLE `base_buy_order`
	ADD COLUMN `wf_step` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '工作流步骤数' AFTER `area_id`;

ALTER TABLE `base_buy_order`
	CHANGE COLUMN `status` `status` TINYINT(3) NULL DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束' AFTER `person_id`;
ALTER TABLE `base_buy_order_frame`
	CHANGE COLUMN `status` `status` TINYINT(3) NULL DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束' AFTER `person_id`;
ALTER TABLE `base_execute_order`
	CHANGE COLUMN `status` `status` TINYINT(3) NULL DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束' AFTER `person_sales_id`;
ALTER TABLE `base_execute_order_frame`
	CHANGE COLUMN `status` `status` TINYINT(3) NULL DEFAULT '0' COMMENT '执行状态：0，未生效；1，未开始； 2，进行中；3，已结束' AFTER `person_sales_id`;



--20170718 燕燕
ALTER TABLE `base_execute_order` ADD `sign_type` TINYINT(1)  NOT NULL  COMMENT '签约类型：1：代理2：直客'  AFTER `frame_id`;
ALTER TABLE `base_execute_order_frame` ADD `sign_type` TINYINT(1)  NOT NULL  COMMENT '签约类型：1：代理2：直客'  AFTER `frame_id`;


CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  `dates` int(4) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP FUNCTION IF EXISTS currval;
DELIMITER $
CREATE FUNCTION currval (seq_name VARCHAR(50))
     RETURNS INTEGER
     LANGUAGE SQL
     DETERMINISTIC
     CONTAINS SQL
     SQL SECURITY DEFINER
     COMMENT ''
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
END
$
DELIMITER ;

DROP FUNCTION IF EXISTS nextval;
DELIMITER $
CREATE FUNCTION nextval (seq_name VARCHAR(50))
     RETURNS INTEGER
     LANGUAGE SQL
     DETERMINISTIC
     CONTAINS SQL
     SQL SECURITY DEFINER
     COMMENT ''
BEGIN
     DECLARE value INTEGER;
    SET value = currval(seq_name);
     UPDATE sequence
          SET current_value = current_value + increment
          WHERE name = seq_name;
     RETURN currval(seq_name);
END
$
DELIMITER ;

-- 20170717
ALTER TABLE `base_execute_order`
	ADD COLUMN `custom_sign_id` int(11) NOT NULL DEFAULT 0 COMMENT '签约公司id，关联客户表' AFTER `area_id`;
ALTER TABLE `base_execute_order`
	ADD COLUMN `custom_adverser_id` int(11) NOT NULL DEFAULT 0 COMMENT '广告主id，关联custom表' AFTER `custom_sign_id`;
ALTER TABLE `base_execute_order`
	ADD COLUMN `industry_id` int(11) NOT NULL DEFAULT 0 COMMENT '行业id，关连行业表' AFTER `custom_adverser_id`;

ALTER TABLE `base_execute_order_frame`
	ADD COLUMN `custom_sign_id` int(11) NOT NULL DEFAULT 0 COMMENT '签约公司id，关联客户表' AFTER `area_id`;
ALTER TABLE `base_execute_order_frame`
	ADD COLUMN `custom_adverser_id` int(11) NOT NULL DEFAULT 0 COMMENT '广告主id，关联custom表' AFTER `custom_sign_id`;
ALTER TABLE `base_execute_order_frame`
	ADD COLUMN `industry_id` int(11) NOT NULL DEFAULT 0 COMMENT '行业id，关连行业表' AFTER `custom_adverser_id`;

