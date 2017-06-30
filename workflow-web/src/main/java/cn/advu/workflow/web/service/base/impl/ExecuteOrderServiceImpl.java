package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class ExecuteOrderServiceImpl implements ExecuteOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteOrderServiceImpl.class);

    @Autowired
    private BaseExecuteOrderRepo baseExecuteOrderRepo;

    @Override
    public ResultJson<List<BaseExecuteOrder>> findAll() {
        ResultJson<List<BaseExecuteOrder>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseExecuteOrderRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseExecuteOrder baseExecuteOrder) {
        Integer insertCount = baseExecuteOrderRepo.addSelective(baseExecuteOrder);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> update(BaseExecuteOrder baseExecuteOrder) {
        if (baseExecuteOrder.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseExecuteOrderRepo.updateSelective(baseExecuteOrder);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseExecuteOrder> findById(Integer id) {
        ResultJson<BaseExecuteOrder> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseExecuteOrderRepo.findOne(id));
        return result;
    }
}
