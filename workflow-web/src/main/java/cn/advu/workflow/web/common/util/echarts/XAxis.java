package cn.advu.workflow.web.common.util.echarts;

import java.util.List;

public class XAxis {
    private final String type = "category";
    private final Boolean boundaryGap = false;
    private List<String> data;
    
    public XAxis() {
        super();
    }
    
    public XAxis(List<String> data) {
        super();
        this.data = data;
    }

    public String getType() {
        return type;
    }
    public Boolean getBoundaryGap() {
        return boundaryGap;
    }
    public List<String> getData() {
        return data;
    }
    public void setData(List<String> data) {
        this.data = data;
    }
}
