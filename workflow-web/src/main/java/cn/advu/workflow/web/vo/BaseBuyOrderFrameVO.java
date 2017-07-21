package cn.advu.workflow.web.vo;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;

/**
 * Created by weiqz on 2017/7/2.
 */
public class BaseBuyOrderFrameVO extends WorkflowVO{

    private BaseBuyOrderFrame baseBuyOrderFrame;

    public BaseBuyOrderFrame getBaseBuyOrderFrame() {
        return baseBuyOrderFrame;
    }

    public void setBaseBuyOrderFrame(BaseBuyOrderFrame baseBuyOrderFrame) {
        this.baseBuyOrderFrame = baseBuyOrderFrame;
    }
}
