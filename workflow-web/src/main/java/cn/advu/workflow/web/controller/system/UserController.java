package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.SysFuction;
import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.LoginAccount;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.dto.system.UserRole;
import cn.advu.workflow.web.service.system.LoginService;
import cn.advu.workflow.web.service.system.SysRoleService;
import cn.advu.workflow.web.service.system.SysUserService;
import cn.advu.workflow.web.vo.MenuVO;
import com.google.gson.Gson;
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

        LoginAccount account = new LoginAccount();
        LoginUser loginUser = UserThreadLocalContext.getCurrentUser();
        if(loginUser != null ){
            account.setUser(loginUser);
            // 获取用户菜单信息
            loginService.queryUserFunction(account);
        } else {
            LOGGER.warn("loginuser is null.");
        }

        MenuVO menu = new MenuVO();
        menu.setWorkflowStart(new ArrayList<String>());
        menu.setWorkflowAudit(new ArrayList<String>());
        menu.setReportInfo(new ArrayList<String>());
        menu.setBaseInfo(new ArrayList<String>());
        menu.setSystemInfo(new ArrayList<String>());
        for (SysFuction sysfunction : account.getUserFunction()) {
            String strId = sysfunction.getId()  + "";
            strId = strId.substring(0, strId.length() - 1) + "0"; // 将ID末位变为0
            LOGGER.debug("sysfunction-id:{},strId:{}", sysfunction.getId(), strId);
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

        ResultJson<SysUser> resultJson = new ResultJson<>(WebConstants.OPERATION_SUCCESS);

        // 新增用户
        sysUserService.add(sysUser);

        String[] userRoleStrList = userRoleList.split(",");
        List<Integer> userRoleIdList = new ArrayList<>();
        for (String userRole : userRoleStrList) {
            if (StringUtils.isNoneEmpty(userRole)) {
                userRoleIdList.add(Integer.valueOf(userRole));
            }
        }

        userRoleIdList = sysRoleService.addUserRole(
                sysUser.getId(),
                userRoleIdList
        ).getData();

        sysUser.setUserRoleList(userRoleIdList);
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

        ResultJson<SysUser> resultJson = new ResultJson<>(WebConstants.OPERATION_SUCCESS);

        Integer id = sysUser.getId();
        if (id == null) {
            resultJson.setCode(WebConstants.OPERATION_FAILURE);
        }

        // 更新用户
        sysUserService.update(sysUser);

        String[] userRoleStrList = userRoleList.split(",");
        List<Integer> userRoleIdList = new ArrayList<>();
        for (String userRole : userRoleStrList) {
            if (StringUtils.isNoneEmpty(userRole)) {
                userRoleIdList.add(Integer.valueOf(userRole));
            }
        }

        sysRoleService.updateUserRole(
                sysUser.getId(),
                userRoleIdList
        );

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
     * @param userId
     */
    @RequestMapping("/remove")
    public void remove(Integer userId){
        sysRoleService.removeUserRole(userId);
        sysUserService.remove(userId);
    }

    @RequestMapping("/home")
    public String toIndexContent(){
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
