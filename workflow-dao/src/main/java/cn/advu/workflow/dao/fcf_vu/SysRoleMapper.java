package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseDAO<SysRole> {

    // 以下为自定义SQL
    List<SysRole> queryAll();
}