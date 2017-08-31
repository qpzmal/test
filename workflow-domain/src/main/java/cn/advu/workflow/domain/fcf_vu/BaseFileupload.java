package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

public class BaseFileupload extends AbstractEntity {

    private String bizName;

    private String bizType;

    private String fileName;

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName == null ? null : bizName.trim();
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

}