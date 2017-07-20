package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderFrameRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.BuyFrameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class BuyFrameServiceImpl implements BuyFrameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyFrameServiceImpl.class);

    @Autowired
    private BaseBuyOrderFrameRepo baseBuyOrderFrameRepo;

    @Override
    public ResultJson<List<BaseBuyOrderFrame>> findAll() {
        ResultJson<List<BaseBuyOrderFrame>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        BaseBuyOrderFrame param = new BaseBuyOrderFrame();
        param.setStatus((byte) 0);
        result.setData(baseBuyOrderFrameRepo.findAll(param));
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseBuyOrderFrame baseExecuteOrderFrame) {

        Integer insertCount = baseBuyOrderFrameRepo.addSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }


    @Override
    public ResultJson<Integer> update(BaseBuyOrderFrame baseExecuteOrderFrame) {
        if (baseExecuteOrderFrame.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseBuyOrderFrameRepo.updateSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseBuyOrderFrame> findById(Integer id) {
        ResultJson<BaseBuyOrderFrame> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseBuyOrderFrameRepo.findOne(id));
        return result;
    }
}
