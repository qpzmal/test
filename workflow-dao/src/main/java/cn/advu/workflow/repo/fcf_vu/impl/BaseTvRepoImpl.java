package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseTvMapper;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseTv;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseTvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 媒体
 *
 */
@Repository
public class BaseTvRepoImpl extends AbstractRepo<BaseTv> implements BaseTvRepo {

    @Autowired
    BaseTvMapper baseTvMapper;

    @Override
    protected BaseDAO<BaseTv> getSqlMapper() {
        return baseTvMapper;
    }

    @Override
    public List<BaseTv> findAll() {
        return baseTvMapper.queryAll(null);
    }
}
