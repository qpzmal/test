package cn.advu.workflow.web.service.system;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

public interface SysRoleService {

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    ResultJson<Object> addRole(SysRole sysRole);

    /**
     * 返回全部角色
     *
     * @return
     */
    ResultJson<List<SysRole>> findAll();
//
//    ResultJson<List<Map<String, Object>>> getAllRoleAndPers();
//
//    ResultJson<Object> addRole(Integer creatorId, String roleName, String[] permissionIds);

//    ResultJson<List<SysPermission>> getAllPermissions();
//
//    List<SysPermission> getPermissionsOf(Integer roleId);

//    ResultJson<Object> editRole(Integer creatorId, String roleName, String[] permissionIds);

}
