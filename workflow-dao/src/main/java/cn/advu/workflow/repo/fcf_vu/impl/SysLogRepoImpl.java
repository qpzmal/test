package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.SysLogMapper;
import cn.advu.workflow.dao.fcf_vu.SysRoleFuctionMapper;
import cn.advu.workflow.domain.fcf_vu.SysLog;
import cn.advu.workflow.domain.fcf_vu.SysLogWithBLOBs;
import cn.advu.workflow.domain.fcf_vu.SysRoleFuction;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.SysLogRepo;
import cn.advu.workflow.repo.fcf_vu.SysRoleFunctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class SysLogRepoImpl extends AbstractRepo<SysLogWithBLOBs> implements SysLogRepo {

    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    protected BaseDAO<SysLogWithBLOBs> getSqlMapper() {
        return sysLogMapper;
    }

    @Override
    public List<SysLogWithBLOBs> findAllLog() {
        return sysLogMapper.queryAll();
    }
}
