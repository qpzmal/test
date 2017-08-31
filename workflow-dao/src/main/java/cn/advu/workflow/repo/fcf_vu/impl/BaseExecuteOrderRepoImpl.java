package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderMapper;
import cn.advu.workflow.dao.fcf_vu.BaseOrderCpmMapper;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 需求订单
 *
 */
@Repository
public class BaseExecuteOrderRepoImpl extends AbstractRepo<BaseExecuteOrder> implements BaseExecuteOrderRepo {

    @Autowired
    BaseExecuteOrderMapper baseExecuteOrderMapper;
    @Autowired
    BaseOrderCpmMapper baseOrderCpmMapper;

    @Override
    protected BaseDAO<BaseExecuteOrder> getSqlMapper() {
        return baseExecuteOrderMapper;
    }

    @Override
    public List<BaseExecuteOrder> findAll(BaseExecuteOrder baseExecuteOrder) {
        return baseExecuteOrderMapper.queryAll(baseExecuteOrder);
    }
    @Override
    public List<BaseExecuteOrder> queryAllForContract(BaseExecuteOrder baseExecuteOrder) {
        return baseExecuteOrderMapper.queryAllForContract(baseExecuteOrder);
    }
    @Override
    public List<BaseExecuteOrder> findAllUnFinished() {
        return baseExecuteOrderMapper.findAllUnFinished();
    }

    @Override
    public List<Map> finalReport(String likeSearch, List<String> mediaIdList) {
        return baseExecuteOrderMapper.queryFinalReport(likeSearch, mediaIdList);
    }

    @Override
    public List<Map> reminderPaymentList(String days, String bizId) {
        return baseExecuteOrderMapper.reminderPaymentList(days, bizId);
    }

    @Override
    public int addSelective(BaseExecuteOrder entity) {
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
    public int update(BaseExecuteOrder entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().updateByPrimaryKey(entity);

            baseOrderCpmMapper.deleteByOrderAndType(entity.getId(), 1);

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
    public int logicRemove(BaseExecuteOrder entity) {
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
