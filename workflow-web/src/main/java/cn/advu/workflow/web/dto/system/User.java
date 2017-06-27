package cn.advu.workflow.web.dto.system;

import cn.advu.workflow.domain.fcf_vu.SysUser;

import java.util.List;

/**
 * 用户
 */
public class User {

    private SysUser sysUser;

    private List<Integer> userRoleIdList;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<Integer> getUserRoleIdList() {
        return userRoleIdList;
    }

    public void setUserRoleIdList(List<Integer> userRoleIdList) {
        this.userRoleIdList = userRoleIdList;
    }
}
