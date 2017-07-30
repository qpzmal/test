package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleRepo extends IRepo<SysUserRole> {

    List<SysUserRole> findAll();

    List<SysUserRole> findUserRole(Integer userId);

    List<SysUserRole> findUserRoleByName(String loginName);

    List<SysUserRole> findRoleUser(Integer roleId);

    int removeUserRole(Integer userId);

    int removeUserRole(List<Integer> userRoleIds);

    int removeRoleUser(Integer roleId);

}
