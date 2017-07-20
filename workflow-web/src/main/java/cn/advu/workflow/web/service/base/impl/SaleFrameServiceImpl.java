package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderFrameRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.AreaManager;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.manager.CustomMananger;
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
public class SaleFrameServiceImpl extends AbstractOrderService implements SaleFrameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleFrameServiceImpl.class);

    @Autowired
    private BaseExecuteOrderFrameRepo baseExecuteOrderFrameRepo;

    @Autowired
    CustomMananger customMananger;

    @Autowired
    AreaManager areaManager;

    @Autowired
    CpmManager cpmManager;

    @Override
    public ResultJson<List<BaseExecuteOrderFrame>> findAll() {
        ResultJson<List<BaseExecuteOrderFrame>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseExecuteOrderFrameRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseExecuteOrderFrame baseExecuteOrderFrame) {

        // 编码
        String orderNumSeqStr = this.buildOrderNumSeqStr();
        Integer signCustomId = baseExecuteOrderFrame.getCustomSignId();
        BaseCustom signCustom = customMananger.findById(signCustomId);
        BaseArea baseArea = areaManager.findById(baseExecuteOrderFrame.getAreaId());
        String areaCode = baseArea.getCode();
        String orderNum = "S" + findCustomType(signCustom.getCustomType()) + areaCode + orderNumSeqStr;
        baseExecuteOrderFrame.setOrderNum(orderNum);
        // 补充编码
        if (StringUtils.isEmpty(baseExecuteOrderFrame.getSecOrderNum())) {
            baseExecuteOrderFrame.setSecOrderNum(orderNum);
        }
        String userId = UserThreadLocalContext.getCurrentUser().getUserId();
        baseExecuteOrderFrame.setUserId(Integer.valueOf(userId));

        // CPM
        buildExecuteFrameCpm(baseExecuteOrderFrame);

        Integer insertCount = baseExecuteOrderFrameRepo.addSelective(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }


    @Override
    public ResultJson<Integer> update(BaseExecuteOrderFrame baseExecuteOrderFrame) {

        // CPM
        buildExecuteFrameCpm(baseExecuteOrderFrame);

        if (baseExecuteOrderFrame.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseExecuteOrderFrameRepo.update(baseExecuteOrderFrame);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseExecuteOrderFrame> findById(Integer id) {
        ResultJson<BaseExecuteOrderFrame> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        BaseExecuteOrderFrame baseExecuteOrderFrame = baseExecuteOrderFrameRepo.findOne(id);
        result.setData(baseExecuteOrderFrame);

        List<BaseOrderCpm> cpmList = cpmManager.findExecuteOrderFrameCpm(baseExecuteOrderFrame.getId());
        baseExecuteOrderFrame.setBaseOrderCpmList(cpmList);

        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {

        BaseExecuteOrderFrame baseExecuteOrderFrame = baseExecuteOrderFrameRepo.findOne(id);
        List<BaseOrderCpm> cpmList = cpmManager.findOrderCustomCpm(id);
        baseExecuteOrderFrame.setBaseOrderCpmList(cpmList);

        Integer count = baseExecuteOrderFrameRepo.logicRemove(baseExecuteOrderFrame);
        if (count == 0) {
            throw new ServiceException("需求单不存在！");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);

    }
}
