package cn.advu.workflow.domain.database;

import cn.advu.workflow.domain.base.IDomain;

import java.util.Date;

/**
 * 权限菜单映射实体类
 *
 * @author Niu Qianghong
 */
public class SysPermissionMenu implements IDomain {
    private static final long serialVersionUID = 1920453082812620014L;
    private Integer permissionMenuId;
    private Integer menuId;
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

    public Integer getPermissionMenuId() {
        return permissionMenuId;
    }

    public void setPermissionMenuId(Integer permissionMenuId) {
        this.permissionMenuId = permissionMenuId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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
