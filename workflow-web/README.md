开发文档
=

##BUG列表
1. 用户管理
    1. ~~用户密码>6位~~                              
    1. ~~从页面删除部门的输入~~                         
    1. ~~角色的位置放到启用前~~                      
    1. ~~用户角色没有保存成功（新增）~~                  
    1. ~~增加角色时，同步到工作流用户组  ~~              
    
1. ~~通讯录中公司和部门加上必须校验~~                      （未复现）
    1. ~~通讯录列表选择部门后，点添加，没有带部门到新增页面~~ 
    1. 从新增页面取消后，列表页部门不可用   ----非必要bug
    
1. 新增部门，检查所属区域的错误消息，所属公司-》所属部门  --非必要bug
    1. ~~部门列表的公司树状列表，点+会关闭树~~              
    
1. 区域管理列表的清空，没有清空树的选中状态 --非bug

1. ~~需求单里项目补充编码不应该是必须填~~ 
    1. ~~项目编号隐藏~~
    1. 销售员初次填的时候，投放地区等没有控制住----没有复现
    1. ~~销售单更新时，person没有判断存在，会出空指针异常~~ 
    1. 是否加数据权限控制 ---待定
    
1. 运行中流程，第一列名字改成 单据名称、 申请人由ID改成名称，申请时间	开始时间	结束时间，任务创建时间，当前处理人"-"
    1. ~~当前节点-流程信息说明：有代码。~~
    1. 关闭按钮显示不正常
    1. 申请人是自己的流程，自己只要在节点中就可以看
    
    
1. 排期单
    1. ~~销售负责人变成了当前用户~~
    1. 排期单不应该能删除
    
1. 监测
    1.预置三条数据
    
1. 采购框架
    1. ~~去掉项目编号
    1. ~~项目补充编号非必填
    1. 项目总金额没有校验
    1. 回款比例也是财务填写吧
    1. 采购框架的CPM没有查询出来
    1. 已结束流程里没有表的抬头

1. 采购单
    1. 框架复制的场景下，区域选择后，没有项目负责人列表
    1. CPM没有复制？
    
1. 需求框架
    1. ~~去掉项目编号(需求复制)~~
    1. ~~项目补充编号非必填(需求复制)~~
    1. 所有金额项目的检查
    1. 收款负责人是不是只保留名称即可
    1. ~~项目负责人没有保存，区域选择后，没有项目负责人列表~~

1. 需求复制
    1. ~~CPM复制有问题，条数重复。且有id~~
    1. ~~有代理公司，但报代理公司为空~~
    
1. CPM的类型影响

    
    
    
    


    

## 修改
1. 项目编号、项目补充编号隐藏
1. 输入项目校验
    1. 项目负责人关联person表，去掉项目负责人的【ID】
    1. 长度校验（不能超过DB）
    1. 金额只能数字
    1. 比例（有placehold提示和示例，比如10%）
    1. 账期（纯数字）
1. 需求单、需求框架，需要增加媒介普通填写的流程
1. 驳回流程
1. 流程中的时间等字段，补充完整
1. 报表--历史订单--客户汇总--增加全部
1. 当前用户名
1. 菜单栏去掉无用项目（测试、报表部分）
1. 文案--删除【按ESC关闭】



##TODO列表（必做）
1. 报表
    1. 销售报表分析系统--2）未来订单--b）无排期
    1. 资源采购分析系统（全部）
    1. 分公司财务分析系统--1）
    1. 分公司财务分析系统--2）--?缺少季度预算
    1. 客户分析系统--1）--？SQL是否正确
    1. 客户分析系统--2）--？SQL是否正确
1. 首页面板--进行中，还没取数据
1. 流程表中加入wf_step字段--未开始
1. 计算器--20170728已完成
1. 通知发送记录
1. 日志--进行中
1. 工作流历史记录的查看（在流程审批--已结束流程作为入口）
1. 流程触发时的短信、邮件
1. 其他工作流（盖章收款等）
1. 定时任务，提前七天收账
1. 图像上传、修改LOGO
1. FF浏览器使用测试
1. 销售，媒介，财务依次填写内容的流程
1. 单据能够导成EXCEL
1. ====================
1. 列表页中的删除按钮，没有触发删除事件
1. 更新页面如果数据库里该项没有值，则显示空。
1. 启用停用，做成checkbox样式
1. UserController里面的index_content和list都是干什么用的？不是我加的
1. 角色页面，checkbox的展示错误



##待确认
1. ====================
1. 采购框架、销售框架



##BUG列表
1. 用户按钮，只有用户自己才能修改和发起流程。
1. 【客户需求单】--对公返点--填【10】报错：# Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Out of range value for column 'public_rebate' at row 1
1. ***** Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column 'person_id' cannot be null
***** at cn.advu.workflow.repo.fcf_vu.impl.BaseBuyOrderRepoImpl.update(BaseBuyOrderRepoImpl.java:61)
1. 更新改为动态更新
1. 给用户赋予admin权限，无法看到日志菜单
1. 去掉拦截器中最后登录时间的校验




##TODO优化列表（选作）
1. 用户修改密码
1. 报表导出excel
1. 翻页
1. 查询页面重置功能
1. 手机号DES加密
1. sys_user表login_fail_count字段的使用（记录错误的登录次数）
1. 连接池使用druid？现在使用的是boneCP
1. 登录认证使用shiro?
1. 饼图总数（标题下面的subtext）



##交付前要做的
1. 建立分支
1. 整理代码：
    1. 删除wv等字符
    1. 删除无用注释
    1. 删除无用代码
    1. 删除zi-han代码
1. aa


## 项目开发
1. 模版主页：http://www.zi-han.net/theme/hplus/
1. 导入本地patchca-0.5.0.jar包到maven库
> mvn install:install-file  -Dfile=/Users/wangry/WorkFlower/patchca-0.5.0.jar  -DgroupId=org.patchca  -DartifactId=patchca -Dversion=0.5.0 -Dpackaging=jar



# 流程图和项目之间的关系
1. 图中的 **流程标识** 需要和流程相关的Controller的URI向匹配。比如采购定价单中的URI是/buyOrder/xxxx，因此流程标识应该设置为buyOrder，这样在审核列表页面中才能根据此地址，定位到相应的参考页面。

####相关插件版本：
1. layer--3.0.3
官网：http://layer.layui.com/
1. Font Awesome--4.4.0
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
>       <a href="javascript:;" class="btn btn-info btn-sm button_view_pd"><i class="fa fa-pencil "></i> 修改 </a>
>       <a href="javascript:;" class="btn btn-danger btn-sm button_view_pd"><i class="fa fa-trash "></i> 删除 </a>
* 新增 btn-primary --> fa-plus
* 修改 btn-info --> fa-pencil
* 删除 btn-danger --> fa-trash
* 搜索 btn-info --> fa-search
* 查看 btn-default --> fa-photo 或 fa-search
* 返回 btn-default --> fa-reply

3. 示例部分代码有如下注释
* （示例：XXXX）


####短信模版
1. 您有由%s发起的%s申请带待处理，请及时处理。
1. 您的%s申请由%s驳回，请查看原因及时修改。
1. 您的%s申请已通过，请及时查看。

#### 流程相关定义
* 采购定价单（buy_frame）--》年度采购框架合同模型，三方（媒介、销售总经理、财务）顺序审批--》年度采购框架合同
* 采购定价单（buy..buyOrder）--》CPM采购成本单模型，四方（媒介、销售总经理、财务、法务）顺序审批--》CPM采购成本单
* 客户需求单（sale_frame）--》年度需求单审批模型，三方（销售总经理、媒介、财务）顺序审批--》年度需求单框架合同
* 客户需求单（sale..executeOrder）--》合同审批模型，五方（销售主管、销售总经理、媒介、财务、法务）顺序审批--》需求单合同
* 排期执行单（execute）--》排期执行审批模型，四方（销售主管、销售总经理、媒介、财务）顺序审批--》需求单合同

#### 项目中角色相关英文
* 总经理--general manager--GM
* 部门主管--Department Manager--DM
* 财务--Financial--DM


* 4A--customer4A
* 媒介主管--mediaGM--mediaAudit--${mediaGMPass}--${!mediaGMPass}
* 销售普通--saler--
* 销售主管--salerDM--salerDMAudit--${salerDMPass}--${!salerDMPass}
* 销售总经理--salerGM--salerGMAudit--${salerGMPass}--${!salerGMPass}
* 财务主管--financialGM--financialGMAudit--${financialGMPass}--${!financialGMPass}
* 法务主管--legalGM--legalGMAudit--${legalGMPass}--${!legalGMPass}
* hr--hr
* 调整申请-----------modifyApply--${reApply}--${!reApply}


#### mybatis相关mapper配置
*  keyColumn="id" keyProperty="id"  useGeneratedKeys="true"

#### 测试环境
1. 部署地址：/data/web/tomcat-8.5.16-8091-8092/webapps/ROOT