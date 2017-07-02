-- 初始化数据

INSERT INTO `sys_role` (`activiti_name`, `name`, `description`, `is_system`, `sort`) VALUES
	('customer4A', '4A', '4A客户', b'0', NULL),
	('mediaGM', '媒介主管', '媒介主管', b'0', NULL),
	('saler', '销售员工', '销售普通', b'0', NULL),
	('salerDM', '销售主管', '销售主管', b'0', NULL),
	('salerGM', '销售总经理', '销售总经理', b'0', NULL),
	('financialGM', '财务主管', '财务主管', b'0', NULL),
	('legalGM', '法务主管', '法务主管', b'0', NULL);
INSERT INTO `act_id_group` (`ID_`, `REV_`, `NAME_`, `TYPE_`) VALUES
	('legalGM', 1, '法务主管', ''),
	('customer4A', 1, '4A', ''),
	('financialGM', 1, '财务主管', ''),
	('mediaGM', 1, '媒介主管', ''),
	('saler', 1, '销售员工', ''),
	('salerDM', 1, '销售主管', ''),
	('salerGM', 1, '销售总经理', '');

INSERT INTO `sys_user` (``login_name`, `user_name`, `password`, `mobile`, `phone`, `address`, `email`, `dept`, `lockedDate`, `login_fail_count`, `sort`, `dept_id`, `last_login_time`, `item_status`) VALUES
	('u1', 'u1', '24a1db89d3bdaa23189e78887fb8b8fa', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('u2', 'u2', '5f37718acbbe271d522e572a3de5c616', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('u3', 'u3', 'b49d1b9ddb7a447a8fb7b99b8b80ccc9', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('u4', 'u4', '28d44c2fe6bc3157013010b7a574541b', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('u5', 'u5', '2fe153797330a1b1b25b8b26e4b2e768', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('u6', 'u6', '5310a57d8e55c36a02e4c698035fe29d', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('u7', 'u7', '4e74671fda8cac9b16b9719ccc92f7df', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `act_id_user` (`ID_`, `REV_`, `FIRST_`, `LAST_`, `EMAIL_`, `PWD_`, `PICTURE_ID_`) VALUES
	('u1', 1, 'u', '1', '', '24a1db89d3bdaa23189e78887fb8b8fa', NULL),
	('u2', 1, 'u', '2', '', '5f37718acbbe271d522e572a3de5c616', NULL),
	('u3', 1, 'u', '3', '', 'b49d1b9ddb7a447a8fb7b99b8b80ccc9', NULL),
	('u4', 1, 'u', '4', '', '28d44c2fe6bc3157013010b7a574541b', NULL),
	('u5', 1, 'u', '5', '', '2fe153797330a1b1b25b8b26e4b2e768', NULL),
	('u6', 1, 'u', '6', '', '5310a57d8e55c36a02e4c698035fe29d', NULL),
	('u7', 1, 'u', '7', '', '4e74671fda8cac9b16b9719ccc92f7df', NULL);

insert into act_id_membership VALUES ('u1', 'mediaGM'); -- 媒介
insert into act_id_membership VALUES ('u2', 'saler'); -- 销售普通
insert into act_id_membership VALUES ('u3', 'salerDM'); -- 销售主管
insert into act_id_membership VALUES ('u4', 'salerGM'); -- 销售总经理
insert into act_id_membership VALUES ('u5', 'financialGM'); -- 财务
insert into act_id_membership VALUES ('u6', 'legalGM'); -- 法务
insert into act_id_membership VALUES ('u7', 'customer4A'); -- 4A