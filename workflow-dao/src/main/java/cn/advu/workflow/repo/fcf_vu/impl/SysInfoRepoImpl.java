package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.SysInfoMapper;
import cn.advu.workflow.domain.fcf_vu.SysInfo;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.SysInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by weiqz on 2017/8/24.
 */
@Repository
public class SysInfoRepoImpl extends AbstractRepo<SysInfo> implements SysInfoRepo {

    @Autowired
    SysInfoMapper sysInfoMapper;

    @Override
    protected BaseDAO<SysInfo> getSqlMapper() {
        return sysInfoMapper;
    }
}
