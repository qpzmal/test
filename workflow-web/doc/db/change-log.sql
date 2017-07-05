-- 数据库变动记录
-- 5.20170706 魏，重新整理建表语句
-- 4.20170702 魏修改，流程相关表增加与activiti关联的process_instance_id字段
-- 3.20170628 兆华修改
-- 2.20170628 燕燕修改
-- 1.20170620 兆华建立





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


-- 导出  表 fcf_vu.act_evt_log 结构
DROP TABLE IF EXISTS `act_evt_log`;
CREATE TABLE IF NOT EXISTS `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ge_bytearray 结构
DROP TABLE IF EXISTS `act_ge_bytearray`;
CREATE TABLE IF NOT EXISTS `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ge_property 结构
DROP TABLE IF EXISTS `act_ge_property`;
CREATE TABLE IF NOT EXISTS `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_actinst 结构
DROP TABLE IF EXISTS `act_hi_actinst`;
CREATE TABLE IF NOT EXISTS `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_attachment 结构
DROP TABLE IF EXISTS `act_hi_attachment`;
CREATE TABLE IF NOT EXISTS `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_comment 结构
DROP TABLE IF EXISTS `act_hi_comment`;
CREATE TABLE IF NOT EXISTS `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_detail 结构
DROP TABLE IF EXISTS `act_hi_detail`;
CREATE TABLE IF NOT EXISTS `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_identitylink 结构
DROP TABLE IF EXISTS `act_hi_identitylink`;
CREATE TABLE IF NOT EXISTS `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_procinst 结构
DROP TABLE IF EXISTS `act_hi_procinst`;
CREATE TABLE IF NOT EXISTS `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_taskinst 结构
DROP TABLE IF EXISTS `act_hi_taskinst`;
CREATE TABLE IF NOT EXISTS `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_hi_varinst 结构
DROP TABLE IF EXISTS `act_hi_varinst`;
CREATE TABLE IF NOT EXISTS `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_id_group 结构
DROP TABLE IF EXISTS `act_id_group`;
CREATE TABLE IF NOT EXISTS `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_id_info 结构
DROP TABLE IF EXISTS `act_id_info`;
CREATE TABLE IF NOT EXISTS `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_id_membership 结构
DROP TABLE IF EXISTS `act_id_membership`;
CREATE TABLE IF NOT EXISTS `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_id_user 结构
DROP TABLE IF EXISTS `act_id_user`;
CREATE TABLE IF NOT EXISTS `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_procdef_info 结构
DROP TABLE IF EXISTS `act_procdef_info`;
CREATE TABLE IF NOT EXISTS `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_re_deployment 结构
DROP TABLE IF EXISTS `act_re_deployment`;
CREATE TABLE IF NOT EXISTS `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_re_model 结构
DROP TABLE IF EXISTS `act_re_model`;
CREATE TABLE IF NOT EXISTS `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_re_procdef 结构
DROP TABLE IF EXISTS `act_re_procdef`;
CREATE TABLE IF NOT EXISTS `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ru_event_subscr 结构
DROP TABLE IF EXISTS `act_ru_event_subscr`;
CREATE TABLE IF NOT EXISTS `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ru_execution 结构
DROP TABLE IF EXISTS `act_ru_execution`;
CREATE TABLE IF NOT EXISTS `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ru_identitylink 结构
DROP TABLE IF EXISTS `act_ru_identitylink`;
CREATE TABLE IF NOT EXISTS `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ru_job 结构
DROP TABLE IF EXISTS `act_ru_job`;
CREATE TABLE IF NOT EXISTS `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ru_task 结构
DROP TABLE IF EXISTS `act_ru_task`;
CREATE TABLE IF NOT EXISTS `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.act_ru_variable 结构
DROP TABLE IF EXISTS `act_ru_variable`;
CREATE TABLE IF NOT EXISTS `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。


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
  `name` varchar(60) NOT NULL COMMENT '公司名字',
  `parent_id` int(11) NOT NULL COMMENT '父级公司',
  `code` varchar(10) NOT NULL COMMENT '公司编号,用于生成项目号',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `room_pay` decimal(18,2) DEFAULT '0.00' COMMENT '房租成本',
  `remark` varchar(255) NOT NULL DEFAULT '无' COMMENT '备注',
  `person_pay` decimal(18,2) DEFAULT '0.00' COMMENT '人员成本',
  `other_pay` decimal(18,2) DEFAULT '0.00' COMMENT '其他费用',
  `tax_amount` decimal(18,2) DEFAULT '0.00' COMMENT '含税金额',
  `tax_free_amount` decimal(18,2) DEFAULT '0.00' COMMENT '不含税金额',
  `pay2` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `pay3` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `pay4` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域（公司）信息';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_buy_order 结构
DROP TABLE IF EXISTS `base_buy_order`;
CREATE TABLE IF NOT EXISTS `base_buy_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `process_instance_id` varchar(64) NOT NULL DEFAULT '' COMMENT '流程ID',
  `order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL DEFAULT '' COMMENT '项目补充编号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '项目类型：1，框架 2,单采（下拉）',
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目日期',
  `amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '项目总金额',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行开始日期',
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '执行结束日期',
  `person_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目负责人ID',
  `status` tinyint(3) DEFAULT '1' COMMENT '执行状态：1，未开始； 2，进行中；3，已结束',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '付款比例',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `area_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属区域Id',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
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
  `order_num` varchar(15) NOT NULL COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL COMMENT '项目补充编号',
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `order_date` datetime NOT NULL COMMENT '订单日期',
  `amount` decimal(18,2) NOT NULL COMMENT '项目总金额',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `start_date` datetime NOT NULL COMMENT '执行开始日期',
  `end_date` datetime NOT NULL COMMENT '执行结束日期',
  `person_id` int(11) NOT NULL COMMENT '项目负责人ID',
  `status` tinyint(3) DEFAULT '1' COMMENT '执行状态：1，未开始； 2，进行中；3，已结束',
  `delivery_area_ids` varchar(255) NOT NULL COMMENT '投放地区编号，多个地区用逗号隔开',
  `delivery_area_names` varchar(255) NOT NULL COMMENT '投放地区名称，多个地区用逗号隔开',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '付款比例',
  `user_id` int(11) NOT NULL,
  `area_id` int(11) NOT NULL COMMENT '所属区域Id',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒介采购合同';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_custom 结构
DROP TABLE IF EXISTS `base_custom`;
CREATE TABLE IF NOT EXISTS `base_custom` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '客户名称(签约公司名称)',
  `parent_id` int(11) NOT NULL COMMENT '所属上一级id',
  `custom_industry_id` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '客户行业 :1,汽车2,化妆品3,快消4,金融5,旅游6,教育7,游戏8,生活9,IT（下拉）',
  `custom_type` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '类型 1,4A公司 2,直客（下拉）',
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

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_dept 结构
DROP TABLE IF EXISTS `base_dept`;
CREATE TABLE IF NOT EXISTS `base_dept` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门Id',
  `name` varchar(100) NOT NULL COMMENT '部门名字',
  `level` int(11) NOT NULL COMMENT '层次',
  `parent_id` int(11) NOT NULL COMMENT '上级部门',
  `sort` int(10) NOT NULL COMMENT '排序（升序）',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `area_id` int(11) NOT NULL COMMENT '所属区域Id',
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
  `person_sales_id` int(11) NOT NULL DEFAULT '0' COMMENT '销售负责人ID',
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
  `status` tinyint(3) DEFAULT '1' COMMENT '执行状态：1，未开始； 2，进行中；3，已结束',
  `signing_intention` varchar(30) DEFAULT NULL COMMENT '签约意向',
  `person_payee_id` int(11) NOT NULL DEFAULT '0' COMMENT '收款负责人',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '回款比例',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '登录用户id',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `frame_id` int(11) DEFAULT NULL COMMENT '框架协议id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户需求单';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_execute_order_frame 结构
DROP TABLE IF EXISTS `base_execute_order_frame`;
CREATE TABLE IF NOT EXISTS `base_execute_order_frame` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增',
  `process_instance_id` varchar(64) NOT NULL DEFAULT '' COMMENT '流程ID',
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `order_num` varchar(15) NOT NULL COMMENT '项目编号',
  `sec_order_num` varchar(15) NOT NULL COMMENT '项目补充编号',
  `order_date` datetime NOT NULL COMMENT '项目日期',
  `delivery_area_ids` varchar(255) NOT NULL COMMENT '投放地区编号，多个地区用逗号隔开',
  `delivery_area_names` varchar(255) NOT NULL COMMENT '投放地区名称，多个地区用逗号隔开',
  `area_id` int(11) NOT NULL COMMENT '公司区域编号，连接vu_Area表（如北京公司）',
  `person_sales_id` int(11) NOT NULL COMMENT '销售负责人ID',
  `amount` decimal(18,2) DEFAULT '0.00' COMMENT '项目总金额',
  `public_rebate` decimal(5,4) NOT NULL COMMENT '对公返点',
  `private_rebate` decimal(5,4) NOT NULL COMMENT '对私返点',
  `tax_amount` decimal(18,2) DEFAULT '0.00' COMMENT '含税金额',
  `tax_free_amount` decimal(18,2) DEFAULT '0.00' COMMENT '不含税金额',
  `monitor_ids` varchar(50) NOT NULL COMMENT '客户要求监测编号:1,秒针2,Admaster3,记刻（下拉菜单，可多选，多选用逗号隔开)',
  `monitor_names` varchar(100) NOT NULL COMMENT '客户要求监测名称',
  `start_date` datetime NOT NULL COMMENT '投放开始日期',
  `end_date` datetime NOT NULL COMMENT '投放结束日期',
  `person_leader_id` int(11) NOT NULL COMMENT '项目负责人ID',
  `our_monitor_name` varchar(50) NOT NULL COMMENT '我方监测',
  `report_type_id` varchar(50) NOT NULL COMMENT '报告类型Id',
  `status` tinyint(3) DEFAULT '1' COMMENT '执行状态：1，未开始； 2，进行中；3，已结束',
  `signing intention` varchar(30) DEFAULT NULL COMMENT '签约意向',
  `person_payee_id` int(11) NOT NULL COMMENT '收款负责人',
  `pay_percent` decimal(5,4) DEFAULT '0.0000' COMMENT '回款比例',
  `user_id` int(11) NOT NULL COMMENT '登录用户id',
  `account_period` int(5) DEFAULT '0' COMMENT '账期，以日为单位',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL COMMENT '备注',
  `frame_id` int(11) DEFAULT NULL COMMENT '框架协议id',
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
  `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务指标管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_industry 结构
DROP TABLE IF EXISTS `base_industry`;
CREATE TABLE IF NOT EXISTS `base_industry` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '行业id',
  `name` varchar(255) NOT NULL COMMENT '行业名称',
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

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_media_type 结构
DROP TABLE IF EXISTS `base_media_type`;
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
  `order_cpm_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型：1，客户需求CPM 2,执行排期CPM 3，第三方检测CPM',
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
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CPM明细表（每个项目对应的各个厂家的ＣＰＭ的量，包含三个阶段的，1，客户需求CPM 2,执行排期CPM 3，第三方监测CPM）';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_person 结构
DROP TABLE IF EXISTS `base_person`;
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
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `pay2` decimal(18,2) DEFAULT '0.00' COMMENT '备用字段',
  `person_pay` decimal(18,2) DEFAULT '0.00' COMMENT '人员成本',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司人员表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_region 结构
DROP TABLE IF EXISTS `base_region`;
CREATE TABLE IF NOT EXISTS `base_region` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '地区Id',
  `name` varchar(128) NOT NULL COMMENT '地区名称',
  `type` int(1) DEFAULT NULL COMMENT '地区类型\\n            1=省份\\n            2=市',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级地区',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.base_report 结构
DROP TABLE IF EXISTS `base_report`;
CREATE TABLE IF NOT EXISTS `base_report` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '报告名字',
  `report_type_id` int(11) unsigned NOT NULL COMMENT '报告类型id',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
  `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
  `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
  `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
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
  `remark` varchar(500) NOT NULL,
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
  `remark` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`item_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知内容管理';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.oa_notify_record 结构
DROP TABLE IF EXISTS `oa_notify_record`;
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
  `remark` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`item_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- 数据导出被取消选择。


-- 导出  表 fcf_vu.sys_log 结构
DROP TABLE IF EXISTS `sys_log`;
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


-- 导出  表 fcf_vu.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `activiti_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'activiti内部用名',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `is_system` bit(1) NOT NULL COMMENT '是否系统内置',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `activiti_name` (`activiti_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';

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
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
