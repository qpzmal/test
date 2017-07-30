package cn.advu.workflow.web.controller.workflow;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.service.base.BuyFrameService;
import cn.advu.workflow.web.service.base.BuyOrderService;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import cn.advu.workflow.web.service.base.SaleFrameService;
import cn.advu.workflow.web.vo.BaseBuyOrderFrameVO;
import cn.advu.workflow.web.vo.BaseBuyOrderVO;
import cn.advu.workflow.web.vo.BaseExecuteOrderFrameVO;
import cn.advu.workflow.web.vo.BaseExecuteOrderVO;
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

    @Autowired
    private BuyFrameService buyFrameService;

    @Autowired
    private ExecuteOrderService executeOrderService;

    @Autowired
    private SaleFrameService saleFrameService;

    @Autowired
    private SysUserRepo sysUserRepo;


    /**
     * 任务列表
     *
     * @param request
     */
    @RequestMapping(value = "todo")
    public String todoList(HttpServletRequest request, HttpSession session, Model model) {

        List<BaseBuyOrderVO> baseBuyOrderVOList = new ArrayList<>();
        List<BaseBuyOrderFrameVO> baseBuyOrderFrameVOList = new ArrayList<>();
        List<BaseExecuteOrderVO> baseExecuteOrderVOList = new ArrayList<>();
        List<BaseExecuteOrderVO> baseExecuteOrderExecuteVOList = new ArrayList<>();
        List<BaseExecuteOrderFrameVO> baseExecuteOrderFrameVOList = new ArrayList<>();

        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(UserThreadLocalContext.getCurrentUser().getUserName());
        List<Task> tasks = taskQuery.list();

        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
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


            SysUser dbUser = null;
            switch (processInstance.getProcessDefinitionKey()) {
                case WebConstants.WORKFLOW_BUY:
                    BaseBuyOrderVO baseBuyOrderVO = new BaseBuyOrderVO();
                    BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
                    baseBuyOrderVO.setTask(task);
                    baseBuyOrderVO.setBaseBuyOrder(baseBuyOrder);
                    baseBuyOrderVO.setProcessInstance(processInstance);
                    baseBuyOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
                    dbUser = sysUserRepo.findOne(baseBuyOrder.getUserId());
                    baseBuyOrder.setUserName(dbUser.getUserName());

                    baseBuyOrderVOList.add(baseBuyOrderVO);
                    break;

                case WebConstants.WORKFLOW_BUY_FRAME:
                    BaseBuyOrderFrameVO baseBuyOrderFrameVO = new BaseBuyOrderFrameVO();
                    BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(Integer.valueOf(businessKey)).getData();
                    baseBuyOrderFrameVO.setTask(task);
                    baseBuyOrderFrameVO.setBaseBuyOrderFrame(baseBuyOrderFrame);
                    baseBuyOrderFrameVO.setProcessInstance(processInstance);
                    baseBuyOrderFrameVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
                    dbUser = sysUserRepo.findOne(baseBuyOrderFrame.getUserId());
                    baseBuyOrderFrame.setUserName(dbUser.getUserName());

                    baseBuyOrderFrameVOList.add(baseBuyOrderFrameVO);
                    break;
                case WebConstants.WORKFLOW_SALE_ORDER:
                    BaseExecuteOrderVO baseExecuteOrderVO = new BaseExecuteOrderVO();
                    BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
                    baseExecuteOrderVO.setTask(task);
                    baseExecuteOrderVO.setBaseExecuteOrder(baseExecuteOrder);
                    baseExecuteOrderVO.setProcessInstance(processInstance);
                    baseExecuteOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
                    dbUser = sysUserRepo.findOne(baseExecuteOrder.getUserId());
                    baseExecuteOrder.setUserName(dbUser.getUserName());

                    baseExecuteOrderVOList.add(baseExecuteOrderVO);
                    break;
                case WebConstants.WORKFLOW_SALE_EXECUTE:
                    BaseExecuteOrderVO baseExecuteOrderExecuteVO = new BaseExecuteOrderVO();
                    BaseExecuteOrder baseExecuteOrderExecute = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
                    baseExecuteOrderExecuteVO.setTask(task);
                    baseExecuteOrderExecuteVO.setBaseExecuteOrder(baseExecuteOrderExecute);
                    baseExecuteOrderExecuteVO.setProcessInstance(processInstance);
                    baseExecuteOrderExecuteVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
                    dbUser = sysUserRepo.findOne(baseExecuteOrderExecute.getUserId());
                    baseExecuteOrderExecute.setUserName(dbUser.getUserName());

                    baseExecuteOrderExecuteVOList.add(baseExecuteOrderExecuteVO);
                    break;
                case WebConstants.WORKFLOW_SALE_FRAME:
                    BaseExecuteOrderFrameVO baseExecuteOrderFrameVO = new BaseExecuteOrderFrameVO();
                    BaseExecuteOrderFrame baseExecuteOrderFrame = saleFrameService.findById(Integer.valueOf(businessKey)).getData();
                    baseExecuteOrderFrameVO.setTask(task);
                    baseExecuteOrderFrameVO.setBaseExecuteOrderFrame(baseExecuteOrderFrame);
                    baseExecuteOrderFrameVO.setProcessInstance(processInstance);
                    baseExecuteOrderFrameVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
                    dbUser = sysUserRepo.findOne(baseExecuteOrderFrame.getUserId());
                    baseExecuteOrderFrame.setUserName(dbUser.getUserName());

                    baseExecuteOrderFrameVOList.add(baseExecuteOrderFrameVO);
                    break;
                default:
                    LOGGER.error("ProcessDefinitionKey is error.");
                    break;
            }

        }

        model.addAttribute("baseBuyOrderVOList", baseBuyOrderVOList);
        model.addAttribute("baseBuyOrderFrameVOList", baseBuyOrderFrameVOList);
        model.addAttribute("baseExecuteOrderVOList", baseExecuteOrderVOList);
        model.addAttribute("baseExecuteOrderExecuteVOList", baseExecuteOrderExecuteVOList);
        model.addAttribute("baseExecuteOrderFrameVOList", baseExecuteOrderFrameVOList);
        return "workflow/task_todoList";
    }



    /**
     * 读取运行中的流程实例
     *
     * @return
     */
    @RequestMapping(value = "running")
    public String runningList(HttpServletRequest request, Model model) {
        List<BaseBuyOrderVO> baseBuyOrderVOList = new ArrayList<>();
        List<BaseBuyOrderFrameVO> baseBuyOrderFrameVOList = new ArrayList<>();
        List<BaseExecuteOrderVO> baseExecuteOrderVOList = new ArrayList<>();
        List<BaseExecuteOrderVO> baseExecuteOrderExecuteVOList = new ArrayList<>();
        List<BaseExecuteOrderFrameVO> baseExecuteOrderFrameVOList = new ArrayList<>();


        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY).active().orderByProcessInstanceId().desc();
        List<ProcessInstance> list = query.list();

        SysUser dbUser = null;
        // 关联业务实体
        for (ProcessInstance processInstance : list) {

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrderVO baseBuyOrderVO = new BaseBuyOrderVO();
            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderVO.setBaseBuyOrder(baseBuyOrder);
            baseBuyOrderVO.setProcessInstance(processInstance);
            baseBuyOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseBuyOrder.getUserId());
            baseBuyOrder.setUserName(dbUser.getUserName());

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            baseBuyOrderVO.setTask(tasks.get(0));

            baseBuyOrderVOList.add(baseBuyOrderVO);
        }
        LOGGER.debug("baseBuyOrderVOList.size:{}", baseBuyOrderVOList.size());


        query = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY_FRAME).active().orderByProcessInstanceId().desc();
        list = query.list();
        // 关联业务实体
        for (ProcessInstance processInstance : list) {

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrderFrameVO baseBuyOrderFrameVO = new BaseBuyOrderFrameVO();
            BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderFrameVO.setBaseBuyOrderFrame(baseBuyOrderFrame);
            baseBuyOrderFrameVO.setProcessInstance(processInstance);
            baseBuyOrderFrameVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseBuyOrderFrame.getUserId());
            baseBuyOrderFrame.setUserName(dbUser.getUserName());

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            baseBuyOrderFrameVO.setTask(tasks.get(0));

            baseBuyOrderFrameVOList.add(baseBuyOrderFrameVO);
        }
        LOGGER.debug("baseBuyOrderFrameVOList.size:{}", baseBuyOrderFrameVOList.size());


        query = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_ORDER).active().orderByProcessInstanceId().desc();
        list = query.list();
        // 关联业务实体
        for (ProcessInstance processInstance : list) {

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrderVO baseExecuteOrderVO = new BaseExecuteOrderVO();
            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseExecuteOrderVO.setBaseExecuteOrder(baseExecuteOrder);
            baseExecuteOrderVO.setProcessInstance(processInstance);
            baseExecuteOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseExecuteOrder.getUserId());
            baseExecuteOrder.setUserName(dbUser.getUserName());

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            baseExecuteOrderVO.setTask(tasks.get(0));

            baseExecuteOrderVOList.add(baseExecuteOrderVO);
        }
        LOGGER.debug("baseExecuteOrderVOList.size:{}", baseExecuteOrderVOList.size());


        query = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_EXECUTE).active().orderByProcessInstanceId().desc();
        list = query.list();
        // 关联业务实体
        for (ProcessInstance processInstance : list) {

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrderVO baseExecuteOrderVO = new BaseExecuteOrderVO();
            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseExecuteOrderVO.setBaseExecuteOrder(baseExecuteOrder);
            baseExecuteOrderVO.setProcessInstance(processInstance);
            baseExecuteOrderVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseExecuteOrder.getUserId());
            baseExecuteOrder.setUserName(dbUser.getUserName());

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            baseExecuteOrderVO.setTask(tasks.get(0));

            baseExecuteOrderExecuteVOList.add(baseExecuteOrderVO);
        }
        LOGGER.debug("baseExecuteOrderExecuteVOList.size:{}", baseExecuteOrderExecuteVOList.size());



        query = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_FRAME).active().orderByProcessInstanceId().desc();
        list = query.list();
        // 关联业务实体
        for (ProcessInstance processInstance : list) {

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrderFrameVO baseExecuteOrderFrameVO = new BaseExecuteOrderFrameVO();
            BaseExecuteOrderFrame baseExecuteOrderFrame = saleFrameService.findById(Integer.valueOf(businessKey)).getData();
            baseExecuteOrderFrameVO.setBaseExecuteOrderFrame(baseExecuteOrderFrame);
            baseExecuteOrderFrameVO.setProcessInstance(processInstance);
            baseExecuteOrderFrameVO.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseExecuteOrderFrame.getUserId());
            baseExecuteOrderFrame.setUserName(dbUser.getUserName());


            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            baseExecuteOrderFrameVO.setTask(tasks.get(0));

            baseExecuteOrderFrameVOList.add(baseExecuteOrderFrameVO);
        }
        LOGGER.debug("baseExecuteOrderFrameVOList.size:{}", baseExecuteOrderFrameVOList.size());

        model.addAttribute("baseBuyOrderVOList", baseBuyOrderVOList);
        model.addAttribute("baseBuyOrderFrameVOList", baseBuyOrderFrameVOList);
        model.addAttribute("baseExecuteOrderVOList", baseExecuteOrderVOList);
        model.addAttribute("baseExecuteOrderFrameVOList", baseExecuteOrderFrameVOList);
        model.addAttribute("baseExecuteOrderExecuteVOList", baseExecuteOrderExecuteVOList);
        return "workflow/task_runningList";
    }



    /**
     * 读取已结束中的流程
     *
     * @return
     */
    @RequestMapping(value = "finished")
    public String finished(HttpServletRequest request, Model model) {
        List<BaseBuyOrderVO> baseBuyOrderVOList = new ArrayList<>();
        List<BaseBuyOrderFrameVO> baseBuyOrderFrameVOList = new ArrayList<>();
        List<BaseExecuteOrderVO> baseExecuteOrderVOList = new ArrayList<>();
        List<BaseExecuteOrderVO> baseExecuteOrderExecuteVOList = new ArrayList<>();
        List<BaseExecuteOrderFrameVO> baseExecuteOrderFrameVOList = new ArrayList<>();


        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY).finished().orderByProcessInstanceEndTime().desc();
//        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
        List<HistoricProcessInstance> list = query.list();
        LOGGER.info("WORKFLOW_BUY:size{}", list.size());

        SysUser dbUser = null;
        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }
            BaseBuyOrderVO baseBuyOrderVO = new BaseBuyOrderVO();

            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderVO.setBaseBuyOrder(baseBuyOrder);
            baseBuyOrderVO.setHistoricProcessInstance(historicProcessInstance);
            baseBuyOrderVO.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseBuyOrder.getUserId());
            baseBuyOrder.setUserName(dbUser.getUserName());

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(historicProcessInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            if (tasks != null && tasks.size() > 0) {
                baseBuyOrderVO.setTask(tasks.get(0));
            }
            baseBuyOrderVOList.add(baseBuyOrderVO);
        }
        LOGGER.debug("baseBuyOrderVOList.size:{}", baseBuyOrderVOList.size());




        query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY_FRAME).finished().orderByProcessInstanceEndTime().desc();
//        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
        list = query.list();
        LOGGER.info("WORKFLOW_BUY_FRAME:size{}", list.size());

        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrderFrameVO baseBuyOrderFrameVO = new BaseBuyOrderFrameVO();
            BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(Integer.valueOf(businessKey)).getData();
            baseBuyOrderFrameVO.setBaseBuyOrderFrame(baseBuyOrderFrame);
            baseBuyOrderFrameVO.setHistoricProcessInstance(historicProcessInstance);
            baseBuyOrderFrameVO.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseBuyOrderFrame.getUserId());
            baseBuyOrderFrame.setUserName(dbUser.getUserName());


            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(historicProcessInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            if (tasks != null && tasks.size() > 0) {
                baseBuyOrderFrameVO.setTask(tasks.get(0));
            }
            baseBuyOrderFrameVOList.add(baseBuyOrderFrameVO);
        }
        LOGGER.debug("baseBuyOrderFrameVOList.size:{}", baseBuyOrderFrameVOList.size());




        query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_ORDER).finished().orderByProcessInstanceEndTime().desc();
//        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
        list = query.list();
        LOGGER.info("WORKFLOW_SALE_ORDER:size{}", list.size());

        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }
            BaseExecuteOrderVO baseExecuteOrderVO = new BaseExecuteOrderVO();
            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseExecuteOrderVO.setBaseExecuteOrder(baseExecuteOrder);
            baseExecuteOrderVO.setHistoricProcessInstance(historicProcessInstance);
            baseExecuteOrderVO.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseExecuteOrder.getUserId());
            baseExecuteOrder.setUserName(dbUser.getUserName());


            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(historicProcessInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            if (tasks != null && tasks.size() > 0) {
                baseExecuteOrderVO.setTask(tasks.get(0));
            }
            baseExecuteOrderVOList.add(baseExecuteOrderVO);
        }
        LOGGER.debug("baseExecuteOrderVOList.size:{}", baseExecuteOrderVOList.size());




        query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_EXECUTE).finished().orderByProcessInstanceEndTime().desc();
//        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
        list = query.list();
        LOGGER.info("WORKFLOW_SALE_EXECUTE:size{}", list.size());

        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }
            BaseExecuteOrderVO baseExecuteOrderVO = new BaseExecuteOrderVO();
            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            baseExecuteOrderVO.setBaseExecuteOrder(baseExecuteOrder);
            baseExecuteOrderVO.setHistoricProcessInstance(historicProcessInstance);
            baseExecuteOrderVO.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseExecuteOrder.getUserId());
            baseExecuteOrder.setUserName(dbUser.getUserName());

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(historicProcessInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            if (tasks != null && tasks.size() > 0) {
                baseExecuteOrderVO.setTask(tasks.get(0));
            }
            baseExecuteOrderExecuteVOList.add(baseExecuteOrderVO);
        }
        LOGGER.debug("baseExecuteOrderExecuteVOList.size:{}", baseExecuteOrderExecuteVOList.size());




        query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_FRAME).finished().orderByProcessInstanceEndTime().desc();
//        List<HistoricProcessInstance> list = query.listPage(pageParams[0], pageParams[1]);
        list = query.list();
        LOGGER.info("WORKFLOW_SALE_FRAME:size{}", list.size());

        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : list) {

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrderFrameVO baseExecuteOrderFrameVO = new BaseExecuteOrderFrameVO();
            BaseExecuteOrderFrame baseExecuteOrderFrame = saleFrameService.findById(Integer.valueOf(businessKey)).getData();
            baseExecuteOrderFrameVO.setBaseExecuteOrderFrame(baseExecuteOrderFrame);
            baseExecuteOrderFrameVO.setHistoricProcessInstance(historicProcessInstance);
            baseExecuteOrderFrameVO.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
            dbUser = sysUserRepo.findOne(baseExecuteOrderFrame.getUserId());
            baseExecuteOrderFrame.setUserName(dbUser.getUserName());

            // 设置当前任务信息
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(historicProcessInstance.getId()).active().orderByTaskCreateTime().desc().listPage(0, 1);
            if (tasks != null && tasks.size() > 0) {
                baseExecuteOrderFrameVO.setTask(tasks.get(0));
            }

            baseExecuteOrderFrameVOList.add(baseExecuteOrderFrameVO);
        }
        LOGGER.debug("baseExecuteOrderFrameVOList.size:{}", baseExecuteOrderFrameVOList.size());

        model.addAttribute("baseBuyOrderVOList", baseBuyOrderVOList);
        model.addAttribute("baseBuyOrderFrameVOList", baseBuyOrderFrameVOList);
        model.addAttribute("baseExecuteOrderVOList", baseExecuteOrderVOList);
        model.addAttribute("baseExecuteOrderFrameVOList", baseExecuteOrderFrameVOList);
        model.addAttribute("baseExecuteOrderExecuteVOList", baseExecuteOrderExecuteVOList);
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
     * @param pid    流程定义ID
     * @param bizId  业务表ID
     * @param taskId 任务ID
     * @param result 审核结果true 通过/false 驳回
     * @param reason 驳回原因
     * @return
     */
    @RequestMapping(value = "complete")
    @ResponseBody
    public ResultJson complete(String pkey, String tkey, String pid, String bizId, String taskId, String result, String reason) {
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
            if (WebConstants.Audit.FINANCIAL_GM.equals(tkey) || WebConstants.Audit.LEGAL_GM.equals(tkey)) {
                HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(pkey).processInstanceId(pid).finished().orderByProcessInstanceEndTime().desc();
                List<HistoricProcessInstance> list = query.list();
                LOGGER.info("processInstanceId:{}", pid);
                LOGGER.info("HistoricProcessInstance-listSize:{}", list.size());
                if (list.size() > 0) {

                    switch (pkey) {
                        case WebConstants.WORKFLOW_BUY:
                            BaseBuyOrder baseBuyOrder = new BaseBuyOrder();
                            baseBuyOrder.setId(Integer.valueOf(bizId));
                            baseBuyOrder.setStatus((byte)1);
                            buyOrderService.updateSelective(baseBuyOrder).getData();
                            break;

                        case WebConstants.WORKFLOW_BUY_FRAME:
                            BaseBuyOrderFrame baseBuyOrderFrame = new BaseBuyOrderFrame();
                            baseBuyOrderFrame.setId(Integer.valueOf(bizId));
                            baseBuyOrderFrame.setStatus((byte)1);
                            buyFrameService.updateSelective(baseBuyOrderFrame).getData();
                            break;

                        case WebConstants.WORKFLOW_SALE_ORDER:
                            BaseExecuteOrder baseExecuteOrder = new BaseExecuteOrder();
                            baseExecuteOrder.setId(Integer.valueOf(bizId));
                            baseExecuteOrder.setStatus((byte)1);
                            executeOrderService.updateSelective(baseExecuteOrder).getData();
                            break;

                        case WebConstants.WORKFLOW_SALE_FRAME:
                            BaseExecuteOrderFrame baseExecuteOrderFrame = new BaseExecuteOrderFrame();
                            baseExecuteOrderFrame.setId(Integer.valueOf(bizId));
                            baseExecuteOrderFrame.setStatus((byte)1);
                            saleFrameService.updateSelective(baseExecuteOrderFrame).getData();
                            break;

                        case WebConstants.WORKFLOW_SALE_EXECUTE:
                            BaseExecuteOrder baseExecuteOrderExecute = new BaseExecuteOrder();
                            baseExecuteOrderExecute.setId(Integer.valueOf(bizId));
                            baseExecuteOrderExecute.setStatus((byte)2);
                            executeOrderService.updateSelective(baseExecuteOrderExecute).getData();
                            break;
                        default:
                            LOGGER.error("pkey is error.");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "系统错误!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
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
