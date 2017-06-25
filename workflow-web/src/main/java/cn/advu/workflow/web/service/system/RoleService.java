package cn.advu.workflow.web.service.system;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.web.common.ResultJson;

public interface RoleService {

    ResultJson<Object> addRole(SysRole sysRole);
//
//    ResultJson<List<Map<String, Object>>> getAllRoleAndPers();
//
//    ResultJson<Object> addRole(Integer creatorId, String roleName, String[] permissionIds);

//    ResultJson<List<SysPermission>> getAllPermissions();
//
//    List<SysPermission> getPermissionsOf(Integer roleId);

//    ResultJson<Object> editRole(Integer creatorId, String roleName, String[] permissionIds);

//    ResultJson<List<SysRole>> getAll();

}
