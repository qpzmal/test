package cn.advu.workflow.web.vo;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;

/**
 * Created by weiqz on 2017/7/2.
 */
public class BaseExecuteOrderFrameVO extends WorkflowVO{

    private BaseExecuteOrderFrame baseExecuteOrderFrame;

    public BaseExecuteOrderFrame getBaseExecuteOrderFrame() {
        return baseExecuteOrderFrame;
    }

    public void setBaseExecuteOrderFrame(BaseExecuteOrderFrame baseExecuteOrderFrame) {
        this.baseExecuteOrderFrame = baseExecuteOrderFrame;
    }
}
