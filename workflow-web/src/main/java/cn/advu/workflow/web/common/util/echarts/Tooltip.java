package cn.advu.workflow.web.common.util.echarts;

public class Tooltip {
    private String trigger = "item";

    public Tooltip() {
        super();
    }

    public Tooltip(String trigger) {
        this.trigger = trigger;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
