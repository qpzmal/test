package cn.advu.workflow.web.vo;

import java.util.List;

/**
 * Created by weiqz on 2017/7/16.
 */
public class MenuVO {
    private Integer userLevel;
    private List<String> workflowStart;
    private List<String> workflowAudit;
    private List<String> reportInfo;
    private List<String> baseInfo;
    private List<String> systemInfo;

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public List<String> getWorkflowStart() {
        return workflowStart;
    }

    public void setWorkflowStart(List<String> workflowStart) {
        this.workflowStart = workflowStart;
    }

    public List<String> getWorkflowAudit() {
        return workflowAudit;
    }

    public void setWorkflowAudit(List<String> workflowAudit) {
        this.workflowAudit = workflowAudit;
    }

    public List<String> getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(List<String> reportInfo) {
        this.reportInfo = reportInfo;
    }

    public List<String> getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(List<String> baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<String> getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(List<String> systemInfo) {
        this.systemInfo = systemInfo;
    }
}
