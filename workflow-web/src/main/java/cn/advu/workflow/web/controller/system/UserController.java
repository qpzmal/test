package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.service.system.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;

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
            all = sysUserService.getAll(0);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return all;
    }

    /**
     * 新增用户
     *
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson<Object> add(SysUser user, HttpServletRequest request){
//        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
//
//        if(user == null){
//            ResultJson<Object> rj = new ResultJson<>(WebConstants.OPERATION_FAILURE);
//            return rj;
//        }

        return sysUserService.add(user);
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

    @RequestMapping("toUser")
    public String toUser(HttpServletRequest request, Model model){
        String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);
        LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);
        SysUser user = sysUserService.getById(Integer.valueOf(loginUser.getUserId()));
        model.addAttribute("user",user);
        return "user/user";
    }
}
