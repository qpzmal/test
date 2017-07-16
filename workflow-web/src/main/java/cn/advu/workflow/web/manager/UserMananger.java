package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 返回部门下的User列表
     *
     * @param deptId
     * @return
     */
    public List<SysUser> findUserByDept(Integer deptId) {
       return  sysUserRepo.findByDept(deptId);
    }

    /**
     * 返回是否有下属用户
     *
     * @param deptId
     * @return
     */
    public Boolean hasUserUnderDept(Integer deptId) {
        Boolean hasUser = false;
        List<SysUser> sysUserList = findUserByDept(deptId);
        if (sysUserList != null && !sysUserList.isEmpty()) {
            hasUser = true;
        }
        return hasUser;
    }
}
