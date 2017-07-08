package cn.advu.workflow.web.dto.system;

/**
 * Created by wangry on 17/7/8.
 */
public class RoleAuthDTO {
    private String functionListJsonStr;
    private Integer roleId;

    public String getFunctionListJsonStr() {
        return functionListJsonStr;
    }

    public void setFunctionListJsonStr(String functionListJsonStr) {
        this.functionListJsonStr = functionListJsonStr;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
