package cn.advu.workflow.domain.fcf_vu;

public class SysLogWithBLOBs extends SysLog {
    private String content;

    private String parameter;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }
}