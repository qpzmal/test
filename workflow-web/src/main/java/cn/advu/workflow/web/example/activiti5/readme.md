整合activiti5笔记
=

#####
1. 参考网文：
    * [Activiti工作流学习入门其一（渣渣小总结）](http://www.cnblogs.com/fly-piglet/p/6014270.html)
    * [Quick Start Guide](https://www.activiti.org/quick-start)
    * [Activiti工作流引擎使用](http://www.open-open.com/lib/view/open1350460225367.html)
    * [Activiti工作流的应用示例](http://www.cnblogs.com/hellowood23/p/5437909.html)
    * [工作流Activiti5入门（上）](http://www.bridgeli.cn/archives/197)

2. 整合spring4和activiti5
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
3. activiti表结构
    部署对象和流程相关的表
    >      act_re_deployment -- 部署对象表
    >      act_re_procdef -- 流程定义表
    >      act_ge_bytearray -- 资源文件表
    >      act_ge_property -- 主键生成策略表
