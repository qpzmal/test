package cn.advu.workflow.web.common.loginContext;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kai on 15/8/5.
 */
public class LoginUser implements Serializable {
    private String userId;

    private String userName;

    private String realName;

    private Long loginTime;

    private Long departId;

    private Integer leader;

    private List<String> userFunction;

    private List<String> sysRoleList; // 系统内置权限名（activit相关）列表

    private List<Integer> roleIdList; // 用户权限ID列表

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    public List<String> getUserFunction() {
        return userFunction;
    }

    public void setUserFunction(List<String> userFunction) {
        this.userFunction = userFunction;
    }

    public List<String> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<String> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", loginTime=" + loginTime +
                ", departId=" + departId +
                ", leader=" + leader +
                '}';
    }
}
