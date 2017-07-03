开发文档
=

导入本地patchca-0.5.0.jar包到maven库
> mvn install:install-file  -Dfile=/Users/wangry/WorkFlower/patchca-0.5.0.jar  -DgroupId=org.patchca  -DartifactId=patchca -Dversion=0.5.0 -Dpackaging=jar

模版主页：http://www.zi-han.net/theme/hplus/

####相关插件版本：
1. layer--3.0.3
官网：http://layer.layui.com/
2. Font Awesome--4.4.0
图标示例：http://code.zoomla.cn/boot/font.html


####对static文件夹的修改
1. 更新layer版本，由2.0升级到3.0.3版本

####项目中部分守则
1. 每个表有以下字段，字段为NOT NULL 并设置默认值
>     `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
>     `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
>     `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
>     `creator_id` int(11) unsigned NOT NULL COMMENT '创建者ID',
>     `updater_id` int(11) unsigned NOT NULL COMMENT '更新者ID',
>     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
>     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
>     `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',

2. 按钮颜色以及对应图案
* 新增 btn-primary --> fa-plus
* 修改 btn-info --> fa-pencil
* 删除 btn-danger --> fa-trash
* 搜索 btn-info --> fa-search
* 查看 btn-default --> fa-photo 或 fa-search
* 返回 btn-default --> fa-reply


####问题记录
1. 数据库--菜单对应如下

| 表名 | 表名注释（对应菜单名） | 备注 |
| -----|:----| ----:|
| base_adtype    | 广告类型管理（已做，广告类型管理）    |  无   |
| base_area    | 区域管理（未做，区域管理）   |  无   |
| base_buy_order    | 需求订单（未做，工作流、添制采购单）    |  无   |
| base_custom    | 客户管理（未做，客户管理）    |  无   |
| base_dept    | 部门管理（未做）    |  无   |
| base_execute_order    | 执行订单（未做，工作流、添制需求单）    |  无   |
| base_financialindex    | 财务指标管理（未做）    |  无   |
| base_industry    | 客户行业管理（已做）    |  无   |
| base_media    | 媒体管理（未做）    |  无   |
| base_media_type    | 媒体类型管理（未做）    |  无   |
| base_monitor_request    | 监测机构管理（已做）    |  无   |
| base_order_cpm    | CPM明细表（未做）    |  无   |
| base_person    | 公司内部用户通信录表 （未做，通讯录）   |  无   |
| base_region    | 省市地域管理表（已做，省市地域管理）    |  无   |
| base_report    | 报表管理表（未做，报表相关）    |  无   |
| base_reporttype    | 报表类型管理表（未做，报表相关）    |  无   |
| oa_notify    | 通知内容管理（未做）    |  无   |
| oa_notify_record    | 通知发送记录（未做，只读）    |  无   |
| sys_dict    | 系统字典（不做页面）   |  无   |
| sys_log    | 系统日志（暂时不做）   |  无   |
| sys_menu    | 系统资源   |  无   |
| sys_role    | 系统角色   |  无   |
| sys_role_menu    | 角色资源   |  无   |
| sys_user    | 系统用户   |  无   |
| sys_user_role    | 用户角色中间   |  无   |

#### 流程相关定义
* 采购定价单（buy_frame）--》年度采购框架合同模型，三方（媒介、销售总经理、财务）顺序审批--》年度采购框架合同
* 采购定价单（buy）--》CPM采购成本单模型，四方（媒介、销售总经理、财务、法务）顺序审批--》CPM采购成本单
* 客户需求单（sale_frame）--》年度需求单审批模型，三方（销售总经理、媒介、财务）顺序审批--》年度需求单框架合同
* 客户需求单（sale）--》合同审批模型，五方（销售主管、销售总经理、媒介、财务、法务）顺序审批--》需求单合同
* 排期执行单（execute）--》排期执行审批模型，四方（销售主管、销售总经理、媒介、财务）顺序审批--》需求单合同

#### 项目中角色相关英文
* 总经理--general manager--GM
* 部门主管--Department Manager--DM
* 财务--Financial--DM


* 4A--customer4A
* 媒介主管--mediaGM--mediaAudit
* 销售普通--saler--
* 销售主管--salerDM--salerDMAudit
* 销售总经理--salerGM--salerGMAudit
* 财务主管--financialGM--salerGMAudit
* 法务主管--legalGM--legalGMAudit
* hr--hr

#### 待实现功能
* 流程的历史记录
* 流程触发时的短信
* 报表
