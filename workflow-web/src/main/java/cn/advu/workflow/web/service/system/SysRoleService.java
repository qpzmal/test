package cn.advu.workflow.web.service.system;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
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
     * 更新角色
     *
     * @param sysRole
     * @return
     */
    ResultJson<Object> updateRole(SysRole sysRole);

    /**
     * 返回全部角色
     *
     * @return
     */
    ResultJson<List<SysRole>> findAll();

    /**
     * 返回当前角色
     *
     * @param roleId
     * @return
     */
    ResultJson<SysRole> findByRoleId(Integer roleId);

    // 用户-角色 关系

    /**
     * 批量给用户赋予角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    ResultJson<List<Integer>> addUserRole(Integer userId, List<Integer> roleIds);

    /**
     * 批量给用户更新角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    ResultJson<Void> updateUserRole(Integer userId, List<Integer> roleIds);

    /**
     * 返回用户全部角色
     *
     * @return
     */
    ResultJson<List<SysUserRole>> findUserRoleAll(Integer userId);

    /**
     * 删除用户所有角色
     *
     * @param userId
     * @return
     */
    ResultJson<Integer> removeUserRole(Integer userId);

    /**
     * 给角色赋予权限
     *
     * @param functionIds
     * @param roleId
     * @return
     */
    ResultJson<Void> auth(List<Integer> functionIds, Integer roleId);

}
