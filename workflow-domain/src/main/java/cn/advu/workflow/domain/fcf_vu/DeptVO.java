package cn.advu.workflow.domain.fcf_vu;

/**
 * 部门VO
 *
 */
public class DeptVO extends  BaseDept {

    private String parentName;
    private String areaName;

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
