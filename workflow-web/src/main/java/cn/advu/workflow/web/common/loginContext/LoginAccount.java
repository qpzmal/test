package cn.advu.workflow.web.common.loginContext;

import cn.advu.workflow.domain.base.IDomain;
import cn.advu.workflow.domain.fcf_vu.SysFuction;

import java.util.List;

public class LoginAccount implements IDomain{
    
    private static final long serialVersionUID = -6474201385807881196L;
    
    private LoginUser user;
    private List<SysFuction> userFunction;
//    private List<SysPermission> permissions;

    public LoginUser getUser() {
        return user;
    }
    public void setUser(LoginUser user) {
        this.user = user;
    }

//    public List<SysPermission> getPermissions() {
//        return permissions;
//    }
//    public void setPermissionUrls(List<SysPermission> permissions) {
//        this.permissions = permissions;
//    }

    public List<SysFuction> getUserFunction() {
        return userFunction;
    }

    public void setUserFunction(List<SysFuction> userFunction) {
        this.userFunction = userFunction;
    }
}
