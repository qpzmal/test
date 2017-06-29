package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseCustomMapper;
import cn.advu.workflow.dao.fcf_vu.BaseFinancialindexMapper;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import cn.advu.workflow.repo.fcf_vu.BaseFinancialindexRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class BaseCustomRepoImpl extends AbstractRepo<BaseCustom> implements BaseCustomRepo {

    @Autowired
    BaseCustomMapper baseCustomMapper;

    @Override
    protected BaseDAO<BaseCustom> getSqlMapper() {
        return baseCustomMapper;
    }

    @Override
    public List<BaseCustom> findAll() {
        return baseCustomMapper.queryAll(null);
    }
}
