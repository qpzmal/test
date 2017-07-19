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
    @Autowired
    BaseOrderCpmMapper baseOrderCpmMapper;


    @Override
    protected BaseDAO<BaseExecuteOrderFrame> getSqlMapper() {
        return baseExecuteOrderFrameMapper;
    }

    @Override
    public List<BaseExecuteOrderFrame> findAll() {
        return baseExecuteOrderFrameMapper.queryAll(null);
    }

    @Override
    public int addSelective(BaseExecuteOrderFrame entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().insertSelective(entity);
            List<BaseOrderCpm> baseOrderCpmList = entity.getBaseOrderCpmList();
            if (baseOrderCpmList != null && !baseOrderCpmList.isEmpty()) {
                for (BaseOrderCpm baseOrderCpm : baseOrderCpmList) {
                    baseOrderCpm.setOrderId(entity.getId());
                    baseOrderCpmMapper.insertSelective(baseOrderCpm);
                }
            }
        }

        return count;
    }


    @Override
    public int update(BaseExecuteOrderFrame entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().updateByPrimaryKey(entity);

            baseOrderCpmMapper.deleteByOrderAndType(entity.getId(), 1);

            List<BaseOrderCpm> baseOrderCpmList = entity.getBaseOrderCpmList();
            if (baseOrderCpmList != null && !baseOrderCpmList.isEmpty()) {
                for (BaseOrderCpm baseOrderCpm : baseOrderCpmList) {
                    baseOrderCpm.setId(null);
                    baseOrderCpm.setOrderId(entity.getId());
                    baseOrderCpmMapper.insertSelective(baseOrderCpm);
                }
            }
        }

        return count;
    }

    @Override
    public int logicRemove(BaseExecuteOrderFrame entity) {
        int count = 0;
        if (entity != null) {
            entity.setDelFlag("1");
            count = getSqlMapper().updateByPrimaryKeySelective(entity);

            List<BaseOrderCpm> baseOrderCpmList = entity.getBaseOrderCpmList();
            if (baseOrderCpmList != null && !baseOrderCpmList.isEmpty()) {
                for (BaseOrderCpm baseOrderCpm : baseOrderCpmList) {
                    baseOrderCpm.setDelFlag("1");
                    baseOrderCpmMapper.updateByPrimaryKeySelective(baseOrderCpm);
                }
            }

        }
        return count;
    }

}
