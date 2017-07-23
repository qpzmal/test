package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

public class BaseMedia extends AbstractEntity {

    private String code;

    private String name;

    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}