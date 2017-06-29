package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseFinancialindexMapper;
import cn.advu.workflow.dao.fcf_vu.BaseMediaTypeMapper;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseFinancialindexRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class BaseFinancialindexRepoImpl extends AbstractRepo<BaseFinancialindex> implements BaseFinancialindexRepo {

    @Autowired
    BaseFinancialindexMapper baseFinancialindexMapper;

    @Override
    protected BaseDAO<BaseFinancialindex> getSqlMapper() {
        return baseFinancialindexMapper;
    }

    @Override
    public List<BaseFinancialindex> findAll() {
        return baseFinancialindexMapper.queryAll(null);
    }
}
