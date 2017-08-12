package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

public class SysFuction extends AbstractEntity {

    private String name;

    private Integer resourceId;

    private String operateTypeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getOperateTypeId() {
        return operateTypeId;
    }

    public void setOperateTypeId(String operateTypeId) {
        this.operateTypeId = operateTypeId;
    }
}