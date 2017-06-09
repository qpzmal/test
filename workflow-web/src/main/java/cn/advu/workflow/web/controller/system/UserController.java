package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.domain.database.BusinessChannel;
import cn.advu.workflow.domain.database.SysPermission;
import cn.advu.workflow.domain.database.SysUser;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.exception.LoginException;
import cn.advu.workflow.web.common.loginContext.LoginAccount;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.service.SysUserService;
import cn.advu.workflow.web.user.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserService userService;

    @RequestMapping("/toAdd")
    public String toAdd(){
        LOGGER.debug("user-to-add-start");
        return "user/add";
    }

    @RequestMapping("/index_v1")
    public String toIndexV1(){
        return "index_v1";
    }
    
    @RequestMapping("/toList")
    public String toList(){
        return "user/list";
    }
    
    @RequestMapping("/getAll")
    @ResponseBody
    public ResultJson<List<SysUser>> getAll(){
        ResultJson<List<SysUser>> all = null;
        try {
            all = sysUserService.getAll();
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return all;
    }
    
    @RequestMapping("/add")
    @ResponseBody
    public ResultJson<Object> add(SysUser user, HttpServletRequest request){
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));

        if(user == null){
            ResultJson<Object> rj = new ResultJson<>(WebConstants.OPERATION_FAILURE);
            return rj;
        }
        
        //TODO 设置创建者
        user.setCreatorId(1);
        return sysUserService.add(user, roleId);
    }
    
    @RequestMapping("/toEdit")
    public String toEdit(Model model, HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        SysUser user = sysUserService.getById(userId);
        model.addAttribute("user", user);
        return "user/edit";
    }
    
    @ResponseBody
    @RequestMapping("/edit")
    public ResultJson<Object> editUser(SysUser user, HttpServletRequest request){
        Integer roleId = null;
        if(  null != request.getParameter("roleId") ) {
            roleId = Integer.valueOf(request.getParameter("roleId"));
        }

        return sysUserService.edit(user, roleId);
    }
    
    @ResponseBody
    @RequestMapping("/getPerms")
    public ResultJson<List<SysPermission>> getPermsById(HttpServletRequest request){
        String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);


        if (StringUtils.isBlank(loginCookie)) {
            return null;
        }

        LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);

//        RedisClient redisClient = (RedisClient) SpringContextUtil.getBean("redisClient");
//        String str = redisClient.getStr("login_permissions_key_" + loginUser.getUserId().toString());
//        JSONArray array = JSONArray.fromObject(str);
//        List<SysPermission> list = JSONArray.toList(array, new SysPermission(), new JsonConfig());

        LoginAccount account = userService.getAccount(loginUser);
        List<SysPermission> permissions = account.getPermissions();
        JSONArray array = JSONArray.fromObject(permissions);
        List<SysPermission> list = JSONArray.toList(array, new SysPermission(), new JsonConfig());

        ResultJson<List<SysPermission>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(list);
        return rj;
    }

    @RequestMapping("toUser")
    public String toUser(HttpServletRequest request, Model model){
        String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);
        LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);
        SysUser user = sysUserService.getById(loginUser.getUserId().intValue());
        model.addAttribute("user",user);
        return "user/user";
    }

    /**
     * 跳转商务用户列表
     * @return
     */
    @RequestMapping("tobusinessuserlist")
    public String toBusinessUserList(){
        return "user/businessList";
    }

    @RequestMapping("/getBusinessAll")
    @ResponseBody
    public ResultJson<List<SysUser>> getBusinessAll(){
        ResultJson<List<SysUser>> all = null;
        try {
            all = sysUserService.getBusinessAll();
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return all;
    }


    /**
     * 商务用户分配渠道
     * @param businessChannel
     * @param request
     * @return
     */
    @RequestMapping("/allotChannel")
    @ResponseBody
    public ResultJson<String> allotChannel(BusinessChannel businessChannel, HttpServletRequest request){
        ResultJson<String> rj = null;
        try {
            String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);
            if (StringUtils.isNotBlank(loginCookie)) {
                LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);
                businessChannel.setUpdateUser(loginUser.getUserId().intValue());
            }
            sysUserService.allotChannel(businessChannel);
            rj = new ResultJson<>();
            rj.setCode(WebConstants.OPERATION_SUCCESS);
            rj.setData("渠道分配成功");
        } catch (LoginException e) {
            e.printStackTrace();
            rj.setCode(WebConstants.OPERATION_FAILURE);
            rj.setData("渠道分配失败，请联系技术人员");
        }
        return rj;

    }





}
