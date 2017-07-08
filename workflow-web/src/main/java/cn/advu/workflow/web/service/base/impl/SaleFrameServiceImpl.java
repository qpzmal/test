package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderFrameRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.SaleFrameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class SaleFrameServiceImpl implements SaleFrameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleFrameServiceImpl.class);

    @Autowired
    private BaseExecuteOrderFrameRepo baseExecuteOrderFrameRepo;

    @Override
    public ResultJson<List<BaseExecuteOrderFrame>> findAll() {
        ResultJson<List<BaseExecuteOrderFrame>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseExecuteOrderFrameRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseExecuteOrderFrame baseExecuteOrderFrame) {

        Integer insertCount = baseExecuteOrderFrameRepo.addSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }


    @Override
    public ResultJson<Integer> update(BaseExecuteOrderFrame baseExecuteOrderFrame) {
        if (baseExecuteOrderFrame.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseExecuteOrderFrameRepo.updateSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseExecuteOrderFrame> findById(Integer id) {
        ResultJson<BaseExecuteOrderFrame> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseExecuteOrderFrameRepo.findOne(id));
        return result;
    }
}
