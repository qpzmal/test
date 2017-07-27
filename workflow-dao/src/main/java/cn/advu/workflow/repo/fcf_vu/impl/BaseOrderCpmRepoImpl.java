package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderMapper;
import cn.advu.workflow.dao.fcf_vu.BaseOrderCpmMapper;
import cn.advu.workflow.domain.enums.CpmTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
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

    @Override
    public List<BaseOrderCpmVO> findByCustomOrderCpm(Integer orderId) {
        return baseOrderCpmMapper.selectByOrderAndType(orderId, Integer.valueOf(CpmTypeEnum.CUSTOM.getValue()));
    }

    @Override
    public List<BaseOrderCpmVO> findByBuyOrderCpm(Integer orderId) {
        return baseOrderCpmMapper.selectByOrderAndType(orderId, Integer.valueOf(CpmTypeEnum.BUY.getValue()));
    }
    @Override
    public List<BaseOrderCpmVO> findByExecuteOrderFrameCpm(Integer orderId) {
        return baseOrderCpmMapper.selectByOrderAndType(orderId, Integer.valueOf(CpmTypeEnum.EXECUTE_FRAME.getValue()));
    }

    @Override
    public List<BaseOrderCpmVO> findExecuteOrderCpm(Integer orderId) {
        return baseOrderCpmMapper.selectExecuteOrderCpm(orderId);
    }

    @Override
    public List<BaseOrderCpmVO> findByBuyOrderFrameCpm(Integer orderId) {
        return baseOrderCpmMapper.selectByOrderAndType(orderId, Integer.valueOf(CpmTypeEnum.BUY_FRAME.getValue()));
    }

    @Override
    public List<BaseOrderCpmVO> findPassBuyOrderCpm() {
        return baseOrderCpmMapper.selectPassBuyOrderCpm();
    }

}
