package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.domain.golbal.Page;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.common.tool.ActivitiTool;
import cn.advu.workflow.web.dto.system.UserRole;
import cn.advu.workflow.web.manager.PersonMananger;
import cn.advu.workflow.web.service.base.*;
import cn.advu.workflow.web.service.system.LoginService;
import cn.advu.workflow.web.service.system.SysRoleService;
import cn.advu.workflow.web.service.system.SysUserService;
import cn.advu.workflow.web.vo.DataBoardVO;
import cn.advu.workflow.web.vo.MenuVO;
import com.google.gson.Gson;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    private LoginService loginService;

    @Autowired
    PersonService personService;

    @Autowired
    PersonMananger personMananger;

    @Autowired
    private BuyOrderService buyOrderService;

    @Autowired
    private BuyFrameService buyFrameService;

    @Autowired
    private ExecuteOrderService executeOrderService;

    @Autowired
    private SaleFrameService saleFrameService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    protected HistoryService historyService;

    /**
     * 获取用户可用菜单信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMenus")
    public MenuVO getMenus(HttpServletRequest request){
//        String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);
//
//        if (StringUtils.isBlank(loginCookie)) {
//            LOGGER.warn("cookie is null");
//            return null;
//        }
//
//        LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);

        LoginUser loginUser = UserThreadLocalContext.getCurrentUser();
        if(loginUser != null ){
            // 获取用户菜单信息
            loginService.queryUserFunction(loginUser);

            loginService.queryUserRole(loginUser);

        } else {
            LOGGER.warn("loginuser is null.");
        }

        MenuVO menu = new MenuVO();
        menu.setWorkflowStart(new ArrayList<String>());
        menu.setWorkflowAudit(new ArrayList<String>());
        menu.setReportInfo(new ArrayList<String>());
        menu.setBaseInfo(new ArrayList<String>());
        menu.setSystemInfo(new ArrayList<String>());
        for (String functionId : loginUser.getUserFunction()) {
            String strId = functionId.substring(0, functionId.length() - 1) + "0"; // 将ID末位变为0
            LOGGER.debug("sysfunction-id:{},strId:{}", functionId, strId);
            String firstChar = strId.substring(0,1);

            switch (firstChar) {
                case "1": // 基本信息
                    menu.getBaseInfo().add(strId);
                    break;
                case "2": // 发起工作流
                    menu.getWorkflowStart().add(strId);
                    break;
                case "3": // 审核工作流
                    menu.getWorkflowAudit().add(strId);
                    break;
                case "4": // 报表
                    menu.getReportInfo().add(strId);
                    break;
                case "5": // 系统
                    menu.getSystemInfo().add(strId);
                    break;
                default:
                    LOGGER.warn("菜单信息错误，function-id:{}", strId);
                    break;
            }
        }
        if ("admin".equals(loginUser.getUserName())) {
            menu.setUserLevel(-1);
        }

        Gson gson = new Gson();
        String strMenu = gson.toJson(menu);
        LOGGER.info("strMenu:{}", strMenu);

        return menu;
    }


    /**
     * 跳转用户业务首页-用户列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<SysUser>> result = sysUserService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "system/user/list";
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @param request
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson<SysUser> add(SysUser sysUser, String userRoleList, HttpServletRequest request){

        ResultJson<SysUser> resultJson = new ResultJson<>();

        // 新增用户
        ResultJson<Integer> addUserResult = sysUserService.add(sysUser);
        if (WebConstants.OPERATION_FAILURE == addUserResult.getCode()) {
            resultJson.setCode(WebConstants.OPERATION_FAILURE);
            resultJson.setInfo(addUserResult.getInfo());
            return resultJson;
        }

        // 增加角色
        String[] userRoleStrList = userRoleList.split(",");
        List<Integer> userRoleIdList = new ArrayList<>();
        for (String userRole : userRoleStrList) {
            if (StringUtils.isNoneEmpty(userRole)) {
                userRoleIdList.add(Integer.valueOf(userRole));
            }
        }
        ResultJson<List<Integer>> userRoleIdListResult = sysRoleService.addUserRole(
                sysUser.getId(),
                userRoleIdList
        );
        if (WebConstants.OPERATION_FAILURE == userRoleIdListResult.getCode()) {
            resultJson.setCode(WebConstants.OPERATION_FAILURE);
            resultJson.setInfo(userRoleIdListResult.getInfo());
            return resultJson;
        }

        // 新增通讯录
        BasePerson basePerson = new BasePerson();
        basePerson.setUid(sysUser.getId() + "");
        basePerson.setName(sysUser.getUserName());
        basePerson.setAddress(sysUser.getAddress());
        basePerson.setEmail(sysUser.getEmail());
        basePerson.setMobile(sysUser.getMobile());
        basePerson.setAreaId(0);
        basePerson.setDeptId(0);
        personService.add(basePerson);


        sysUser.setUserRoleList(userRoleIdListResult.getData());
        resultJson.setData(sysUser);

        return resultJson;
    }

    /**
     * 更新用户
     *
     * @param sysUser
     * @param request
     * @return
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson<SysUser> update(SysUser sysUser, String userRoleList, HttpServletRequest request){

        ResultJson<SysUser> resultJson = new ResultJson<>();

        // 更新用户
        ResultJson<Void> updateUserResult = sysUserService.update(sysUser);
        if (WebConstants.OPERATION_FAILURE == updateUserResult.getCode()) {
            resultJson.setCode(WebConstants.OPERATION_FAILURE);
            resultJson.setInfo(updateUserResult.getInfo());
            return resultJson;
        }

        String[] userRoleStrList = userRoleList.split(",");
        List<Integer> userRoleIdList = new ArrayList<>();
        for (String userRole : userRoleStrList) {
            if (StringUtils.isNoneEmpty(userRole)) {
                userRoleIdList.add(Integer.valueOf(userRole));
            }
        }
        ResultJson<Void> updateUserRoleResult = sysRoleService.updateUserRole(
                sysUser.getId(),
                userRoleIdList
        );
        if (WebConstants.OPERATION_FAILURE == updateUserRoleResult.getCode()) {
            resultJson.setCode(WebConstants.OPERATION_FAILURE);
            resultJson.setInfo(updateUserRoleResult.getInfo());
            return resultJson;
        }

        // 更新通讯录
        BasePerson basePerson = personMananger.queryByUid(sysUser.getId());
        basePerson.setName(sysUser.getUserName());
        basePerson.setAddress(sysUser.getAddress());
        basePerson.setEmail(sysUser.getEmail());
        basePerson.setMobile(sysUser.getMobile());
        personService.update(basePerson);


        sysUser.setUserRoleList(userRoleIdList);
        resultJson.setData(sysUser);

        return resultJson;
    }

    /**
     * 跳转新增页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model){

        List<UserRole> userRoleList = initUserRoleList();

        model.addAttribute("roleList", userRoleList);

        return "system/user/add";
    }

    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer userId, Model model){

        SysUser sysUser = sysUserService.findByUserId(userId).getData();

        List<UserRole> userRoleList = initUserRoleList(userId);

        model.addAttribute("roleList", userRoleList);
        model.addAttribute("sysUser", sysUser);

        return "system/user/update";
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @RequestMapping("/remove")
    public ResultJson<Void> remove(Integer id){
        sysRoleService.removeUserRole(id);
        sysUserService.remove(id);
        return new ResultJson<>();
    }

    /**
     * 登录后，右侧内容页面
     * @return
     */
    @RequestMapping("/home")
    public String toIndexContent(Model model){
        ResultJson<List<BaseExecuteOrder>> dbList;
        boolean welcomeFlag = false; // true展示欢迎页；false不展示欢迎文字
//        boolean dataBoardFlag = true; // true展示工作看板；false不展示工作看板
        boolean progressFlag = true; // true展示工作进度；false不展示工作进度

        boolean buyFrameFlag = true; // true展示[框架采购]工作看板；false不展示[框架采购]工作看板
        boolean buyOrderFlag = true; // true展示[采购单]工作看板；false不展示[采购单]工作看板
        boolean saleFrameFlag = true; // true展示[框架需求]工作看板；false不展示[框架需求]工作看板
        boolean saleOrderFlag = true; // true展示[需求单]工作看板；false不展示[需求单]工作看板
        boolean executeFlag = true; // true展示[排期执行单]工作看板；false不展示[排期执行单]工作看板



        // 待处理
        List<DataBoardVO> todoListBuyFrame = new ArrayList<>();
        List<DataBoardVO> todoListBuyOrder = new ArrayList<>();
        List<DataBoardVO> todoListSaleFrame = new ArrayList<>();
        List<DataBoardVO> todoListSaleOrder = new ArrayList<>();
        List<DataBoardVO> todoListExecuteOrder = new ArrayList<>();


        // 进行中
//        List<DataBoardVO> handlingList= new ArrayList<>();
        List<DataBoardVO> handlingListExecuteOrder = new ArrayList<>();
        List<DataBoardVO> handlingListSaleOrder = new ArrayList<>();
        List<DataBoardVO> handlingListSaleFrame = new ArrayList<>();
        List<DataBoardVO> handlingListBuyOrder = new ArrayList<>();
        List<DataBoardVO> handlingListBuyFrame = new ArrayList<>();


        // 已完成
//        List<DataBoardVO> finishedList = new ArrayList<>();
        List<DataBoardVO> finishedListBuyFrame = new ArrayList<>();
        List<DataBoardVO> finishedListBuyOrder = new ArrayList<>();
        List<DataBoardVO> finishedListSaleFrame = new ArrayList<>();
        List<DataBoardVO> finishedListSaleOrder = new ArrayList<>();
        List<DataBoardVO> finishedListExecuteOrder = new ArrayList<>();

        // 项目进度
        List<DataBoardVO> processList = new ArrayList<>();


        Page page = new Page();
        // 工作看板--待处理
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(UserThreadLocalContext.getCurrentUser().getUserName()).orderByTaskCreateTime().desc();
        List<Task> todoTaskList = taskQuery.listPage(0,20);
        for (Task todoTask : todoTaskList) {
            DataBoardVO dataBoardVO = new DataBoardVO();
            String processInstanceId = todoTask.getProcessInstanceId();
            LOGGER.info("processInstanceId:{}", processInstanceId);
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            if (processInstance == null) {
                LOGGER.warn("processInstance is null");
                continue;
            }

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                LOGGER.warn("businessKey is empty");
                continue;
            }
            LOGGER.info("businessKey:{}", businessKey);
            LOGGER.info("ProcessDefinitionKey:{}", processInstance.getProcessDefinitionKey());

            switch (processInstance.getProcessDefinitionKey()) {

                case WebConstants.WORKFLOW_BUY:
                    BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
                    dataBoardVO.setId(baseBuyOrder.getId() + "");
                    dataBoardVO.setName(baseBuyOrder.getName());
                    dataBoardVO.setCreateTime(baseBuyOrder.getCreateTime());
                    dataBoardVO.setUpdateTime(baseBuyOrder.getUpdateTime());
                    dataBoardVO.setProcessInstanceId(processInstance.getProcessInstanceId());
                    dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_BUY);
                    todoListBuyOrder.add(dataBoardVO);
                    break;

                case WebConstants.WORKFLOW_BUY_FRAME:
                    BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(Integer.valueOf(businessKey)).getData();
                    dataBoardVO.setId(baseBuyOrderFrame.getId() + "");
                    dataBoardVO.setName(baseBuyOrderFrame.getName());
                    dataBoardVO.setCreateTime(baseBuyOrderFrame.getCreateTime());
                    dataBoardVO.setUpdateTime(baseBuyOrderFrame.getUpdateTime());
                    dataBoardVO.setProcessInstanceId(processInstance.getProcessInstanceId());
                    dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_BUY_FRAME);
                    todoListBuyFrame.add(dataBoardVO);
                    break;

                case WebConstants.WORKFLOW_SALE_ORDER:
                    BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
                    dataBoardVO.setId(baseExecuteOrder.getId() + "");
                    dataBoardVO.setName(baseExecuteOrder.getName());
                    dataBoardVO.setCreateTime(baseExecuteOrder.getCreateTime());
                    dataBoardVO.setUpdateTime(baseExecuteOrder.getUpdateTime());
                    dataBoardVO.setProcessInstanceId(processInstance.getProcessInstanceId());
                    dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_ORDER);
                    todoListSaleOrder.add(dataBoardVO);
                    break;

                case WebConstants.WORKFLOW_SALE_EXECUTE:
                    BaseExecuteOrder baseExecuteOrderExecute = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
                    dataBoardVO.setId(baseExecuteOrderExecute.getId() + "");
                    dataBoardVO.setName(baseExecuteOrderExecute.getName());
                    dataBoardVO.setCreateTime(baseExecuteOrderExecute.getCreateTime());
                    dataBoardVO.setUpdateTime(baseExecuteOrderExecute.getUpdateTime());
                    dataBoardVO.setProcessInstanceId(processInstance.getProcessInstanceId());
                    dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_EXECUTE);
                    todoListExecuteOrder.add(dataBoardVO);
                    break;

                case WebConstants.WORKFLOW_SALE_FRAME:
                    BaseExecuteOrderFrame baseExecuteOrderFrame = saleFrameService.findById(Integer.valueOf(businessKey)).getData();
                    dataBoardVO.setId(baseExecuteOrderFrame.getId() + "");
                    dataBoardVO.setName(baseExecuteOrderFrame.getName());
                    dataBoardVO.setCreateTime(baseExecuteOrderFrame.getCreateTime());
                    dataBoardVO.setUpdateTime(baseExecuteOrderFrame.getUpdateTime());
                    dataBoardVO.setProcessInstanceId(processInstance.getProcessInstanceId());
                    dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_FRAME);
                    todoListSaleFrame.add(dataBoardVO);
                    break;

                default:
                    LOGGER.error("ProcessDefinitionKey is error.");
                    break;
            }
        }


        Integer uid = Integer.valueOf(UserThreadLocalContext.getCurrentUser().getUserId());
        List<String> sysRoleList = UserThreadLocalContext.getCurrentUser().getSysRoleList();

        // 工作看板--进行中
        ProcessInstanceQuery handlingDemandQuery = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY).active().orderByProcessInstanceId().desc();
        List<ProcessInstance> ProcessInstanceQueryList = handlingDemandQuery.list();
        // 关联业务实体
        for (ProcessInstance processInstance : ProcessInstanceQueryList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            if (baseBuyOrder.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseBuyOrder.getId() + "");
            dataBoardVO.setName(baseBuyOrder.getName());
            dataBoardVO.setCreateTime(baseBuyOrder.getCreateTime());
            dataBoardVO.setUpdateTime(baseBuyOrder.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseBuyOrder.getProcessInstanceId());
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_BUY);

            handlingListBuyOrder.add(dataBoardVO);
        }
        LOGGER.debug("handlingListBuyOrder.size:{}", handlingListBuyOrder.size());

        handlingDemandQuery = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY_FRAME).active().orderByProcessInstanceId().desc();
        ProcessInstanceQueryList = handlingDemandQuery.list();
        // 关联业务实体
        for (ProcessInstance processInstance : ProcessInstanceQueryList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(Integer.valueOf(businessKey)).getData();
            if (baseBuyOrderFrame.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseBuyOrderFrame.getId() + "");
            dataBoardVO.setName(baseBuyOrderFrame.getName());
            dataBoardVO.setCreateTime(baseBuyOrderFrame.getCreateTime());
            dataBoardVO.setUpdateTime(baseBuyOrderFrame.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseBuyOrderFrame.getProcessInstanceId());
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_BUY_FRAME);

            handlingListBuyFrame.add(dataBoardVO);
        }
        LOGGER.debug("handlingListBuyFrame.size:{}", handlingListBuyFrame.size());

        handlingDemandQuery = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_ORDER).active().orderByProcessInstanceId().desc();
        ProcessInstanceQueryList = handlingDemandQuery.list();
        // 关联业务实体
        for (ProcessInstance processInstance : ProcessInstanceQueryList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey) || "null".equals(businessKey)) {
                continue;
            }

            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            if (baseExecuteOrder.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseExecuteOrder.getId() + "");
            dataBoardVO.setName(baseExecuteOrder.getName());
            dataBoardVO.setCreateTime(baseExecuteOrder.getCreateTime());
            dataBoardVO.setUpdateTime(baseExecuteOrder.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseExecuteOrder.getProcessInstanceId());
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_ORDER);

            handlingListSaleOrder.add(dataBoardVO);
        }
        LOGGER.debug("handlingListSaleOrder.size:{}", handlingListSaleOrder.size());

        handlingDemandQuery = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_EXECUTE).active().orderByProcessInstanceId().desc();
        ProcessInstanceQueryList = handlingDemandQuery.list();
        // 关联业务实体
        for (ProcessInstance processInstance : ProcessInstanceQueryList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            if (baseExecuteOrder.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseExecuteOrder.getId() + "");
            dataBoardVO.setName(baseExecuteOrder.getName());
            dataBoardVO.setCreateTime(baseExecuteOrder.getCreateTime());
            dataBoardVO.setUpdateTime(baseExecuteOrder.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseExecuteOrder.getProcessInstanceId());
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_EXECUTE);

            handlingListExecuteOrder.add(dataBoardVO);
        }
        LOGGER.debug("handlingListExecuteOrder.size:{}", handlingListExecuteOrder.size());

        handlingDemandQuery = runtimeService.createProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_FRAME).active().orderByProcessInstanceId().desc();
        ProcessInstanceQueryList = handlingDemandQuery.list();
        // 关联业务实体
        for (ProcessInstance processInstance : ProcessInstanceQueryList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = processInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrderFrame baseExecuteOrderFrame = saleFrameService.findById(Integer.valueOf(businessKey)).getData();
            if (baseExecuteOrderFrame.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseExecuteOrderFrame.getId() + "");
            dataBoardVO.setName(baseExecuteOrderFrame.getName());
            dataBoardVO.setCreateTime(baseExecuteOrderFrame.getCreateTime());
            dataBoardVO.setUpdateTime(baseExecuteOrderFrame.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseExecuteOrderFrame.getProcessInstanceId());
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_FRAME);

            handlingListSaleFrame.add(dataBoardVO);
        }
        LOGGER.debug("handlingListSaleFrame.size:{}", handlingListSaleFrame.size());

//        handlingList.addAll(handlingListBuyFrame);
//        handlingList.addAll(handlingListBuyOrder);
//        handlingList.addAll(handlingListSaleFrame);
//        handlingList.addAll(handlingListSaleOrder);
//        handlingList.addAll(handlingListExecuteOrder);
//        LOGGER.debug("排序前handlingList.size:{}", handlingList.size());
//        Collections.sort(handlingList);
//        handlingList = handlingList.subList(0, 20);
//        LOGGER.debug("排序后handlingList.size:{}", handlingList.size());


        // 工作看板--已完成
        HistoricProcessInstanceQuery finishedDemandQuery = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY).finished().orderByProcessInstanceEndTime().desc();
        List<HistoricProcessInstance> historicProcessInstanceList = finishedDemandQuery.list();
        LOGGER.info("WORKFLOW_BUY:size{}", historicProcessInstanceList.size());
        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrder baseBuyOrder = buyOrderService.findById(Integer.valueOf(businessKey)).getData();
            if (baseBuyOrder.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseBuyOrder.getId() + "");
            dataBoardVO.setName(baseBuyOrder.getName());
            dataBoardVO.setCreateTime(baseBuyOrder.getCreateTime());
            dataBoardVO.setUpdateTime(baseBuyOrder.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseBuyOrder.getProcessInstanceId());
            dataBoardVO.setWfStep(99);
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_BUY);

            finishedListBuyOrder.add(dataBoardVO);
        }
        LOGGER.debug("finishedListBuyOrder.size:{}", finishedListBuyOrder.size());



        finishedDemandQuery = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_BUY_FRAME).finished().orderByProcessInstanceEndTime().desc();
        historicProcessInstanceList = finishedDemandQuery.list();
        LOGGER.info("WORKFLOW_BUY_FRAME:size{}", historicProcessInstanceList.size());
        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(Integer.valueOf(businessKey)).getData();
            if (baseBuyOrderFrame.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseBuyOrderFrame.getId() + "");
            dataBoardVO.setName(baseBuyOrderFrame.getName());
            dataBoardVO.setCreateTime(baseBuyOrderFrame.getCreateTime());
            dataBoardVO.setUpdateTime(baseBuyOrderFrame.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseBuyOrderFrame.getProcessInstanceId());
            dataBoardVO.setWfStep(99);
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_BUY_FRAME);

            finishedListBuyFrame.add(dataBoardVO);
        }
        LOGGER.debug("finishedListBuyFrame.size:{}", finishedListBuyFrame.size());



        finishedDemandQuery = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_ORDER).finished().orderByProcessInstanceEndTime().desc();
        historicProcessInstanceList = finishedDemandQuery.list();
        LOGGER.info("WORKFLOW_SALE_ORDER:size{}", historicProcessInstanceList.size());
        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            if (baseExecuteOrder.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseExecuteOrder.getId() + "");
            dataBoardVO.setName(baseExecuteOrder.getName());
            dataBoardVO.setCreateTime(baseExecuteOrder.getCreateTime());
            dataBoardVO.setUpdateTime(baseExecuteOrder.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseExecuteOrder.getProcessInstanceId());
            dataBoardVO.setWfStep(99);
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_ORDER);

            finishedListSaleOrder.add(dataBoardVO);
        }
        LOGGER.debug("finishedListSaleOrder.size:{}", finishedListSaleOrder.size());



        finishedDemandQuery = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_EXECUTE).finished().orderByProcessInstanceEndTime().desc();
        historicProcessInstanceList = finishedDemandQuery.list();
        LOGGER.info("WORKFLOW_SALE_EXECUTE:size{}", historicProcessInstanceList.size());
        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(Integer.valueOf(businessKey)).getData();
            if (baseExecuteOrder.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseExecuteOrder.getId() + "");
            dataBoardVO.setName(baseExecuteOrder.getName());
            dataBoardVO.setCreateTime(baseExecuteOrder.getCreateTime());
            dataBoardVO.setUpdateTime(baseExecuteOrder.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseExecuteOrder.getProcessInstanceId());
            dataBoardVO.setWfStep(99);
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_EXECUTE);

            finishedListExecuteOrder.add(dataBoardVO);
        }
        LOGGER.debug("finishedListExecuteOrder.size:{}", finishedListExecuteOrder.size());



        finishedDemandQuery = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(WebConstants.WORKFLOW_SALE_FRAME).finished().orderByProcessInstanceEndTime().desc();
        historicProcessInstanceList = finishedDemandQuery.list();
        LOGGER.info("WORKFLOW_SALE_FRAME:size{}", historicProcessInstanceList.size());
        // 关联业务实体
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
            DataBoardVO dataBoardVO = new DataBoardVO();

            String businessKey = historicProcessInstance.getBusinessKey();
            if (StringUtils.isEmpty(businessKey)) {
                continue;
            }

            BaseExecuteOrderFrame baseExecuteOrderFrame = saleFrameService.findById(Integer.valueOf(businessKey)).getData();
            if (baseExecuteOrderFrame.getUserId() != uid) {
                if (!ActivitiTool.checkViewAuth(sysRoleList)) {
                    continue;
                }
            }

            dataBoardVO.setId(baseExecuteOrderFrame.getId() + "");
            dataBoardVO.setName(baseExecuteOrderFrame.getName());
            dataBoardVO.setCreateTime(baseExecuteOrderFrame.getCreateTime());
            dataBoardVO.setUpdateTime(baseExecuteOrderFrame.getUpdateTime());
            dataBoardVO.setProcessInstanceId(baseExecuteOrderFrame.getProcessInstanceId());
            dataBoardVO.setWfStep(99);
            dataBoardVO.setProcessDefinitionKey(WebConstants.WORKFLOW_SALE_FRAME);

            finishedListSaleFrame.add(dataBoardVO);
        }
        LOGGER.debug("finishedListSaleFrame.size:{}", finishedListSaleFrame.size());
//
//        finishedList.addAll(finishedListBuyFrame);
//        finishedList.addAll(finishedListBuyOrder);
//        finishedList.addAll(finishedListSaleFrame);
//        finishedList.addAll(finishedListSaleOrder);
//        finishedList.addAll(finishedListExecuteOrder);
//        LOGGER.debug("排序前finishedList.size:{}", finishedList.size());
//        Collections.sort(finishedList);
//        finishedList = finishedList.subList(0, 20);
//        LOGGER.debug("排序后finishedList.size:{}", finishedList.size());


        // 项目进度
//        processList.addAll(todoListBuyFrame);
//        processList.addAll(todoListBuyOrder);
//        processList.addAll(todoListSaleFrame);
//        processList.addAll(todoListSaleOrder);
//        processList.addAll(todoListExecuteOrder);
        processList.addAll(handlingListBuyFrame);
        processList.addAll(handlingListBuyOrder);
        processList.addAll(handlingListSaleFrame);
        processList.addAll(handlingListSaleOrder);
        processList.addAll(handlingListExecuteOrder);
        processList.addAll(finishedListBuyFrame);
        processList.addAll(finishedListBuyOrder);
        processList.addAll(finishedListSaleFrame);
        processList.addAll(finishedListSaleOrder);
        processList.addAll(finishedListExecuteOrder);
        LOGGER.debug("排序前processList.size:{}", processList.size());
        Collections.sort(processList);
        int listSize = processList.size() >= 20 ? 20 : processList.size();
        processList = processList.subList(0, listSize);
        LOGGER.debug("排序后processList.size:{}", processList.size());

        for (DataBoardVO data:processList) {
            LOGGER.debug("data is null:{}" , data == null);
            LOGGER.debug("getWfStep:{}" , data.getWfStep());
            if (data.getWfStep() > 0) {
                continue;
            }

            // 排期需求单有可能有两个task
            List<Task> taskList = taskService.createTaskQuery().processInstanceId(data.getProcessInstanceId()).active().list();
            if (taskList == null) {
                LOGGER.warn("ProcessInstanceId:{}", data.getProcessInstanceId());
                continue;
            }

            for (Task task:taskList) {
                String taskDefKey = task.getTaskDefinitionKey();

                int wfStep = 0;
                switch (data.getProcessDefinitionKey()) {
                    case WebConstants.WORKFLOW_BUY:
                        switch (taskDefKey) {
                            case WebConstants.Audit.MEDIA:
                                wfStep = 1; // 媒介审核
                                break;
                            case WebConstants.Audit.SALER_GM:
                                wfStep = 2; // 销售总经理审核
                                break;
                            case WebConstants.Audit.FINANCIAL_GM:
                                wfStep = 3; //  财务审核
                                break;
                            case WebConstants.Audit.LEGAL_GM:
                                wfStep = 4; //  法务审核
                                break;
                            default:
                                LOGGER.warn("WORKFLOW_BUY错误的参数。mapKey is :{}", taskDefKey);
                        }
                        break;

                    case WebConstants.WORKFLOW_BUY_FRAME:
                        switch (taskDefKey) {
                            case WebConstants.Audit.MEDIA:
                                wfStep = 1; // 媒介审核
                                break;
                            case WebConstants.Audit.SALER_GM:
                                wfStep = 2; // 销售总经理审核
                                break;
                            case WebConstants.Audit.FINANCIAL_GM:
                                wfStep = 3; //  财务审核
                                break;
                            default:
                                LOGGER.warn("WORKFLOW_BUY_FRAME错误的参数。mapKey is :{}", taskDefKey);
                        }
                        break;

                    case WebConstants.WORKFLOW_SALE_ORDER:
                        switch (taskDefKey) {
                            case WebConstants.Audit.SALER_DM:
                                wfStep = 1; // 销售主管审核
                                break;
                            case WebConstants.Audit.SALER_GM:
                                wfStep = 2; // 销售总经理审核
                                break;
                            case WebConstants.Audit.MEDIA:
                                wfStep = 3; // 媒介审核
                                break;
                            case WebConstants.Audit.FINANCIAL_GM:
                                wfStep = 4; //  财务审核
                                break;
                            case WebConstants.Audit.LEGAL_GM:
                                wfStep = 5; //  法务审核
                                break;
                            case WebConstants.Audit.SIGN_CONTRACT:
                                wfStep = 6; //  销售-签署合同
                                break;
                            case WebConstants.Audit.UPLOAD_CONTRACT_IMG:
                                wfStep = 7; //  销售-上传扫描合同
                                break;
                            case WebConstants.Audit.ORIGINAL_CONTRACT:
                                wfStep = 8; //  销售-追要原章合同
                                break;
                            default:
                                LOGGER.warn("WORKFLOW_SALE_ORDER错误的参数。mapKey is :{}", taskDefKey);
                        }
                        break;

                    case WebConstants.WORKFLOW_SALE_EXECUTE:
                        switch (taskDefKey) {
                            case WebConstants.Audit.MEDIA:
                                wfStep = 1; // 媒介审核
                                break;
                            case WebConstants.Audit.SALER_DM:
                                wfStep = 2; // 销售主管审核
                                break;
                            case WebConstants.Audit.SALER_GM:
                                wfStep = 3; // 销售总经理审核
                                break;
                            case WebConstants.Audit.FINANCIAL_GM:
                                wfStep = 4; //  财务审核
                                break;
                            case WebConstants.Audit.UPLOAD_EXECUTE_ORDER_IMG:
                                wfStep = 5; //  上传扫描版排期单
                                break;
                            case WebConstants.Audit.ORIGINAL_EXECUTE_ORDER:
                                wfStep = 20; //  追要原章排期单
                                if (data.getWfStep() > 10) {
                                    wfStep = wfStep + data.getWfStep();
                                }
                                break;
                            case WebConstants.Audit.CONFIRM_COST:
                                wfStep = 11; //  成本确认
                                if (data.getWfStep() > 20) {
                                    wfStep = wfStep + data.getWfStep();
                                }
                                break;
                            case WebConstants.Audit.REMINDER_PAYMENT:
                                wfStep = 12; //  财务向销售催款
                                if (data.getWfStep() > 20) {
                                    wfStep = wfStep + data.getWfStep();
                                }
                                break;
                            case WebConstants.Audit.GATHERING:
                                wfStep = 13; //  销售向客户催款
                                if (data.getWfStep() > 20) {
                                    wfStep = wfStep + data.getWfStep();
                                }
                                break;
                            default:
                                LOGGER.warn("WORKFLOW_SALE_ORDER错误的参数。mapKey is :{}", taskDefKey);
                        }
                        break;

                    case WebConstants.WORKFLOW_SALE_FRAME:
                        switch (taskDefKey) {
                            case WebConstants.Audit.SALER_GM:
                                wfStep = 1; // 销售总经理审核
                                break;
                            case WebConstants.Audit.MEDIA:
                                wfStep = 2; // 媒介审核
                                break;
                            case WebConstants.Audit.FINANCIAL_GM:
                                wfStep = 3; //  财务审核
                                break;
                            default:
                                LOGGER.warn("WORKFLOW_SALE_FRAME错误的参数。mapKey is :{}", taskDefKey);
                        }
                        break;

                    default:
                        LOGGER.error("ProcessDefinitionKey is error.");
                        break;
                }
                data.setWfStep(wfStep);
            }
        }


        // 判断工作看板：如果各list都为空，不展示；否则展示工作看板
        if ((todoListBuyFrame.size() == 0)
                && (handlingListBuyFrame.size() == 0)
                && (finishedListBuyFrame.size() == 0)
                ) {
            buyFrameFlag = false;
        }
        if ((todoListBuyOrder.size() == 0)
                && (handlingListBuyOrder.size() == 0)
                && (finishedListBuyOrder.size() == 0)
                ) {
            buyOrderFlag = false;
        }
        if ((todoListSaleFrame.size() == 0)
                && (handlingListSaleFrame.size() == 0)
                && (finishedListSaleFrame.size() == 0)
                ) {
            saleFrameFlag = false;
        }
        if ((todoListSaleOrder.size() == 0)
                && (handlingListSaleOrder.size() == 0)
                && (finishedListSaleOrder.size() == 0)
                ) {
            saleOrderFlag = false;
        }
        if ((todoListExecuteOrder.size() == 0)
                && (handlingListExecuteOrder.size() == 0)
                && (finishedListExecuteOrder.size() == 0)
                ) {
            executeFlag = false;
        }

        // 项目进度
        if ((processList.size() == 0)
                ) {
            progressFlag = false;
        }

        // 判断欢迎页展示内容：如果各list都为空，展示欢迎文字；否则展示工作看板
        if (!buyFrameFlag && !buyOrderFlag && !saleFrameFlag && !saleOrderFlag && !executeFlag && !progressFlag) {
            welcomeFlag = true;
        }
        LOGGER.info("progressFlag：{}", progressFlag);
        LOGGER.info("welcomeFlag：{}", welcomeFlag);

        model.addAttribute("welcomeFlag", welcomeFlag);
        model.addAttribute("progressFlag", progressFlag);
        model.addAttribute("buyFrameFlag", buyFrameFlag);
        model.addAttribute("buyOrderFlag", buyOrderFlag);
        model.addAttribute("saleFrameFlag", saleFrameFlag);
        model.addAttribute("saleOrderFlag", saleOrderFlag);
        model.addAttribute("executeFlag", executeFlag);
        model.addAttribute("progressFlag", progressFlag);

        model.addAttribute("todoListBuyFrame", todoListBuyFrame);
        model.addAttribute("handlingListBuyFrame", handlingListBuyFrame);
        model.addAttribute("finishedListBuyFrame", finishedListBuyFrame);
        model.addAttribute("todoListBuyOrder", todoListBuyOrder);
        model.addAttribute("handlingListBuyOrder", handlingListBuyOrder);
        model.addAttribute("finishedListBuyOrder", finishedListBuyOrder);
        model.addAttribute("todoListSaleFrame", todoListSaleFrame);
        model.addAttribute("handlingListSaleFrame", handlingListSaleFrame);
        model.addAttribute("finishedListSaleFrame", finishedListSaleFrame);
        model.addAttribute("todoListSaleOrder", todoListSaleOrder);
        model.addAttribute("handlingListSaleOrder", handlingListSaleOrder);
        model.addAttribute("finishedListSaleOrder", finishedListSaleOrder);
        model.addAttribute("todoListExecuteOrder", todoListExecuteOrder);
        model.addAttribute("handlingListExecuteOrder", handlingListExecuteOrder);
        model.addAttribute("finishedListExecuteOrder", finishedListExecuteOrder);

        model.addAttribute("processList", processList);
        return "system/user/home";
    }
    
    @RequestMapping("/toList")
    public String toList(){
        return "system/user/list";
    }

    /**
     * 初始化用户角色列表
     *
     * @param userId
     * @return
     */
    private List<UserRole> initUserRoleList(Integer userId) {
        List<UserRole> userRoleList = new ArrayList<>();
        List<SysUserRole> sysUserRoleList = sysRoleService.findUserRoleAll(userId).getData();
        List<SysRole> roleList = sysRoleService.findAll().getData();
        for (SysRole sysRole : roleList) {
            UserRole userRole = new UserRole();
            userRole.setSysRole(sysRole);
            // 设置当前用户是否拥有此角色
            for (SysUserRole sysUserRole : sysUserRoleList) {
                if (sysRole.getId().equals(sysUserRole.getRoles())) {
                    userRole.setSelected(true);
                    break;
                }
            }
            userRoleList.add(userRole);
        }

        return userRoleList;
    }

    /**
     * 初始化用户角色列表
     *
     * @return
     */
    private List<UserRole> initUserRoleList() {
        List<UserRole> userRoleList = new ArrayList<>();
        List<SysRole> roleList = sysRoleService.findAll().getData();
        for (SysRole sysRole : roleList) {
            UserRole userRole = new UserRole();
            userRole.setSysRole(sysRole);
            userRole.setSelected(false);
            userRoleList.add(userRole);
        }
        return userRoleList;
    }
}
