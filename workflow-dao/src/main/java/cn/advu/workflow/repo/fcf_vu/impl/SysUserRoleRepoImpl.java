package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.SysUserRoleMapper;
import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class SysUserRoleRepoImpl extends AbstractRepo<SysUserRole> implements SysUserRoleRepo {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    protected BaseDAO<SysUserRole> getSqlMapper() {

        return sysUserRoleMapper;
    }

    @Override
    public List<SysUserRole> findAll() {

        return sysUserRoleMapper.queryAll();
    }

    @Override
    public List<SysUserRole> findUserRole(Integer userId) {

        return sysUserRoleMapper.queryByUser(userId);
    }

    @Override
    public List<SysUserRole> findRoleUser(Integer roleId) {
        return sysUserRoleMapper.queryByRole(roleId);
    }

    @Override
    public int removeUserRole(Integer userId) {

        return sysUserRoleMapper.deleteByUser(userId);
    }

    @Override
    public int removeUserRole(List<Integer> userRoleIds) {
        return sysUserRoleMapper.deleteByIds(userRoleIds);
    }

    @Override
    public int removeRoleUser(Integer roleId) {

        return sysUserRoleMapper.deleteByRole(roleId);
    }

}
