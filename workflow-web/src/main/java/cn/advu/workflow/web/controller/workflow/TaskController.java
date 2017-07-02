package cn.advu.workflow.web.controller.workflow;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.BuyOrderService;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiqz on 2017/6/4.
 */
@Controller
@RequestMapping("/workflow/task")
public class TaskController {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private BuyOrderService buyOrderService;

    /**
     * 任务列表
     *
     * @param request
     */
    @RequestMapping(value = "todo")
    public String todoList(HttpServletRequest request, HttpSession session, Model model) {
        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned("u1");
        List<Task> tasks = taskQuery.list();

        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if (processInstance == null) {
                continue;
            }
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            LOGGER.info("businessKey:{}", businessKey);


//            Leave leave = leaveManager.getLeave(new Long(businessKey));
//            leave.setTask(task);
//            leave.setProcessInstance(processInstance);
//            leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
        }
        ResultJson<List<BaseBuyOrder>> buyOrderList = buyOrderService.findAll();
        model.addAttribute("buyOrderList", "buyOrderList");
        return "workflow/task_todoList";
    }



    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "running")
    public String runningList(HttpServletRequest request) {
        List<BaseBuyOrder> results = new ArrayList<BaseBuyOrder>();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY).active().orderByProcessInstanceId().desc();
//        List<ProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
        List<ProcessInstance> list = query.list();


        // 关联业务实体
        for (ProcessInstance processInstance : list) {
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
//            Leave leave = leaveManager.getLeave(new Long(businessKey));
//            leave.setProcessInstance(processInstance);
//            leave.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
//            results.add(leave);
//
//            // 设置当前任务信息
//            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
//            leave.setTask(tasks.get(0));
        }
        return "workflow/task_todoList";
    }
}
