package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractTreeEntity;

public class BaseDept extends AbstractTreeEntity {

    private String name;

    private Integer sort;

    private String description;

    private Integer areaId;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

}