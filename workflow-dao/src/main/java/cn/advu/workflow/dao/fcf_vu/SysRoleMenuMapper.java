package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.SysRoleMenu;

public interface SysRoleMenuMapper extends ISqlMapper {
    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);
}