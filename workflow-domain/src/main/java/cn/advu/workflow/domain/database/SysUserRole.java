package cn.advu.workflow.domain.database;

import java.util.Date;

import cn.advu.workflow.domain.base.IDomain;

/**
 * 用户角色映射实体类
 *
 * @author Niu Qianghong
 */
public class SysUserRole implements IDomain {

    private static final long serialVersionUID = 5142231611592870324L;
    private Integer userRoleId;
    private Integer userId;
    private Integer roleId;
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

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
