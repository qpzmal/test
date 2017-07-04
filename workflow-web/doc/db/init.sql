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