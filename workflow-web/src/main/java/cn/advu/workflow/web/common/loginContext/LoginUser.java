package cn.advu.workflow.web.common.loginContext;

import java.io.Serializable;

/**
 * Created by kai on 15/8/5.
 */
public class LoginUser implements Serializable {
    private long userId;

    private String userName;

    private String realName;

    private Long loginTime;

    private Long departId;

    private Integer leader;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
