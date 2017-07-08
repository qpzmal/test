package cn.advu.workflow.domain.base;

import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderEntity extends AbstractEntity {


    private List<BaseOrderCpm> baseOrderCpmList;

    private String cpmJsonStr;

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