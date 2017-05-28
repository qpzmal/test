package cn.advu.workflow.web.service.system;

import cn.advu.workflow.domain.database.SysPermission;
import cn.advu.workflow.domain.database.SysRole;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;
import java.util.Map;

public interface RoleService {

    ResultJson<List<Map<String, Object>>> getAllRoleAndPers();

    ResultJson<Object> addRole(Integer creatorId, String roleName, String[] permissionIds);

    ResultJson<List<SysPermission>> getAllPermissions();

    List<SysPermission> getPermissionsOf(Integer roleId);

    ResultJson<Object> editRole(Integer creatorId, String roleName, String[] permissionIds);

    ResultJson<List<SysRole>> getAll();

}
