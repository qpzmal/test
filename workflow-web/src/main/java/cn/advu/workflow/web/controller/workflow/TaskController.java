package cn.advu.workflow.web.controller.workflow;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.service.base.BuyOrderService;
import cn.advu.workflow.web.vo.BaseBuyOrderVO;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, List<BaseBuyOrderVO>> resultMap = new HashMap();

        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(UserThreadLocalContext.getCurrentUser().getUserName());
        List<Task> tasks = taskQuery.list();

        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            BaseBuyOrderVO baseBuyOrderVO = new BaseBuyOrderVO();

            String processInstanceId = task.getProcessInstanceId();
            LOGGER.info("processInstanceId:{}", processInstanceId);
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if (processInstance == null) {
                continue;
            }

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }
            LOGGER.info("businessKey:{}", businessKey);
            LOGGER.info("ProcessDefinitionKey:{}", processInstance.getProcessDefinitionKey());

            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderVO.setTask(task);
            baseBuyOrderVO.setBaseBuyOrder(baseBuyOrder);
            baseBuyOrderVO.setProcessInstance(processInstance);
            baseBuyOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));



            String mapKey = processInstance.getProcessDefinitionKey();
            if (resultMap.get(mapKey) == null) {
                List<BaseBuyOrderVO> results = new ArrayList<BaseBuyOrderVO>();
                results.add(baseBuyOrderVO);
                resultMap.put(mapKey, results);
            } else {
                List<BaseBuyOrderVO> results = resultMap.get(mapKey);
                results.add(baseBuyOrderVO);
            }

        }
//        LOGGER.debug("results.size:{}", results.size());
//        model.addAttribute("resultList", results);

        model.addAttribute("resultMap", resultMap);
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
     * 读取已结束中的流程
     *
     * @return
     */
    @RequestMapping(value = "finished")
    public String finished(HttpServletRequest request, Model model) {
        List<BaseBuyOrderVO> results = new ArrayList<BaseBuyOrderVO>();


        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY).finished().orderByProcessInstanceEndTime().desc();
//        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);

        List<HistoricProcessInstance> list = query.list();
        LOGGER.info("WORKFLOW_BUY:size{}", list.size());


        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {
            BaseBuyOrderVO baseBuyOrderVO = new BaseBuyOrderVO();

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderVO.setBaseBuyOrder(baseBuyOrder);
            baseBuyOrderVO.setHistoricProcessInstance(historicProcessInstance);
            baseBuyOrderVO.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(historicProcessInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            baseBuyOrderVO.setTask(tasks.get(0));

            results.add(baseBuyOrderVO);
        }
        LOGGER.debug("results.size:{}", results.size());
        model.addAttribute("resultList", results);
        return "workflow/task_finished";
    }

    /**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}")
    public String claim(@PathVariable("id") String taskId) {
        String userName = UserThreadLocalContext.getCurrentUser().getUserName();
        taskService.claim(taskId, userName);
        return "redirect:/workflow/task/todo";
    }

//    /**
//     * 打开办理任务页面
//     *
//     * @param pkey
//     * @param tkey
//     * @param taskId
//     * @return
//     */
//    @RequestMapping(value = "toAudit")
//    public String toAudit(String pkey, String tkey, String taskId, Model model) {
//        model.addAttribute("pkey", pkey);
//        model.addAttribute("tkey", tkey);
//        model.addAttribute("taskId", taskId);
//        return "/workflow/task_audit";
//    }

    /**
     * 完成任务
     *
     * @param pkey   流程key，例:buyOrder
     * @param tkey   审核任务key，例:mediaAudit
     * @param taskId 任务ID
     * @param result 审核结果true 通过/false 驳回
     * @param reason 驳回原因
     * @return
     */
    @RequestMapping(value = "complete")
    @ResponseBody
    public ResultJson complete(String pkey, String tkey, String taskId, String result, String reason) {
        ResultJson<Object> rj = new ResultJson<>();
        Boolean resultBoolean = Boolean.valueOf(result);

        Map<String, Object> paramMap = new HashMap<>();

        if ("true".equals(result)) {
        } else if ("false".equals(result))  {
            paramMap.put("reason", reason);
        } else {
            LOGGER.warn("错误的参数。result is :{}", result);
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "错误的参数!");
        }

        switch (tkey) {
            case WebConstants.Audit.MEDIA:
                paramMap.put(WebConstants.AuditPass.MEDIA, resultBoolean);
                break;
            case WebConstants.Audit.SALER_DM:
                paramMap.put(WebConstants.AuditPass.SALER_DM, resultBoolean);
                break;
            case WebConstants.Audit.SALER_GM:
                paramMap.put(WebConstants.AuditPass.SALER_GM, resultBoolean);
                break;
            case WebConstants.Audit.FINANCIAL_GM:
                paramMap.put(WebConstants.AuditPass.FINANCIAL_GM, resultBoolean);
                break;
            case WebConstants.Audit.LEGAL_GM:
                paramMap.put(WebConstants.AuditPass.LEGAL_GM, resultBoolean);
                break;
            case WebConstants.Audit.MODIFY_APPLY:
                paramMap.put(WebConstants.AuditPass.MODIFY_APPLY, resultBoolean);
                break;
            default:
                LOGGER.warn("错误的参数。tkey is :{},result is :{}", tkey, resultBoolean);
                return new ResultJson<>(WebConstants.OPERATION_FAILURE, "错误的参数!");
        }

        try {
            taskService.complete(taskId, paramMap);
            return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("", e);
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "系统错误!");
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
