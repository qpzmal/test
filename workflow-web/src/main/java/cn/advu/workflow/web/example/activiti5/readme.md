TODO列表
1、列表页中的删除按钮，没有触发删除事件
2、更新页面如果数据库里该项没有值，则显示空。
3、启用停用，做成checkbox样式
4、UserController里面的index_content和list都是干什么用的？不是我加的
5、角色页面，checkbox的展示错误
6、



整合activiti5笔记
=


### 参考网文：
* [Activiti工作流学习入门其一（渣渣小总结）](http://www.cnblogs.com/fly-piglet/p/6014270.html)
* [Quick Start Guide](https://www.activiti.org/quick-start)
* [Activiti工作流引擎使用](http://www.open-open.com/lib/view/open1350460225367.html)
* [Activiti工作流的应用示例](http://www.cnblogs.com/hellowood23/p/5437909.html)
* [工作流Activiti5入门（上）](http://www.bridgeli.cn/archives/197)
* 这个系列等于是传智播客activiti视频的图文版[Activiti系列（一）——最简单的Activiti程序](http://blog.csdn.net/qiumuxia0921/article/details/50988487)
* [Activiti系列（二）--审批流程](http://blog.csdn.net/qiumuxia0921/article/details/50993365)
* [Activiti系列（三）——设置监听器指定Assignee](http://blog.csdn.net/qiumuxia0921/article/details/50993703)
* [Activiti系列(四)——设计指定Assignee+审批](http://blog.csdn.net/qiumuxia0921/article/details/50993888)
* [activiti并发多实例子流程任务处理 ](http://www.cnblogs.com/mycifeng/p/5309150.html)
* [Activiti进阶（六）——连线（SequenceFlow）](http://blog.csdn.net/zjx86320/article/details/50364129)


### 整合spring4和activiti5
*  配置maven(web项目的pom.xml)，引入关联jar包
>         <dependency>
>              <groupId>org.activiti</groupId>
>              <artifactId>activiti-engine</artifactId>
>          </dependency>
>          <dependency>
>              <groupId>org.activiti</groupId>
>              <artifactId>activiti-spring</artifactId>
>          </dependency>
>          <dependency>
>              <groupId>org.activiti</groupId>
>              <artifactId>activiti-bpmn-model</artifactId>
>          </dependency>
>          <dependency>
>              <groupId>org.activiti</groupId>
>              <artifactId>activiti-image-generator</artifactId>
>          </dependency>
>          <dependency>
>              <groupId>org.activiti</groupId>
>              <artifactId>activiti-process-validation</artifactId>
>          </dependency>
>          <dependency>
>              <groupId>org.activiti</groupId>
>              <artifactId>activiti-bpmn-converter</artifactId>
>          </dependency>

* 配置activiti相关数据库(application-activiti.xml)，其中workflow_dataSource和workflow_transactionManager是在application-db.xml配置的数据源和事务管理
>     <!-- spring负责创建流程引擎的配置文件 -->
>      <bean id="processEngineConfiguration" class="org.activiti.spring.SpringProcessEngineConfiguration">
>          <!-- 数据源 -->
>          <property name="dataSource" ref="workflow_dataSource" />
>          <!-- 配置事务管理器，统一事务 -->
>          <property name="transactionManager" ref="workflow_transactionManager" />
>          <!-- 设置建表策略，如果没有表，自动创建表 -->
>          <property name="databaseSchemaUpdate" value="true" />
>          <!-- 是否启动jobExecutor -->
>          <!-- <property name="jobExecutorActivate"value="false" /> -->
>      </bean>
>
>      <!-- 创建流程引擎对象 -->
>      <bean id="processEngine" class="org.activiti.spring.ProcessEngineFactoryBean">
>          <property name="processEngineConfiguration" ref="processEngineConfiguration" />
>      </bean>
>
>      <!-- 创建activiti提供的各种服务 -->
>      <!-- 工作流仓储服务 -->
>      <bean id="repositoryService" factory-bean="processEngine" factory-method="getRepositoryService" />
>      <!-- 工作流运行服务 -->
>      <bean id="runtimeService" factory-bean="processEngine" factory-method="getRuntimeService" />
>      <!-- 工作流任务服务-->
>      <bean id="taskService" factory-bean="processEngine" factory-method="getTaskService" />
>      <!-- 工作流历史数据服务-->
>      <bean id="historyService" factory-bean="processEngine" factory-method="getHistoryService" />
>      <!-- 工作流管理服务-->
>      <bean id="managementService" factory-bean="processEngine" factory-method="getManagementService" />
>      <bean id="formService" factory-bean="processEngine" factory-method="getFormService" />
>      <!-- 工作流唯一服务 -->
>      <!-- <bean id="IdentityService"factory-bean="processEngine"factory-method="getIdentityService"/> -->

* 启动web项目，会自动执行activiti相关脚本，在数据库中建立25张表
* eclipse下载Activiti BPMN designer 地址 http://activiti.org/designer/update

### activiti表结构
部署对象和流程相关的表
>      act_re_deployment -- 部署对象表
>      act_re_procdef -- 流程定义表
>      act_ge_bytearray -- 资源文件表
>      act_ge_property -- 主键生成策略表

### 使用Activiti Modeler在线设计流程器
官网（www.activiti.org）无法打开，最后在CSDN上下载的activiti-5.22.0.zip
官网github:https://github.com/Activiti/Activiti
参考网文(5.17版，不太一样)：http://www.kafeitu.me/activiti/2013/03/10/integrate-activiti-modeler.html
（5.22版）http://blog.csdn.net/u010411264/article/details/71480354

----
###5.22版的Activiti Modeler配置过程记录如下，
咖啡兔的配置方案应该是旧版的，一些文件名字都对应不上了，参考了几篇文章，最后流程记录如下：
* github上下载Activiti 源代码：Activiti-master.zip
* 从CSDN上找到了activiti-5.22.0.zip，是文档、例子、源码等。官网www.activiti.org无法打开。
* 解压Activiti-master.zip，找到modules\activiti-webapp-explorer2\src\main\webapp下的diagram-viewer、editor-app、modeler.html，
将其加到项目的webapp中。（这一步主要是Activiti Modeler的前端页面）
* 解压activiti-5.22.0.zip，找到wars在的activiti-explorer.war，使用解压工具（或放入tomcat下）将其解压，
找activiti-explorer\WEB-INF\lib，拷贝所有jar包到的lib下。
这步没有做，用了以下的配置代替（版本都在主pom文件当中设置了，以下配置没有体现）
>        <dependency>
>            <groupId>org.activiti</groupId>
>            <artifactId>activiti-explorer</artifactId>
>            <exclusions>
>                <exclusion>
>                    <groupId>com.vaadin</groupId>
>                    <artifactId>vaadin</artifactId>
>                </exclusion>
>                <exclusion>
>                    <groupId>org.vaadin.addons</groupId>
>                    <artifactId>dcharts-widget</artifactId>
>                </exclusion>
>                <exclusion>
>                    <groupId>org.activiti</groupId>
>                    <artifactId>activiti-simple-workflow</artifactId>
>                </exclusion>
>            </exclusions>
>        </dependency>
>        <dependency>
>            <groupId>org.activiti</groupId>
>            <artifactId>activiti-modeler</artifactId>
>        </dependency>

* 给Activiti Modeler做配置文件application-activiti.xml
* 在spring的控制文件application.xml中，引入application-activiti-modeler.xml
>       <import resource="classpath*:spring/application-activiti.xml">
* 在application-activiti-modeler.xml中,加入如下配置
>        <!-- 扫描activiti在线编辑器的跳转@RestController -->
>        <context:component-scan base-package="cn.advu.workflow.web.common.workflow.*" use-default-filters="false">
>           <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller" />
>        </context:component-scan>

* 在如下两个路径中，任意一个都可以找到stencilset.json文件，将其加入到项目的配置文件里面 <br/>
\Activiti-master\modules\activiti-webapp-explorer2\src\main\resources\  <br/>
\activiti-explorer\WEB-INF\classes\
* 在\activiti-5.22.0\libs\中找到activiti-modeler-5.22.0-sources.jar，解压后，
在\org\activiti\rest\editor\model\中拷贝ModelEditorJsonRestResource.java，和ModelSaveRestResource.java两个文件，
到\workflow-web\src\main\java\cn\advu\workflow\web\common路径下;
在\org\activiti\rest\editor\main\中拷贝StencilsetRestResource.java也到上述路径下。
######StencilsetRestResource.java 用来加载并返回stencilset.json文件的内容
######ModelEditorJsonRestResource.java 这个就是访问的那个url了，通过部署的modelId模型编辑源
######ModelSaveRestResource.java 这个是用来保存编辑的模型
然后对上述三个类的注解都加上service，如在StencilsetRestResource中@RequestMapping(value="/service/editor/stencilset"


* 在editor-app中找到app-cfg.js文件，将'contextRoot' : '/activiti-explorer/service',修改为本项目的路径。如下：
ACTIVITI.CONFIG = {
	'contextRoot' : '/service',
};
######此处的'contextRoot' : '/service', 中，地址是和项目路径相对应的，我的项目是按照如下配置的
>            <plugin>
>                <groupId>org.mortbay.jetty</groupId>
>                <artifactId>maven-jetty-plugin</artifactId>
>                <version>6.1.26</version>
>                <configuration>
>                    <scanIntervalSeconds>3</scanIntervalSeconds>
>                    <webDefaultXml>src/main/resources/webdefault.xml</webDefaultXml>
>                    <connectors>
>                        <connector implementation="org.mortbay.jetty.nio.SelectChannelConnector">
>                            <port>9000</port>
>                            <host>127.0.0.1</host>
>                            <!--<host>192.168.1.166</host>-->
>                        </connector>
>                    </connectors>
>                    <contextPath>/</contextPath>
>                </configuration>
>            </plugin>
######由于contextPath配置的是/，因此项目访问路径为http://127.0.0.1:9000/modeler.html?modelId=12540。如果没有contextPath这行，contextRoot就应该配置为'/项目名/service'，访问路径为http://127.0.0.1:9000/项目名/modeler.html?modelId=12540

最后，由于使用maven部署，要将json格式作为资源部署，否则stencilset.json无法打成包
>       <build>
>           <sourceDirectory>src/main/java</sourceDirectory>
>           <resources>
>               <resource>
>                   <directory>src/main/resources</directory>
>                   <filtering>true</filtering>
>                   <includes>
>                       <include>**/*.properties</include>
>                       <include>**/*.xml</include>
>                   </includes>
>               </resource>
>               <resource>
>                   <directory>src/main/resources</directory>
>                   <filtering>false</filtering>
>                   <includes>
>                       <include>**/*.xlsx</include>
>                       <include>**/*.xls</include>
>                       <include>**/*.bpmn</include>
>                       <include>**/*.png</include>
>                       <include>**/*.json</include>
>                   </includes>
>               </resource>
>           </resources>
>       </build>