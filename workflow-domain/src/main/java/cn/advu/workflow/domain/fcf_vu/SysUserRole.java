package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

public class SysUserRole extends AbstractEntity {
    private Integer admins; // 用户id

    private Integer roles; // 角色id

    private String roleName; // 角色名称

    private String activitiName; // activiti名称

    public Integer getAdmins() {
        return admins;
    }

    public void setAdmins(Integer admins) {
        this.admins = admins;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getActivitiName() {
        return activitiName;
    }

    public void setActivitiName(String activitiName) {
        this.activitiName = activitiName;
    }
}