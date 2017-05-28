package cn.advu.workflow.web.common.util.echarts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YAxis {
    private final String type = "value";
    private final boolean scale = true;
    @JsonProperty("axisLabel")
    private AxisLabel axisLabel;
    
    public String getType() {
        return type;
    }

    public YAxis(String formatter){
        axisLabel = new AxisLabel();
        axisLabel.setFormatter(axisLabel.getFormatter() + formatter);
    }
    
    public boolean isScale() {
        return scale;
    }

    static class AxisLabel{
        private String formatter = "{value}";

        public String getFormatter() {
            return formatter;
        }

        public void setFormatter(String formatter) {
            this.formatter = formatter;
        }
    }
}
