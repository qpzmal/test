package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.repo.fcf_vu.BaseBuyOrderFrameRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderFrameRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.service.base.BuyFrameService;
import cn.advu.workflow.web.service.base.SaleFrameService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class BuyFrameServiceImpl extends AbstractOrderService implements BuyFrameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyFrameServiceImpl.class);

    @Autowired
    private BaseBuyOrderFrameRepo baseBuyOrderFrameRepo;
    @Autowired
    CpmManager cpmManager;

    @Override
    public ResultJson<List<BaseBuyOrderFrame>> findAll() {
        ResultJson<List<BaseBuyOrderFrame>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseBuyOrderFrameRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseBuyOrderFrame baseExecuteOrderFrame) {

        // 编码
        String orderNumSeqStr = this.buildOrderNumSeqStr();

        String orderNum = "P" + orderNumSeqStr;
        baseExecuteOrderFrame.setOrderNum(orderNum);
        // 补充编码
        if (StringUtils.isEmpty(baseExecuteOrderFrame.getSecOrderNum())) {
            baseExecuteOrderFrame.setSecOrderNum(orderNum);
        }
        String userId = UserThreadLocalContext.getCurrentUser().getUserId();
        baseExecuteOrderFrame.setUserId(Integer.valueOf(userId));

        // CPM
        buildBuyFrameCpm(baseExecuteOrderFrame);

        Integer insertCount = baseBuyOrderFrameRepo.addSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }


    @Override
    public ResultJson<Integer> update(BaseBuyOrderFrame baseExecuteOrderFrame) {

        // CPM
        buildBuyFrameCpm(baseExecuteOrderFrame);

        if (baseExecuteOrderFrame.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseBuyOrderFrameRepo.update(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseBuyOrderFrame> findById(Integer id) {
        ResultJson<BaseBuyOrderFrame> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        BaseBuyOrderFrame baseBuyOrderFrame = baseBuyOrderFrameRepo.findOne(id);
        result.setData(baseBuyOrderFrame);

        List<BaseOrderCpmVO> cpmList = cpmManager.findOrderBuyFrameCpm(id);
        baseBuyOrderFrame.setBaseOrderCpmList(cpmList);

        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {

        BaseBuyOrderFrame baseBuyOrderFrame = baseBuyOrderFrameRepo.findOne(id);
        List<BaseOrderCpmVO> cpmList = cpmManager.findOrderBuyFrameCpm(id);
        baseBuyOrderFrame.setBaseOrderCpmList(cpmList);

        Integer count = baseBuyOrderFrameRepo.logicRemove(baseBuyOrderFrame);
        if (count == 0) {
            throw new ServiceException("需求单不存在！");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }
}
