package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.CustomTypeEnum;
import cn.advu.workflow.domain.enums.IValueEnum;
import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.domain.utils.ValueEnumUtils;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.AreaManager;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.manager.CustomMananger;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class ExecuteOrderServiceImpl extends  AbstractOrderService implements ExecuteOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteOrderServiceImpl.class);

    @Autowired
    private BaseExecuteOrderRepo baseExecuteOrderRepo;

    @Autowired
    CustomMananger customMananger;

    @Autowired
    AreaManager areaManager;

    @Autowired
    CpmManager cpmManager;

    @Override
    public ResultJson<List<BaseExecuteOrder>> findAll() {
        ResultJson<List<BaseExecuteOrder>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        BaseExecuteOrder param = new BaseExecuteOrder();
        param.setStatus((byte) 0);
        result.setData(baseExecuteOrderRepo.findAll(param));
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseExecuteOrder baseExecuteOrder) {

        // 编码
        String orderNumSeqStr = this.buildOrderNumSeqStr();
        Integer signCustomId = baseExecuteOrder.getCustomSignId();
        BaseCustom signCustom = customMananger.findById(signCustomId);
        BaseArea baseArea = areaManager.findById(baseExecuteOrder.getAreaId());
        String areaCode = baseArea.getCode();
        String orderNum = "S" + findCustomType(signCustom.getCustomType()) + areaCode + orderNumSeqStr;
        baseExecuteOrder.setOrderNum(orderNum);
        // 补充编码
        if (StringUtils.isEmpty(baseExecuteOrder.getSecOrderNum())) {
            baseExecuteOrder.setSecOrderNum(orderNum);
        }
        String userId = UserThreadLocalContext.getCurrentUser().getUserId();
        baseExecuteOrder.setUserId(Integer.valueOf(userId));

        // CPM
        buildExecuteCpm(baseExecuteOrder);

        Integer insertCount = baseExecuteOrderRepo.addSelective(baseExecuteOrder);
        if(insertCount != 1){
            throw new ServiceException("创建需求单失败!");
        }

        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> update(BaseExecuteOrder baseExecuteOrder) {

        // CPM
        buildExecuteCpm(baseExecuteOrder);

        if (baseExecuteOrder.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseExecuteOrderRepo.update(baseExecuteOrder);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新需求单失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Void> remove(Integer id) {

        BaseExecuteOrder baseExecuteOrder = baseExecuteOrderRepo.findOne(id);
        List<BaseOrderCpmVO> cpmList = cpmManager.findExecuteOrderFrameCpm(id);
        baseExecuteOrder.setBaseOrderCpmList(cpmList);

        Integer count = baseExecuteOrderRepo.logicRemove(baseExecuteOrder);
        if (count == 0) {
            throw new ServiceException("需求单不存在！");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseExecuteOrder> findById(Integer id) {
        ResultJson<BaseExecuteOrder> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        BaseExecuteOrder baseExecuteOrder = baseExecuteOrderRepo.findOne(id);
        result.setData(baseExecuteOrder);

        List<BaseOrderCpmVO> cpmList = cpmManager.findOrderCustomCpm(id);
        baseExecuteOrder.setBaseOrderCpmList(cpmList);

        return result;
    }
}
