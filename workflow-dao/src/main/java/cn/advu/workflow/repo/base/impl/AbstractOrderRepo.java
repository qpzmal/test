package cn.advu.workflow.repo.base.impl;


import cn.advu.workflow.dao.fcf_vu.BaseOrderCpmMapper;
import cn.advu.workflow.domain.base.AbstractOrderEntity;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class AbstractOrderRepo<T extends AbstractOrderEntity> extends AbstractRepo<T> {

    @Autowired
    BaseOrderCpmMapper baseOrderCpmMapper;

    @Override
    public int addSelective(T entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().insertSelective(entity);

            setCpmList(entity);

            List<BaseOrderCpm> baseOrderCpmList = entity.getBaseOrderCpmList();
            if (baseOrderCpmList != null && !baseOrderCpmList.isEmpty()) {
                for (BaseOrderCpm baseOrderCpm : baseOrderCpmList) {
                    baseOrderCpm.setOrderId(entity.getId());
                    baseOrderCpmMapper.insertSelective(baseOrderCpm);
                }
            }
        }

        return count;
    }

    /**
     * 设置CPM列表
     *
     * @param entity
     */
    private void setCpmList(T entity) {
        String cpmJsonStr = entity.getCpmJsonStr();
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
            entity.setBaseOrderCpmList(baseOrderCpmList);
        }
    }
}
