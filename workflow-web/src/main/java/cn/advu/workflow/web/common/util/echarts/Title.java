package cn.advu.workflow.web.common.util.echarts;

public class Title {
    private String text;
    private String subtext;
    public Title() {
        super();
    }
    public Title(String text, String subtext) {
        this.text = text;
        this.subtext = subtext;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getSubtext() {
        return subtext;
    }
    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }
}
