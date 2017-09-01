开发文档
=

## 测试环境配置路径
1. nginx    /usr/local/webserver/nginx/conf/nginx.conf
    


##TODO列表（必做）
    1.预览图片，看能否再大点显示
    1.驳回时的流程ID
	6.排期单审核时，有必填校验
	8.销售人员有很多个，他们登陆上来，每个销售只能看到自己的单子
	9.销售主管只能看到自己公司的销售单子；
	10.媒介也只能看到自己公司的单子
	1.并行网关的红框
	4.设置LOGO入口；
	11.然后你再改改首页，加个logo
	2.剩下的几个报表；
	12.短信切换；
	3.短信和邮件的功能放开；
    1.需求单、框架合同  中的投放地区编号、投放日期---销售可写
	7.sys_user和base_person表同步，屏蔽person的入口；
    1.成本计算，加权

1. 报表
    1. 销售报表分析系统--2）未来订单--b）无排期
    1. 资源采购分析系统（全部）
    1. 分公司财务分析系统--1）
    1. 分公司财务分析系统--2）--?缺少季度预算
    1. 客户分析系统--1）--？SQL是否正确
    1. 客户分析系统--2）--？SQL是否正确
1. 首页面板--进行中，还没取数据
1. 流程表中加入wf_step字段--20170805：不加了
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
2. 每个表有以下字段，字段为NOT NULL 并设置默认值
    >     `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    >     `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 ，0正常，1删除',
    >     `item_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 ，0正常；1停用',
    >     `creator_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
    >     `updater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新者ID',
    >     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    >     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    >     `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',

1. 按钮颜色以及对应图案
    >       <a href="javascript:;" class="btn btn-info btn-sm button_view_pd"><i class="fa fa-pencil "></i> 修改 </a>
    >       <a href="javascript:;" class="btn btn-danger btn-sm button_view_pd"><i class="fa fa-trash "></i> 删除 </a>
    * 新增 btn-primary --> fa-plus
    * 修改 btn-info --> fa-pencil
    * 删除 btn-danger --> fa-trash
    * 搜索 btn-info --> fa-search
    * 查看 btn-default --> fa-photo 或 fa-search
    * 返回 btn-default --> fa-reply

1. 示例部分代码有如下注释
    * （示例：XXXX）

1. 特殊颜色文字：
    1. 必须输入项目：<label class="text-danger">(*)</label>

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


开发文档
=

## 测试环境配置路径
1. nginx    /usr/local/webserver/nginx/conf/nginx.conf
1. 部署地址：/data/web/tomcat-8.5.16-8091-8092/webapps/ROOT



## 项目开发
1. 导入本地patchca-0.5.0.jar包到maven库
> mvn install:install-file  -Dfile=/Users/wangry/WorkFlower/patchca-0.5.0.jar  -DgroupId=org.patchca  -DartifactId=patchca -Dversion=0.5.0 -Dpackaging=jar

#### 前端相关插件版本：
1. 弹出层：layer--3.0.3  官网：http://layer.layui.com/
1. 字体图标：Font Awesome--4.4.0  图标示例：http://code.zoomla.cn/boot/font.html
1. 报表：echart  官网：http://echarts.baidu.com/
1. 表单验证：jquery.validate.js 官网：http://jqueryvalidation.org/
1. 日期选择：layerDate  官网：http://sentsin.com/layui/laydate/
1. 下拉列表： http://harvesthq.github.io/chosen/
1. 文件上传：Web Uploader  官网：http://fex.baidu.com/webuploader/
1. 相册插件（图片预览）：blueimp Gallery 官网：https://github.com/blueimp/Gallery
1. 表格：Bootstrap table 官网：http://bootstrap-table.wenzhixin.net.cn/zh-cn

#### 工作流相关资料：
1. activiti https://github.com/Activiti/Activiti
1. 国内参考关键词：咖啡兔


#### 项目部署：
1. 环境：
    1. jdk-8u131-linux-x64
    1. tomcat-8.5.16
    1. nginx
1. maven编译项目，打成war包；
1. 设置java字体，支持工作流的图形展示，防止乱码：
    1. 复制doc/fonts下的文件（msyhbd.ttf、msyh.ttf）到【/data/jdk1.8.0_131/jre/lib/fonts/fallback】目录下（如果fallback文件夹不存在，需要手动创建）
    1. 修改/data/jdk1.8.0_131/jre/lib/fonts文件夹下fonts.dir文件，加入以下内容：
    ```shell
    msyh.ttf -microsoft-microsoft yahei-medium-r-normal--0-0-0-0-p-0-iso10646-1
    msyhbd.ttf -microsoft-microsoft yahei-bold-r-normal--0-0-0-0-p-0-iso10646-1
    ```
1. 建立数据库
    1. 执行doc/db/create.sql文件
    1. create.sql文件中没有activiti相关的25张表的建表语句，项目启动时会自动创建
1. tomcat
    1. 启动tomcat，确认项目是否正常启动
1. nginx
    1. 配置动静分离，用于图片上传、预览
        /usr/local/webserver/nginx/conf/nginx.conf
        ```conf
            upstream advu {
                    server   127.0.0.1:8091 weight=1;
            }
            server {
                listen 80;
                server_name 114.113.126.212;

                location ~ /workflow-admin/ {
                        root /data/web/upload;
                }
                location / {
                        proxy_pass http://advu;
                        proxy_redirect off;
                        proxy_set_header Host $host;
                        proxy_set_header X-Real-IP $remote_addr;
                        proxy_set_header X-Forwarded_For proxy_add_x_forwarded_for;
                        proxy_next_upstream http_502 http_504 http_404 error timeout invalid_header;
                }
            }
        ```

