package cn.advu.workflow.domain.database;

import cn.advu.workflow.domain.base.IDomain;

import java.util.Date;

/**
 * 角色权限映射实体类
 * @author Niu Qianghong
 *
 */
public class SysRolePermission implements IDomain{

    private static final long serialVersionUID = 8575309977548734058L;
    private Integer rolePermissionId;
    private Integer roleId;
    private Integer permissionId;
    private Integer creatorId;
    private Date createTime;
    private Date updateTime;
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getRolePermissionId() {
        return rolePermissionId;
    }
    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getPermissionId() {
        return permissionId;
    }
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    public Integer getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
