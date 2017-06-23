package cn.advu.workflow.domain.database;

import java.io.Serializable;

public class UserRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8065343288209095012L;

    private Long id;
    private String userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
