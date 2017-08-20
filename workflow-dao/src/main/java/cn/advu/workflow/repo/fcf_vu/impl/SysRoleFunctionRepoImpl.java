package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.SysRoleFuctionMapper;
import cn.advu.workflow.domain.fcf_vu.SysRoleFuction;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.SysRoleFunctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class SysRoleFunctionRepoImpl extends AbstractRepo<SysRoleFuction> implements SysRoleFunctionRepo {

    @Autowired
    SysRoleFuctionMapper sysRoleFuctionMapper;

    @Override
    protected BaseDAO<SysRoleFuction> getSqlMapper() {
        return sysRoleFuctionMapper;
    }

    @Override
    public List<SysRoleFuction> findByRole(Integer roleId) {
        return sysRoleFuctionMapper.queryByRole(roleId);
    }
    @Override
    public void removeByIds(List<Integer> ids) {
        sysRoleFuctionMapper.deleteByIds(ids);
    }

    @Override
    public void removeByRole(Integer roleId) {
        sysRoleFuctionMapper.deleteByRole(roleId);
    }
}
