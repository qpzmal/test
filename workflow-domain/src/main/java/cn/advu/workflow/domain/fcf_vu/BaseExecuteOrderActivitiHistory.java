package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractOrderEntity;

public class BaseExecuteOrderActivitiHistory extends AbstractOrderEntity {

    private Integer ececuteOrderId;

    private Integer activitiId;

    public Integer getEcecuteOrderId() {
        return ececuteOrderId;
    }

    public void setEcecuteOrderId(Integer ececuteOrderId) {
        this.ececuteOrderId = ececuteOrderId;
    }

    public Integer getActivitiId() {
        return activitiId;
    }

    public void setActivitiId(Integer activitiId) {
        this.activitiId = activitiId;
    }

}