package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.SysUserRoleKey;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);
}