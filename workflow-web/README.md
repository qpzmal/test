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

