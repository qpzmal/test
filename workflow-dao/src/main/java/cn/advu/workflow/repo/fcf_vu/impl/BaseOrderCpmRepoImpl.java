package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderMapper;
import cn.advu.workflow.dao.fcf_vu.BaseOrderCpmMapper;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import cn.advu.workflow.repo.fcf_vu.BaseOrderCpmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 需求订单
 *
 */
@Repository
public class BaseOrderCpmRepoImpl extends AbstractRepo<BaseOrderCpm> implements BaseOrderCpmRepo {

    @Autowired
    BaseOrderCpmMapper baseOrderCpmMapper;

    @Override
    protected BaseDAO<BaseOrderCpm> getSqlMapper() {
        return baseOrderCpmMapper;
    }

}
