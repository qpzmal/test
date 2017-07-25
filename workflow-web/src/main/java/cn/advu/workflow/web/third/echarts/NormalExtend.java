package cn.advu.workflow.web.third.echarts;

import com.github.abel533.echarts.style.itemstyle.Normal;

/**
 * Created by weiqz on 2017/7/24.
 */
public class NormalExtend extends Normal {
    private String position;
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}