INSERT INTO `sequence` (`name`, `current_value`, `increment`, `dates`) VALUES ('orderNum', 1, 1, 2017);

INSERT INTO `base_financialindex` (`number`, `name`, `value`, `del_flag`, `item_status`, `creator_id`, `updater_id`,`remark`) VALUES ('C001', '加税率', '1.06', 0, 0, 0, 0, '备注');
INSERT INTO `base_financialindex` (`number`, `name`, `value`, `del_flag`, `item_status`, `creator_id`, `updater_id`,`remark`) VALUES ('C002', '增值税税率', '0.06', 0, 0, 0, 0, '备注');
INSERT INTO `base_financialindex` (`number`, `name`, `value`, `del_flag`, `item_status`, `creator_id`, `updater_id`,`remark`) VALUES ('C003', '附加税税率', '0.12', 0, 0, 0, 0, '备注');
INSERT INTO `base_financialindex` (`number`, `name`, `value`, `del_flag`, `item_status`, `creator_id`, `updater_id`,`remark`) VALUES ('C004', '文化事业建设税的税率', '0.03', 0, 0, 0, 0, '备注');
INSERT INTO `base_financialindex` (`number`, `name`, `value`, `del_flag`, `item_status`, `creator_id`, `updater_id`,`remark`) VALUES ('C005', '销售提成比例（%）', '0.035', 0, 0, 0, 0, '备注');
INSERT INTO `base_financialindex` (`number`, `name`, `value`, `del_flag`, `item_status`, `creator_id`, `updater_id`,`remark`) VALUES ('C006', '工资房租分摊比例', '0.115', 0, 0, 0, 0, '备注');
INSERT INTO `base_financialindex` (`number`, `name`, `value`, `del_flag`, `item_status`, `creator_id`, `updater_id`,`remark`) VALUES ('C007', '所得税率', '0.15', 0, 0, 0, 0, '备注');

-- 资源表
INSERT INTO `sys_resource` (`id`, `name`, `item_status`, `creator_id`, `updater_id`, `resource_type`)
VALUES
	(101, '广告类型', 0, 0, 0, 1),
	(102, '客户行业', 0, 0, 0, 1),
	(103, '监测机构', 0, 0, 0, 1),
	(104, '省市地域', 0, 0, 0, 1),
	(105, '通讯录', 0, 0, 0, 1),
	(106, '客户管理', 0, 0, 0, 1),
	(107, '区域', 0, 0, 0, 1),
	(108, '财务指标', 0, 0, 0, 1),
	(109, '媒体', 0, 0, 0, 1),
	(110, '媒体类型', 0, 0, 0, 1),
	(111, '部门', 0, 0, 0, 1),
	(112, '客户核算', 0, 0, 0, 1),
	(113, '区域核算', 0, 0, 0, 1),
	(201, '综合计价', 0, 0, 0, 1),
	(202, '需求单', 0, 0, 0, 1),
	(203, '框架需求', 0, 0, 0, 1),
	(204, '采购单', 0, 0, 0, 1),
	(205, '框架采购', 0, 0, 0, 1),
	(206, '排期执行', 0, 0, 0, 1),
	(301, '待处理流程', 0, 0, 0, 1),
	(302, '运行中流程', 0, 0, 0, 1),
	(303, '已结束流程', 0, 0, 0, 1),
	(401, '分公司财务分析', 0, 0, 0, 1),
	(402, '采购报表', 0, 0, 0, 1),
	(403, '客户分析报表', 0, 0, 0, 1),
	(404, '销售报表', 0, 0, 0, 1),
	(501, '用户管理', 0, 0, 0, 1),
	(502, '角色管理', 0, 0, 0, 1);



-- 功能权限表
INSERT INTO `sys_fuction` (`id`, `name`, `item_status`, `creator_id`, `updater_id`, `resource_id`, `operate_type_id`)
VALUES
	(10101, '添加广告类型', 0, 0, 0, 101, NULL),
	(10102, '更新广告类型', 0, 0, 0, 101, NULL),
	(10103, '删除广告类型', 0, 0, 0, 101, NULL),
	(10201, '添加客户行业', 0, 0, 0, 102, NULL),
	(10202, '更新客户行业', 0, 0, 0, 102, NULL),
	(10203, '删除客户行业', 0, 0, 0, 102, NULL),
	(10301, '添加监测机构', 0, 0, 0, 103, NULL),
	(10302, '更新监测机构', 0, 0, 0, 103, NULL),
	(10303, '删除监测机构', 0, 0, 0, 103, NULL),
	(10401, '添加省市地域', 0, 0, 0, 104, NULL),
	(10402, '更新省市地域', 0, 0, 0, 104, NULL),
	(10403, '删除省市地域', 0, 0, 0, 104, NULL),
	(10501, '添加通讯录', 0, 0, 0, 105, NULL),
	(10502, '更新通讯录', 0, 0, 0, 105, NULL),
	(10503, '删除通讯录', 0, 0, 0, 105, NULL),
	(10601, '添加客户', 0, 0, 0, 106, NULL),
	(10602, '更新客户', 0, 0, 0, 106, NULL),
	(10603, '删除客户', 0, 0, 0, 106, NULL),
	(10701, '添加区域', 0, 0, 0, 107, NULL),
	(10702, '更新区域', 0, 0, 0, 107, NULL),
	(10703, '删除区域', 0, 0, 0, 107, NULL),
	(10801, '添加财务指标', 0, 0, 0, 108, NULL),
	(10802, '更新财务指标', 0, 0, 0, 108, NULL),
	(10803, '删除财务指标', 0, 0, 0, 108, NULL),
	(10901, '添加媒体', 0, 0, 0, 109, NULL),
	(10902, '更新媒体', 0, 0, 0, 109, NULL),
	(10903, '删除媒体', 0, 0, 0, 109, NULL),
	(11001, '添加媒体类型', 0, 0, 0, 110, NULL),
	(11002, '更新媒体类型', 0, 0, 0, 110, NULL),
	(11003, '删除媒体类型', 0, 0, 0, 110, NULL),
	(11101, '添加部门', 0, 0, 0, 111, NULL),
	(11102, '更新部门', 0, 0, 0, 111, NULL),
	(11103, '删除部门', 0, 0, 0, 111, NULL),
	(11201, '添加客户核算', 0, 0, 0, 112, NULL),
	(11202, '更新客户核算', 0, 0, 0, 112, NULL),
	(11203, '删除客户核算', 0, 0, 0, 112, NULL),
	(11301, '添加区域核算', 0, 0, 0, 113, NULL),
	(11302, '更新区域核算', 0, 0, 0, 113, NULL),
	(11303, '删除区域核算', 0, 0, 0, 113, NULL),
	(20101, '综合计价', 0, 0, 0, 201, NULL),
	(20201, '添加需求单', 0, 0, 0, 202, NULL),
	(20202, '更新需求单', 0, 0, 0, 202, NULL),
	(20203, '删除需求单', 0, 0, 0, 202, NULL),
	(20301, '添加框架需求', 0, 0, 0, 203, NULL),
	(20302, '更新框架需求', 0, 0, 0, 203, NULL),
	(20303, '删除框架需求', 0, 0, 0, 203, NULL),
	(20401, '添加采购单', 0, 0, 0, 204, NULL),
	(20402, '更新采购单', 0, 0, 0, 204, NULL),
	(20403, '删除采购单', 0, 0, 0, 204, NULL),
	(20501, '添加框架采购', 0, 0, 0, 205, NULL),
	(20502, '更新框架采购', 0, 0, 0, 205, NULL),
	(20503, '删除框架采购', 0, 0, 0, 205, NULL),
	(20601, '添加排期执行单', 0, 0, 0, 206, NULL),
	(20602, '更新排期执行单', 0, 0, 0, 206, NULL),
	(20603, '删除排期执行单', 0, 0, 0, 206, NULL),
	(30101, '办理待处理流程', 0, 0, 0, 301, NULL),
	(30201, '查看运行中流程', 0, 0, 0, 302, NULL),
	(30301, '查看已结束流程', 0, 0, 0, 303, NULL),
	(40101, '查看分公司财务分析', 0, 0, 0, 401, NULL),
	(40201, '查看采购报表', 0, 0, 0, 402, NULL),
	(40301, '查看客户分析报表', 0, 0, 0, 403, NULL),
	(40401, '查看销售报表', 0, 0, 0, 404, NULL),
	(50101, '添加用户管理', 0, 0, 0, 501, NULL),
	(50102, '更新用户管理', 0, 0, 0, 501, NULL),
	(50103, '删除用户管理', 0, 0, 0, 501, NULL),
	(50201, '添加角色', 0, 0, 0, 502, NULL),
	(50202, '更新角色', 0, 0, 0, 502, NULL),
	(50203, '删除角色', 0, 0, 0, 502, NULL),
	(50204, '角色授权', 0, 0, 0, 502, NULL);


-- 管理员初始化
-- 93d6f2648c6f590a7bc8cd0be9243149--123456
-- f7ad7cc1c8ee8431b0a8c3abb872a33c--111111
INSERT INTO `sys_user` ( `id`,`login_name`, `user_name`, `password`, `mobile`, `phone`, `address`, `email`, `dept`, `lockedDate`, `login_fail_count`, `sort`, `dept_id`, `last_login_time`, `del_flag`, `item_status`)
VALUES (1, 'admin', 'admin', '93d6f2648c6f590a7bc8cd0be9243149', '', '', '', '', '', NULL, NULL, NULL, NULL, 1500290298281, 0, 0);

INSERT INTO `sys_user_role` (`id`, `admins`, `roles`) VALUES (1, 1, 1);

INSERT INTO `sys_role` (`id`, `activiti_name`, `name`, `description`, `is_system`, `sort`, `del_flag`) VALUES (1, '', 'admin', 'admin', b'1', NULL, 0);
INSERT INTO `base_person` (`name`, `area_id`, `mobile`, `address`, `email`, `dept_id`, `state`, `parent_id`, `del_flag`, `item_status`, `pay2`, `person_pay`, `create_time`, `update_time`, `remark`) VALUES ('admin', 1, '1', '1', '1', 1, 0, 0, 0, 0, 0.00, 0.00, '2017-07-19 22:24:54', '2017-07-19 22:24:54', '');

INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10101, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10102, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10103, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10201, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10202, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10203, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10301, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10302, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10303, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10401, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10402, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10403, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10501, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10502, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10503, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10601, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10602, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10603, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10701, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10702, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10703, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10801, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10802, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10803, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10901, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10902, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 10903, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11001, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11002, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11003, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11101, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11102, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11103, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11201, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11202, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11203, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11301, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11302, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 11303, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20101, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20201, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20202, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20203, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20301, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20302, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20303, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20401, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20402, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20403, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20501, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20502, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20503, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20601, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20602, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 20603, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 30101, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 30201, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 30301, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 40101, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 40201, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 40301, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 40401, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 50101, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 50102, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 50103, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 50201, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 50202, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 50203, '', 0, NULL, NULL, 0);
INSERT INTO `sys_role_fuction` (`role_id`, `function_id`, `name`, `item_status`, `creator_id`, `updater_id`, `del_flag`) VALUES (1, 50204, '', 0, NULL, NULL, 0);


-- 初始化数据
INSERT INTO `sys_role` (`activiti_name`, `name`, `description`, `is_system`, `sort`) VALUES
	('customer4A', '4A', '4A客户', b'1', NULL),
	('mediaGM', '媒介主管', '媒介主管', b'1', NULL),
	('saler', '销售员工', '销售普通', b'1', NULL),
	('salerDM', '销售主管', '销售主管', b'1', NULL),
	('salerGM', '销售总经理', '销售总经理', b'1', NULL),
	('financialGM', '财务主管', '财务主管', b'1', NULL),
	('legalGM', '法务主管', '法务主管', b'1', NULL),
	('media', '媒介员工', '媒介员工', b'1', NULL);

INSERT INTO `sys_user` (`login_name`, `user_name`, `password`) VALUES
   ('u1', 'u1', 'f7ad7cc1c8ee8431b0a8c3abb872a33c'),
	('u2', 'u2', 'f7ad7cc1c8ee8431b0a8c3abb872a33c'),
	('u3', 'u3', 'f7ad7cc1c8ee8431b0a8c3abb872a33c'),
	('u4', 'u4', 'f7ad7cc1c8ee8431b0a8c3abb872a33c'),
	('u5', 'u5', 'f7ad7cc1c8ee8431b0a8c3abb872a33c'),
	('u6', 'u6', 'f7ad7cc1c8ee8431b0a8c3abb872a33c'),
	('u7', 'u7', 'f7ad7cc1c8ee8431b0a8c3abb872a33c'),
	('u8', 'u8', 'f7ad7cc1c8ee8431b0a8c3abb872a33c');

-- INSERT INTO `act_id_group` (`ID_`, `REV_`, `NAME_`, `TYPE_`) VALUES
-- 	('legalGM', 1, '法务主管', ''),
-- 	('customer4A', 1, '4A', ''),
-- 	('financialGM', 1, '财务主管', ''),
-- 	('mediaGM', 1, '媒介主管', ''),
-- 	('saler', 1, '销售员工', ''),
-- 	('salerDM', 1, '销售主管', ''),
-- 	('salerGM', 1, '销售总经理', ''),
-- 	('media', 1, '媒介员工', '');
-- INSERT INTO `act_id_user` (`ID_`, `REV_`, `FIRST_`, `LAST_`, `EMAIL_`, `PWD_`, `PICTURE_ID_`) VALUES
-- 	('u1', 1, 'u', '1', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL),
-- 	('u2', 1, 'u', '2', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL),
-- 	('u3', 1, 'u', '3', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL),
-- 	('u4', 1, 'u', '4', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL),
-- 	('u5', 1, 'u', '5', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL),
-- 	('u6', 1, 'u', '6', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL),
-- 	('u7', 1, 'u', '7', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL),
-- 	('u8', 1, 'u', '8', '', 'f7ad7cc1c8ee8431b0a8c3abb872a33c', NULL);

-- insert into act_id_membership VALUES ('u1', 'mediaGM'); -- 媒介管理
-- insert into act_id_membership VALUES ('u2', 'saler'); -- 销售普通
-- insert into act_id_membership VALUES ('u3', 'salerDM'); -- 销售主管
-- insert into act_id_membership VALUES ('u4', 'salerGM'); -- 销售总经理
-- insert into act_id_membership VALUES ('u5', 'financialGM'); -- 财务管理
-- insert into act_id_membership VALUES ('u6', 'legalGM'); -- 法务管理
-- insert into act_id_membership VALUES ('u7', 'customer4A'); -- 4A
-- insert into act_id_membership VALUES ('u8', 'media'); -- 媒介普通



