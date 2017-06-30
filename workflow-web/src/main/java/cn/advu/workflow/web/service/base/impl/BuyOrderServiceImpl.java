package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseBuyOrderMapper;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.BuyOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class BuyOrderServiceImpl implements BuyOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuyOrderServiceImpl.class);

    @Autowired
    private BaseBuyOrderRepo baseBuyOrderRepo;

    @Override
    public ResultJson<List<BaseBuyOrder>> findAll() {
        ResultJson<List<BaseBuyOrder>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseBuyOrderRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseBuyOrder baseBuyOrder) {
        Integer insertCount = baseBuyOrderRepo.addSelective(baseBuyOrder);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建采购单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> update(BaseBuyOrder baseBuyOrder) {
        if (baseBuyOrder.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseBuyOrderRepo.updateSelective(baseBuyOrder);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新采购单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseBuyOrder> findById(Integer id) {
        ResultJson<BaseBuyOrder> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseBuyOrderRepo.findOne(id));
        return result;
    }
}
