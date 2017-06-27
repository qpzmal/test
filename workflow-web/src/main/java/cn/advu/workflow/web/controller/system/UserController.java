package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.dto.system.User;
import cn.advu.workflow.web.dto.system.UserRole;
import cn.advu.workflow.web.service.system.SysRoleService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

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
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson<User> add(User user, HttpServletRequest request){

        ResultJson<User> resultJson = new ResultJson<>(WebConstants.OPERATION_SUCCESS);

        // 新增用户
        SysUser sysUser = user.getSysUser();
        sysUserService.add(sysUser);

        // 新增用户角色
        List<Integer> userRoleIdList = sysRoleService.addUserRole(
                sysUser.getId(),
                user.getUserRoleIdList()
        ).getData();

        user.setUserRoleIdList(userRoleIdList);
        resultJson.setData(user);

        return resultJson;
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model){

        List<UserRole> userRoleList = initUserRoleList();

        model.addAttribute("roleList", userRoleList);

        return "system/user/add";
    }

    @RequestMapping("/index_content")
    public String toIndexContent(){
        return "index_content";
    }
    
    @RequestMapping("/toList")
    public String toList(){
        return "system/user/list";
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
