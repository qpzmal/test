package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysRoleFuction;
import cn.advu.workflow.repo.fcf_vu.SysRoleFunctionRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.dto.system.RoleAuthDTO;
import cn.advu.workflow.web.service.system.SysRoleService;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
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
        return "system/role/list";
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

    /**
     * 更新角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Object> updateRole(SysRole sysRole, HttpServletRequest request){
        return sysRoleService.updateRole(sysRole);
    }

    /**
     * 删除角色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/remove", method = RequestMethod.POST)
    public ResultJson<Void> updateRole(Integer id, HttpServletRequest request){
        return sysRoleService.removeRole(id);
    }

    /**
     * 跳转至新增角色页
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "system/role/add";
    }

    /**
     * 跳转至角色授权页
     *
     * @return
     */
    @RequestMapping("/toAuth")
    public String toAuth(Integer roleId, Model resultModel){
        resultModel.addAttribute("roleId", roleId);
        resultModel.addAttribute("roleFunctionList", sysRoleService.findRoleFuntionList(roleId).getData());
        return "system/role/auth";
    }

    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer roleId, Model model){

        SysRole sysRole = sysRoleService.findByRoleId(roleId).getData();

        model.addAttribute("sysRole", sysRole);

        return "system/role/update";
    }

    /**
     * 跳转修改页
     *
     * @param roleAuthDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/auth", method = RequestMethod.POST)
    public ResultJson<Void> auth(RoleAuthDTO roleAuthDTO){

        ResultJson result = new ResultJson(WebConstants.OPERATION_SUCCESS);

        String functionListJsonStr = roleAuthDTO.getFunctionListJsonStr();
        Integer roleId = roleAuthDTO.getRoleId();

        if (StringUtils.isEmpty(functionListJsonStr)) {
            result.setCode(WebConstants.OPERATION_FAILURE);
            result.setInfo("参数格式不正确！");
            return result;
        }

        JSONArray functionListArr = JSONArray.parseArray(functionListJsonStr);
        if (functionListArr == null || functionListArr.isEmpty()) {
            result.setCode(WebConstants.OPERATION_FAILURE);
            result.setInfo("没给角色设置权限！");
            return result;
        }

        List<Integer> functionList = new LinkedList<>();
        for (Object functionId : functionListArr) {
            functionList.add(Integer.valueOf(String.valueOf(functionId)));
        }
        sysRoleService.auth(functionList, roleId);
        return result;
    }

}
