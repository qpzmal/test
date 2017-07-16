--资源表
INSERT INTO `sys_resource` (`id`, `name`, `item_status`, `creator_id`, `updater_id`, `create_time`, `update_time`, `resource_type`)
VALUES
	(101, '广告类型', 0, 0, 0, '2017-07-02 13:37:02', '2017-07-16 11:26:54', 1),
	(102, '客户行业', 0, 0, 0, '2017-07-02 13:37:02', '2017-07-16 11:23:56', 1),
	(103, '监测机构', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:26:55', 1),
	(104, '省市地域', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:26:56', 1),
	(105, '通讯录', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(106, '客户管理', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(107, '区域', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(108, '财务指标', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(109, '媒体', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(110, '媒体类型', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(111, '部门', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(112, '客户核算', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(113, '区域核算', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:27:30', 1),
	(201, '综合计价', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:26', 1),
	(202, '需求单', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:35', 1),
	(203, '框架需求', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(204, '采购单', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(205, '框架采购', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(301, '待处理流程', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(302, '运行中流程', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(303, '已结束流程', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(401, '分公司财务分析', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(402, '采购报表', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(403, '客户分析报表', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(404, '销售报表', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(501, '用户管理', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1),
	(502, '角色管理', 0, 0, 0, '2017-07-02 13:37:18', '2017-07-16 11:29:51', 1);



-- 功能权限表
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('1', '添加广告类型', '0', '0', '0', '1');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('2', '更新广告类型', '0', '0', '0', '1');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('3', '删除广告类型', '0', '0', '0', '1');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('4', '添加客户行业', '0', '0', '0', '2');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('5', '更新客户行业', '0', '0', '0', '2');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('6', '删除客户行业', '0', '0', '0', '2');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('7', '添加监测机构', '0', '0', '0', '3');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('8', '更新监测机构', '0', '0', '0', '3');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('9', '删除监测机构', '0', '0', '0', '3');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('10', '添加省市地域', '0', '0', '0', '4');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('11', '更新省市地域', '0', '0', '0', '4');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('12', '删除省市地域', '0', '0', '0', '4');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('13', '添加通讯录', '0', '0', '0', '5');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('14', '更新通讯录', '0', '0', '0', '5');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('15', '删除通讯录', '0', '0', '0', '5');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('16', '添加客户', '0', '0', '0', '6');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('17', '更新客户', '0', '0', '0', '6');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('18', '删除客户', '0', '0', '0', '6');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('19', '添加区域', '0', '0', '0', '7');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('20', '更新区域', '0', '0', '0', '7');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('21', '删除区域', '0', '0', '0', '7');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('22', '添加财物指标', '0', '0', '0', '8');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('23', '更新财物指标', '0', '0', '0', '8');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('24', '删除财物指标', '0', '0', '0', '8');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('25', '添加媒体', '0', '0', '0', '9');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('26', '更新媒体', '0', '0', '0', '9');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('27', '删除媒体', '0', '0', '0', '9');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('28', '添加媒体类型', '0', '0', '0', '10');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('29', '更新媒体类型', '0', '0', '0', '10');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('30', '删除媒体类型', '0', '0', '0', '10');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('31', '添加部门', '0', '0', '0', '11');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('32', '更新部门', '0', '0', '0', '11');
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`,   `resource_id`) VALUES ('33', '删除部门', '0', '0', '0', '11');



-- 初始化数据

INSERT INTO `sys_role` (`activiti_name`, `name`, `description`, `is_system`, `sort`) VALUES
	('customer4A', '4A', '4A客户', b'0', NULL),
	('mediaGM', '媒介主管', '媒介主管', b'0', NULL),
	('saler', '销售员工', '销售普通', b'0', NULL),
	('salerDM', '销售主管', '销售主管', b'0', NULL),
	('salerGM', '销售总经理', '销售总经理', b'0', NULL),
	('financialGM', '财务主管', '财务主管', b'0', NULL),
	('legalGM', '法务主管', '法务主管', b'0', NULL),
	('media', '媒介员工', '媒介员工', b'0', NULL);
INSERT INTO `act_id_group` (`ID_`, `REV_`, `NAME_`, `TYPE_`) VALUES
	('legalGM', 1, '法务主管', ''),
	('customer4A', 1, '4A', ''),
	('financialGM', 1, '财务主管', ''),
	('mediaGM', 1, '媒介主管', ''),
	('saler', 1, '销售员工', ''),
	('salerDM', 1, '销售主管', ''),
	('salerGM', 1, '销售总经理', ''),
	('media', 1, '媒介员工', '');

INSERT INTO `sys_user` (`login_name`, `user_name`, `password`) VALUES
   ('u1', 'u1', '24a1db89d3bdaa23189e78887fb8b8fa'),
	('u2', 'u2', '24a1db89d3bdaa23189e78887fb8b8fa'),
	('u3', 'u3', '24a1db89d3bdaa23189e78887fb8b8fa'),
	('u4', 'u4', '24a1db89d3bdaa23189e78887fb8b8fa'),
	('u5', 'u5', '24a1db89d3bdaa23189e78887fb8b8fa'),
	('u6', 'u6', '24a1db89d3bdaa23189e78887fb8b8fa'),
	('u7', 'u7', '24a1db89d3bdaa23189e78887fb8b8fa'),
	('u8', 'u8', '24a1db89d3bdaa23189e78887fb8b8fa');
INSERT INTO `act_id_user` (`ID_`, `REV_`, `FIRST_`, `LAST_`, `EMAIL_`, `PWD_`, `PICTURE_ID_`) VALUES
	('u1', 1, 'u', '1', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u2', 1, 'u', '2', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u3', 1, 'u', '3', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u4', 1, 'u', '4', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u5', 1, 'u', '5', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u6', 1, 'u', '6', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u7', 1, 'u', '7', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u8', 1, 'u', '8', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL);

insert into act_id_membership VALUES ('u1', 'mediaGM'); -- 媒介管理
insert into act_id_membership VALUES ('u2', 'saler'); -- 销售普通
insert into act_id_membership VALUES ('u3', 'salerDM'); -- 销售主管
insert into act_id_membership VALUES ('u4', 'salerGM'); -- 销售总经理
insert into act_id_membership VALUES ('u5', 'financialGM'); -- 财务管理
insert into act_id_membership VALUES ('u6', 'legalGM'); -- 法务管理
insert into act_id_membership VALUES ('u7', 'customer4A'); -- 4A
insert into act_id_membership VALUES ('u8', 'media'); -- 媒介普通