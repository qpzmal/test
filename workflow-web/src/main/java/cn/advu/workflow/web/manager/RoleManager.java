package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.repo.fcf_vu.SysRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangry on 17/7/16.
 */
@Component
public class RoleManager {

    @Autowired
    SysRoleRepo sysRoleRepo;

    /**
     * 判定客户名称是否重复
     *
     * @param roleId
     * @param name
     * @return
     */
    public Boolean isNameDuplicated(Integer roleId, String name) {
        Boolean isNameDuplicated = false;
        SysRole sysRole = sysRoleRepo.findByIdAndName(roleId, name);
        if (sysRole != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }

}
