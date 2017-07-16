package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.SysUserMapper;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class SysUserRepoImpl extends AbstractRepo<SysUser> implements SysUserRepo {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    protected BaseDAO<SysUser> getSqlMapper() {
        return sysUserMapper;
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.queryAll(null);
    }

    @Override
    public SysUser findByIdAndName(Integer id, String name) {
        return sysUserMapper.queryIdAndName(id, name);
    }

    @Override
    public List<SysUser> findByDept(Integer deptId) {
        return sysUserMapper.queryByDept(deptId);
    }
}
