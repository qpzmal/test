package cn.advu.workflow.web.controller.workflow;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.service.base.BuyOrderService;
import cn.advu.workflow.web.vo.BaseBuyOrderVO;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<BaseBuyOrderVO> results = new ArrayList<BaseBuyOrderVO>();

        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(UserThreadLocalContext.getCurrentUser().getUserName());
        List<Task> tasks = taskQuery.list();

        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            BaseBuyOrderVO baseBuyOrderVO = new BaseBuyOrderVO();

            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if (processInstance == null) {
                continue;
            }
            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }
            LOGGER.info("businessKey:{}", businessKey);


            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderVO.setTask(task);
            baseBuyOrderVO.setBaseBuyOrder(baseBuyOrder);
            baseBuyOrderVO.setProcessInstance(processInstance);
            baseBuyOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));

            results.add(baseBuyOrderVO);

        }
        LOGGER.debug("results.size:{}", results.size());
        model.addAttribute("resultList", results);
        return "workflow/task_todoList";
    }



    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "running")
    public String runningList(HttpServletRequest request, Model model) {
        List<BaseBuyOrderVO> results = new ArrayList<BaseBuyOrderVO>();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY).active().orderByProcessInstanceId().desc();
//        List<ProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
        List<ProcessInstance> list = query.list();


        // 关联业务实体
        for (ProcessInstance processInstance : list) {
            BaseBuyOrderVO baseBuyOrderVO = new BaseBuyOrderVO();

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderVO.setBaseBuyOrder(baseBuyOrder);
            baseBuyOrderVO.setProcessInstance(processInstance);
            baseBuyOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            baseBuyOrderVO.setTask(tasks.get(0));

            results.add(baseBuyOrderVO);
        }
        LOGGER.debug("results.size:{}", results.size());
        model.addAttribute("resultList", results);
        return "workflow/task_runningList";
    }

    /**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}")
    public String claim(@PathVariable("id") String taskId, HttpSession session) {
        String userName = UserThreadLocalContext.getCurrentUser().getUserName();
        taskService.claim(taskId, userName);
        return "redirect:/workflow/task/todo";
    }

    /**
     * 完成任务
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "complete/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String complete(@PathVariable("id") String taskId) {
        try {
            taskService.complete(taskId);
            return "success";
        } catch (Exception e) {
            LOGGER.error("", e);
//            LOGGER.error("error on complete task {}, variables={}", new Object[]{taskId, var.getVariableMap(), e});
            return "error";
        }
    }

    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }

}
