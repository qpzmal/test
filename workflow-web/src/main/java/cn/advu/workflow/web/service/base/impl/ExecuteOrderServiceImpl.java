package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

        String cpmJsonStr = baseExecuteOrder.getCpmJsonStr();
        if (StringUtils.isNotEmpty(cpmJsonStr)) {
            JSONArray cpmJsonArr = JSONArray.parseArray(cpmJsonStr);
            List<BaseOrderCpm> baseOrderCpmList = new ArrayList<>();
            for (Object cpmObject : cpmJsonArr) {
                JSONObject cpmJsonObject = (JSONObject)cpmObject;
                BaseOrderCpm baseOrderCpm = new BaseOrderCpm();
                baseOrderCpm.setOrderCpmType("1");// 类型：1，客户需求CPM 2,执行排期CPM 3，第三方检测CPM
                baseOrderCpm.setMediaId(cpmJsonObject.getInteger("mediaId"));
                baseOrderCpm.setMediaPrice(cpmJsonObject.getBigDecimal("mediaPrice"));
                baseOrderCpm.setFirstPrice(cpmJsonObject.getBigDecimal("firstPrice"));
                baseOrderCpm.setAdTypeId(cpmJsonObject.getInteger("adTypeId"));
                baseOrderCpm.setCpm(cpmJsonObject.getInteger("cpm"));
                baseOrderCpm.setRemark(cpmJsonObject.getString("remark"));
                baseOrderCpmList.add(baseOrderCpm);
            }
            baseExecuteOrder.setBaseOrderCpmList(baseOrderCpmList);
        }

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
