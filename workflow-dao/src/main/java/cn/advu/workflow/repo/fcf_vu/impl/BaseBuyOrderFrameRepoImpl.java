package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseBuyOrderFrameMapper;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderFrameMapper;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import cn.advu.workflow.repo.base.impl.AbstractOrderRepo;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderFrameRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderFrameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 需求框架
 *
 */
@Repository
public class BaseBuyOrderFrameRepoImpl extends AbstractOrderRepo<BaseBuyOrderFrame> implements BaseBuyOrderFrameRepo {

    @Autowired
    BaseBuyOrderFrameMapper baseBuyOrderFrameMapper;

    @Override
    protected BaseDAO<BaseBuyOrderFrame> getSqlMapper() {
        return baseBuyOrderFrameMapper;
    }

    @Override
    public List<BaseBuyOrderFrame> findAll() {
        return baseBuyOrderFrameMapper.queryAll(null);
    }

}
