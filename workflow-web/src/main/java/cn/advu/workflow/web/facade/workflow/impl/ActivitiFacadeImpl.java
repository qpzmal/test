package cn.advu.workflow.web.facade.workflow.impl;

import cn.advu.workflow.web.facade.workflow.ActivitiFacade;
import cn.advu.workflow.web.facade.workflow.vo.WorkflowVO;
import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Created by weiqz on 2017/6/5.
 */
@Service
public class ActivitiFacadeImpl implements ActivitiFacade {

    private static Logger LOGGER = LoggerFactory.getLogger(ActivitiFacadeImpl.class);

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private FormService formService;


    @Override
    public List<Deployment> findDeploymentList() {
        List<Deployment> list = repositoryService.createDeploymentQuery()//创建部署对象查询
                .orderByDeploymenTime().desc()//
                .list();
        return list;
    }

    @Override
    public List<ProcessDefinition> findProcessDefinitionList() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()//创建流程定义查询
                .orderByProcessDefinitionVersion().desc()//
                .list();
        return list;
    }

    @Override
    public void saveNewDeploye(CommonsMultipartFile file, String flowName) {
        try {
            //2：将File类型的文件转化成ZipInputStream流
//            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
            repositoryService.createDeployment()//创建部署对象
                    .name(flowName)//添加部署名称
                    .addZipInputStream(zipInputStream)//
                    .deploy();//完成部署
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
    }

    @Override
    public InputStream findImageInputStream(String deploymentId, String imageName) {
        return repositoryService.getResourceAsStream(deploymentId, imageName);
    }

    @Override
    public List<Task> findTaskListByName(String name) {
        List<Task> list = taskService.createTaskQuery()//
                .taskAssignee(name)//指定个人任务查询
                .orderByTaskCreateTime().desc()//
                .list();
        return list;
    }

    @Override
    public String findTaskFormKeyByTaskId(String taskId) {
        TaskFormData formData = formService.getTaskFormData(taskId);
        //获取Form key的值
        String url = formData.getFormKey();
        return url;
    }



    @Override
    public void saveStartProcess(WorkflowVO workflowVO) {
//        //1：获取请假单ID，使用请假单ID，查询请假单的对象LeaveBill
//        String id = workflowVO.getId();
//        LeaveBill leaveBill = leaveBillDao.findLeaveBillById(id);
//        //2：更新请假单的请假状态从0变成1（初始录入-->审核中）
//        leaveBill.setState(1);
//        //3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
//        String key = leaveBill.getClass().getSimpleName();
//        /*
//         * 4：从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人
//         * inputUser是流程变量的名称，
//         * 获取的办理人是流程变量的值
//         */
//        Map<String, Object> variables = new HashMap<String,Object>();
//        variables.put("inputUser", SessionContext.get().getName());//表示惟一用户
//        /*
//         * 5：	(1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
//         *      (2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
//         */
//        //格式：LeaveBill.id的形式（使用流程变量）
//        String objId = key+"."+id;
//        variables.put("objId", objId);
//        //6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
//        runtimeService.startProcessInstanceByKey(key,objId,variables);
    }
}
