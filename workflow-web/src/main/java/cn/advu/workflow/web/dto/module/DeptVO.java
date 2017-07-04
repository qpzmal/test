package cn.advu.workflow.web.dto.module;

import cn.advu.workflow.domain.fcf_vu.BaseDept;

/**
 * 部门VO
 *
 */
public class DeptVO {

    private BaseDept baseDept;
    private String parentName;
    private String areaName;

    public BaseDept getBaseDept() {
        return baseDept;
    }

    public void setBaseDept(BaseDept baseDept) {
        this.baseDept = baseDept;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
