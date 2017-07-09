package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseAreaFinanceMapper;
import cn.advu.workflow.dao.fcf_vu.BaseAreaMapper;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.base.impl.AbstractTreeRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAreaFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 区域财务核算
 *
 */
@Repository
public class BaseAreaFinanceRepoImpl extends AbstractRepo<BaseAreaFinance> implements BaseAreaFinanceRepo {

    @Autowired
    BaseAreaFinanceMapper baseAreaFinanceMapper;

    @Override
    protected BaseDAO<BaseAreaFinance> getSqlMapper() {
        return baseAreaFinanceMapper;
    }


    @Override
    public List<BaseAreaFinance> findByArea(Integer areaId) {
        return baseAreaFinanceMapper.queryByArea(areaId);
    }
}
