package cn.advu.workflow.domain.base;

import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class AbstractOrderEntity extends AbstractEntity {

    private String orderNum;

    private List<BaseOrderCpmVO> baseOrderCpmList;

    private String cpmJsonStr;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public List<BaseOrderCpmVO> getBaseOrderCpmList() {
        return baseOrderCpmList;
    }

    public void setBaseOrderCpmList(List<BaseOrderCpmVO> baseOrderCpmList) {
        this.baseOrderCpmList = baseOrderCpmList;
    }

    public String getCpmJsonStr() {
        return cpmJsonStr;
    }

    public void setCpmJsonStr(String cpmJsonStr) {
        this.cpmJsonStr = cpmJsonStr;
    }

}