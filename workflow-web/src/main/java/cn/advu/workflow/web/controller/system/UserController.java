package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.domain.golbal.Page;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.dto.system.UserRole;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import cn.advu.workflow.web.service.system.LoginService;
import cn.advu.workflow.web.service.system.SysRoleService;
import cn.advu.workflow.web.service.system.SysUserService;
import cn.advu.workflow.web.vo.MenuVO;
import com.google.gson.Gson;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
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
    ExecuteOrderService executeOrderService;

    @Autowired
    protected TaskService taskService;

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
        boolean dataBoardFlag = true; // true展示工作看板；false不展示工作看板
        boolean progressFlag = true; // true展示工作进度；false不展示工作进度

        Page page = new Page();
        // 工作看板--待处理
        BaseExecuteOrder param = new BaseExecuteOrder();
        param.setPage(page);
        param.setStatus((byte) 1);
        dbList = executeOrderService.findAll(param);
        List<BaseExecuteOrder> todoList = dbList.getData();
        LOGGER.info("待处理件数：{}", todoList.size());

        // 工作看板--进行中
        param.setStatus((byte) 2);
        dbList = executeOrderService.findAll(param);
        List<BaseExecuteOrder> handlingList = dbList.getData();
        LOGGER.info("进行中件数：{}", handlingList.size());

        // 工作看板--已完成
        param.setStatus((byte) 3);
        dbList = executeOrderService.findAll(param);
        List<BaseExecuteOrder> finishedList = dbList.getData();
        LOGGER.info("已完成件数：{}", finishedList.size());

        // 项目进度
        param = new BaseExecuteOrder();
        param.setPage(page);
        param.setStatusArray("(0, 1, 2)");
        dbList = executeOrderService.findAll(param);
        List<BaseExecuteOrder> unFinishedList = dbList.getData(); // 未完成进度列表
        for (BaseExecuteOrder obj:unFinishedList) { // 设置项目进度
            Byte status = obj.getStatus();
            String wfStep = "0";
            switch (status) {
                case 2:
                    wfStep = "1"; // 开始投放
                    if (false) { // TODO 发票
                        wfStep = "9"; // 发票

                    } else if (false) { // TODO 原章合同
                        wfStep = "8"; // 原章合同

                    } else if (false) { // TODO 合同已签署
                        wfStep = "7"; //  开始投放 + 合同已签署
                    } else {
                        // 设置当前任务信息
                        if (StringUtils.isEmpty(obj.getProcessInstanceId())) {
                            LOGGER.warn("ProcessInstanceId is empty, biz-id is :{}", obj.getId());
                            obj.setWfStep(wfStep);
                            continue;
                        }
                        Task task = taskService.createTaskQuery().processInstanceId(obj.getProcessInstanceId()).active().singleResult();
                        if (task == null) {
                            LOGGER.warn("task is null, biz-id is :{}", obj.getId());
                            obj.setWfStep(wfStep);
                            continue;
                        }
                        String taskDefKey = task.getTaskDefinitionKey();
                        switch (taskDefKey) {
                            case WebConstants.Audit.MEDIA:
                                wfStep = "2"; // 媒介审核
                                break;
                            case WebConstants.Audit.SALER_GM:
                                wfStep = "3"; // 销售总经理审核
                                break;
                            case WebConstants.Audit.FINANCIAL_GM:
                                wfStep = "4"; //  财务审核
                                break;
                            case WebConstants.Audit.LEGAL_GM:
                                wfStep = "5"; //  法务审核
                                break;
                            default:
                                LOGGER.warn("错误的参数。mapKey is :{}", taskDefKey);
                        }
                    }
                    break;
                case 1:
                    wfStep = "7"; // 法务已审核
                    break;
                case 0:
                    wfStep = "2"; // 销售已提交

                    // 设置当前任务信息
                    Task task = taskService.createTaskQuery().processInstanceId(obj.getProcessInstanceId()).active().singleResult();
                    if (task == null) {
                        LOGGER.warn("task is null, biz-id is :{}", obj.getId());
                        obj.setWfStep(wfStep);
                        continue;
                    }
                    String taskDefKey = task.getTaskDefinitionKey();
                    switch (taskDefKey) {
                        case WebConstants.Audit.SALER_DM:
                            wfStep = "3"; // 销售主管审核
                            break;
                        case WebConstants.Audit.SALER_GM:
                            wfStep = "4"; // 销售总经理审核
                            break;
                        case WebConstants.Audit.MEDIA:
                            wfStep = "5"; // 媒介审核
                            break;
                        case WebConstants.Audit.FINANCIAL_GM:
                            wfStep = "6"; //  财务审核
                            break;
                        case WebConstants.Audit.LEGAL_GM:
                            wfStep = "7"; //  法务审核
                            break;
                        default:
                            LOGGER.warn("错误的参数。mapKey is :{}", taskDefKey);
                    }
                    break;
                default:
                    LOGGER.warn("status was wrong:{}", status);
                    break;
            }
            obj.setWfStep(wfStep);

        }


        // 判断欢迎页展示内容：如果各list都为空，展示欢迎文字；否则展示工作看板
        if ((todoList == null || todoList.size() == 0)
                && (handlingList == null || handlingList.size() == 0)
                && (finishedList == null || finishedList.size() == 0)
            ) {
            dataBoardFlag = false;
        }
        if ((unFinishedList == null || unFinishedList.size() == 0)
                ) {
            progressFlag = false;
        }
        if (!dataBoardFlag && !progressFlag) {
            welcomeFlag = true;
        }
        LOGGER.info("dataBoardFlag：{}, progressFlag：{}", dataBoardFlag, progressFlag);
        LOGGER.info("welcomeFlag：{}", welcomeFlag);

        model.addAttribute("welcomeFlag", welcomeFlag);
        model.addAttribute("dataBoardFlag", dataBoardFlag);
        model.addAttribute("progressFlag", progressFlag);
        model.addAttribute("todoList", todoList);
        model.addAttribute("handlingList", handlingList);
        model.addAttribute("finishedList", finishedList);
        model.addAttribute("unFinishedList", unFinishedList);
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
