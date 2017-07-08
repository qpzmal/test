package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderFrameMapper;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderMapper;
import cn.advu.workflow.dao.fcf_vu.BaseOrderCpmMapper;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.repo.base.impl.AbstractOrderRepo;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderFrameRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 需求框架
 *
 */
@Repository
public class BaseExecuteOrderFrameRepoImpl extends AbstractOrderRepo<BaseExecuteOrderFrame> implements BaseExecuteOrderFrameRepo {

    @Autowired
    BaseExecuteOrderFrameMapper baseExecuteOrderFrameMapper;

    @Override
    protected BaseDAO<BaseExecuteOrderFrame> getSqlMapper() {
        return baseExecuteOrderFrameMapper;
    }

    @Override
    public List<BaseExecuteOrderFrame> findAll() {
        return baseExecuteOrderFrameMapper.queryAll(null);
    }

}
