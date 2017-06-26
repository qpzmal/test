package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

import java.util.Date;

public class BaseIndustry extends AbstractEntity {

    private String name;

    private Boolean itemStatus;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

}