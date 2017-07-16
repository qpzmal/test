package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangry on 17/7/13.
 */
@Component
public class UserMananger {

    @Autowired
    SysUserRepo sysUserRepo;

    /**
     * 判定客户名称是否重复
     *
     * @param id
     * @param name
     * @return
     */
    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        SysUser sysUser = sysUserRepo.findByIdAndName(id, name);
        if (sysUser != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
