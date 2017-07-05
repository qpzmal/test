package cn.advu.workflow.web.vo;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;

/**
 * Created by weiqz on 2017/7/2.
 */
public class BaseBuyOrderVO extends WorkflowVO{

    private BaseBuyOrder baseBuyOrder;

    public BaseBuyOrder getBaseBuyOrder() {
        return baseBuyOrder;
    }

    public void setBaseBuyOrder(BaseBuyOrder baseBuyOrder) {
        this.baseBuyOrder = baseBuyOrder;
    }
}
