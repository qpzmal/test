package cn.advu.workflow.web.dto.system;

import cn.advu.workflow.domain.fcf_vu.SysRole;

/**
 * 用户角色
 */
public class UserRole {
    private SysRole sysRole;
    private Boolean selected;

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
