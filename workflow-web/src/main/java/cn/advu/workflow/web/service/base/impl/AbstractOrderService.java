package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.SequenceMapper;
import cn.advu.workflow.domain.base.AbstractOrderEntity;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.web.common.ResultJson;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
public class AbstractOrderService {

    @Autowired
    SequenceMapper sequenceMapper;


    protected String buildOrderNumSeqStr() {
        Integer orderNumSeq = sequenceMapper.nextVal();
        return String.format("%04d", orderNumSeq);
    }

    protected <T extends AbstractOrderEntity> void buildCpm(T orderEntity) {
        String cpmJsonStr = orderEntity.getCpmJsonStr();
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
            orderEntity.setBaseOrderCpmList(baseOrderCpmList);
        }
    }
}
