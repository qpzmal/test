package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseAreaFinanceMapper;
import cn.advu.workflow.dao.fcf_vu.BaseCustomFinanceMapper;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAreaFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomFinanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 区域财务核算
 *
 */
@Repository
public class BaseCustomFinanceRepoImpl extends AbstractRepo<BaseCustomFinance> implements BaseCustomFinanceRepo {

    @Autowired
    BaseCustomFinanceMapper baseCustomFinanceMapper;

    @Override
    protected BaseDAO<BaseCustomFinance> getSqlMapper() {
        return baseCustomFinanceMapper;
    }


    @Override
    public List<BaseCustomFinance> findByCustom(Integer customId) {
        return baseCustomFinanceMapper.queryByCustom(customId);
    }
}
