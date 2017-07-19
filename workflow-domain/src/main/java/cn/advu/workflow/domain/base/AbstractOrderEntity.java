package cn.advu.workflow.domain.base;

import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class AbstractOrderEntity extends AbstractEntity {

    private String orderNum;

    private List<BaseOrderCpm> baseOrderCpmList;

    private String cpmJsonStr;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public List<BaseOrderCpm> getBaseOrderCpmList() {
        return baseOrderCpmList;
    }

    public void setBaseOrderCpmList(List<BaseOrderCpm> baseOrderCpmList) {
        this.baseOrderCpmList = baseOrderCpmList;
    }

    public String getCpmJsonStr() {
        return cpmJsonStr;
    }

    public void setCpmJsonStr(String cpmJsonStr) {
        this.cpmJsonStr = cpmJsonStr;
    }

}