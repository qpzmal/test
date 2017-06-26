package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.system.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色相关controller, 用于系统设置——角色管理
 * @author Niu Qianghong
 *
 */

@Controller
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 跳转角色业务首页-角色列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<SysRole>> result = sysRoleService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "role/index_list";
    }

    /**
     * 新增角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Object> addRole(SysRole sysRole, HttpServletRequest request){
        return sysRoleService.addRole(sysRole);
    }

//
//    /**
//     * 查询所有角色, 状态及其权限
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("getRolesAndPermissions")
//    public ResultJson<List<Map<String, Object>>> getRolesAndPermissions(){
//        return roleService.getAllRoleAndPers();
//    }
//
//    /**
//     * 查询并返回所有可用的权限
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("getAllPermissions")
//    public ResultJson<List<SysPermission>> getAllPermissions(){
//        return roleService.getAllPermissions();
//    }
//

//
//    @RequestMapping("/manage")
//    public String toManage(){
//        return "systemSetting/roleManage";
//    }
//
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "role/add";
    }
//
//    @RequestMapping("/toEdit")
//    public String toEdit(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
//        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
//        String roleName = new String(request.getParameter("roleName").getBytes("iso8859-1"),"utf-8");
//        List<SysPermission> permissions = roleService.getPermissionsOf(roleId);
//        model.addAttribute("perms", permissions);
//        model.addAttribute("roleName", roleName);
//        return "systemSetting/editRole";
//    }
//
//    @ResponseBody
//    @RequestMapping("/edit")
//    public ResultJson<Object> edotRole(HttpServletRequest request){
//        String roleName = request.getParameter("roleName");
//        String[] permissionIds = request.getParameter("permissionId").split("-");
//        Integer creatorId = Integer.valueOf(request.getParameter("creatorId"));
//        return roleService.editRole(creatorId, roleName, permissionIds);
//    }
//
//    @ResponseBody
//    @RequestMapping("/getAll")
//    public ResultJson<List<SysRole>> getAll(){
//        return roleService.getAll();
//    }
}
