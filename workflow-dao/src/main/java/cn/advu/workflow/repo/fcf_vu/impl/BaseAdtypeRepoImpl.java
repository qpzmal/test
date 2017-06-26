package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseAdtypeMapper;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 媒体
 *
 */
@Repository
public class BaseAdtypeRepoImpl extends AbstractRepo<BaseAdtype> implements BaseAdtypeRepo {

    @Autowired
    BaseAdtypeMapper baseAdtypeMapper;

    @Override
    protected BaseDAO<BaseAdtype> getSqlMapper() {
        return baseAdtypeMapper;
    }

    @Override
    public List<BaseAdtype> findAll() {
        return baseAdtypeMapper.queryAll(null);
    }
}
