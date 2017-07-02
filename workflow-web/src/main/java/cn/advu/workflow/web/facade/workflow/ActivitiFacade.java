package cn.advu.workflow.web.facade.workflow;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.facade.workflow.vo.WorkflowVO;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by weiqz on 2017/6/5.
 */
public interface ActivitiFacade {

    /**
     * 查询部署对象信息，对应表（act_re_deployment）
     * @return
     */
    List<Deployment> findDeploymentList();

    /**
     * 查询流程定义的信息，对应表（act_re_procdef）
     * @return
     */
    List<ProcessDefinition> findProcessDefinitionList();

    /**
     * 完成部署
     * @param file 页面上传递的zip格式的文件，格式是File类型
     * @param flowName 流程名称
     */
    void saveNewDeploye(CommonsMultipartFile file, String flowName);

    /**
     * 使用部署对象ID，删除流程定义
     * @param deploymentId 部署对象ID
     */
    void deleteProcessDefinitionByDeploymentId(String deploymentId);

    /**
     * 获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream1
     * @param deploymentId 部署对象ID
     * @param imageName 资源图片名称
     * @return
     */
    InputStream findImageInputStream(String deploymentId, String imageName);

    /**
     * 使用当前用户名查询正在执行的任务表，获取当前任务的集合List<Task>
     * @param name
     * @return
     */
    List<Task> findTaskListByName(String name);

    /**
     * 获取任务表单中任务节点的url连接
     * @param taskId
     * @return
     */
    String findTaskFormKeyByTaskId(String taskId);

    /**
     * 启动流程实例，让启动的流程实例关联业务
     * @param workflowVO
     */
    void saveStartProcess(WorkflowVO workflowVO);

    /**
     * 查询用户
     * @param loginName
     * @return
     */
    User queryUser(String loginName);

    /**
     * 建立用户
     * @param sysUser
     */
    void createUser(SysUser sysUser);


    /**
     * 删除用户
     * @param uid
     */
    void deleteUser(String uid);

    /**
     * 查询用户对应的组
     * @param loginName
     * @return
     */
    List<Group> queryGroup(String loginName);

    /**
     * 建立组
     * @param sysRole
     */
    void createGroup(SysRole sysRole);

    /**
     * 删除组
     * @param gid
     */
    void deleteGroup(String gid);


}
