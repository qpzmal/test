package cn.advu.workflow.web.vo;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;

/**
 * Created by weiqz on 2017/7/2.
 */
public class BaseExecuteOrderVO extends WorkflowVO{

    private BaseExecuteOrder baseExecuteOrder;

    public BaseExecuteOrder getBaseExecuteOrder() {
        return baseExecuteOrder;
    }

    public void setBaseExecuteOrder(BaseExecuteOrder baseExecuteOrder) {
        this.baseExecuteOrder = baseExecuteOrder;
    }
}
