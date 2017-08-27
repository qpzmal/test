package cn.advu.workflow.web.vo;

import java.util.Date;

/**
 * Created by weiqz on 2017/8/27.
 */
public class DataBoardVO implements Comparable<DataBoardVO> {
    private String id;
    private String name;
    private Date updateTime; // 更新时间
    private String processDefinitionKey;
    private String processInstanceId;
    private String taskDefKey;
    private Integer wfStep; // 工作流步骤数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public Integer getWfStep() {
        return wfStep;
    }

    public void setWfStep(Integer wfStep) {
        this.wfStep = wfStep;
    }

    @Override
    public int compareTo(DataBoardVO o) {
        return this.getUpdateTime().compareTo(o.getUpdateTime());
    }
}
