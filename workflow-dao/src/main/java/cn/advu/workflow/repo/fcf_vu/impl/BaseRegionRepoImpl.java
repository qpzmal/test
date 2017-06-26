package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseRegionMapper;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseRegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class BaseRegionRepoImpl extends AbstractRepo<BaseRegion> implements BaseRegionRepo {

    @Autowired
    BaseRegionMapper baseRegionMapper;

    @Override
    protected BaseDAO<BaseRegion> getSqlMapper() {
        return baseRegionMapper;
    }

    @Override
    public List<BaseRegion> findAll() {
        return baseRegionMapper.queryAll();
    }
}
