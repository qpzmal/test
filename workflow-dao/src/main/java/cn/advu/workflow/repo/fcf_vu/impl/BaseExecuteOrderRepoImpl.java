package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseCustomMapper;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderMapper;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 需求订单
 *
 */
@Repository
public class BaseExecuteOrderRepoImpl extends AbstractRepo<BaseExecuteOrder> implements BaseExecuteOrderRepo {

    @Autowired
    BaseExecuteOrderMapper baseExecuteOrderMapper;

    @Override
    protected BaseDAO<BaseExecuteOrder> getSqlMapper() {
        return baseExecuteOrderMapper;
    }

    @Override
    public List<BaseExecuteOrder> findAll() {
        return baseExecuteOrderMapper.queryAll(null);
    }
}
