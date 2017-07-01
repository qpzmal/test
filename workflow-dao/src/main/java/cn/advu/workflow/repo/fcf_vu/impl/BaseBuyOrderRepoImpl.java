package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseBuyOrderMapper;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderMapper;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 需求订单
 *
 */
@Repository
public class BaseBuyOrderRepoImpl extends AbstractRepo<BaseBuyOrder> implements BaseBuyOrderRepo {

    @Autowired
    BaseBuyOrderMapper baseBuyOrderMapper;

    @Override
    protected BaseDAO<BaseBuyOrder> getSqlMapper() {
        return baseBuyOrderMapper;
    }

    @Override
    public List<BaseBuyOrder> findAll() {
        return baseBuyOrderMapper.queryAll(null);
    }
}
