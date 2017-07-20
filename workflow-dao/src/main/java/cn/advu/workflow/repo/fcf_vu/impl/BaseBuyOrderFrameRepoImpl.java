package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseBuyOrderFrameMapper;
import cn.advu.workflow.dao.fcf_vu.BaseOrderCpmMapper;
import cn.advu.workflow.domain.enums.CpmTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import cn.advu.workflow.repo.base.impl.AbstractOrderRepo;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderFrameRepo;
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
    @Autowired
    BaseOrderCpmMapper baseOrderCpmMapper;


    @Override
    protected BaseDAO<BaseBuyOrderFrame> getSqlMapper() {
        return baseBuyOrderFrameMapper;
    }

    @Override
    public List<BaseBuyOrderFrame> findAll(BaseBuyOrderFrame baseBuyOrderFrame) {
        return baseBuyOrderFrameMapper.queryAll(baseBuyOrderFrame);
    }

    @Override
    public int addSelective(BaseBuyOrderFrame entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().insertSelective(entity);
            List<BaseOrderCpmVO> baseOrderCpmList = entity.getBaseOrderCpmList();
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
    public int update(BaseBuyOrderFrame entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().updateByPrimaryKey(entity);

            baseOrderCpmMapper.deleteByOrderAndType(entity.getId(), Integer.valueOf(CpmTypeEnum.BUY_FRAME.getValue()));

            List<BaseOrderCpmVO> baseOrderCpmList = entity.getBaseOrderCpmList();
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
    public int logicRemove(BaseBuyOrderFrame entity) {
        int count = 0;
        if (entity != null) {
            entity.setDelFlag("1");
            count = getSqlMapper().updateByPrimaryKeySelective(entity);

            List<BaseOrderCpmVO> baseOrderCpmList = entity.getBaseOrderCpmList();
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
