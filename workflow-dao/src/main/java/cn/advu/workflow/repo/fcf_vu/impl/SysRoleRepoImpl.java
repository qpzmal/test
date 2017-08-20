package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.SysRoleMapper;
import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.SysRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class SysRoleRepoImpl extends AbstractRepo<SysRole> implements SysRoleRepo {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    protected BaseDAO<SysRole> getSqlMapper() {
        return sysRoleMapper;
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.queryAll();
    }

    @Override
    public SysRole findByIdAndName(Integer roleId, String name) {
        return sysRoleMapper.queryByIdAndName(roleId, name);
    }
    @Override
    public SysRole queryByActivitiName(String activitiName) {
        return sysRoleMapper.queryByActivitiName(activitiName);
    }


}
