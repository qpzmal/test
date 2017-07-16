package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.fcf_vu.BasePersonRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/13.
 */
@Component
public class PersonMananger {

    @Autowired
    BasePersonRepo basePersonRepo;

    /**
     * 返回部门下的通讯录列表
     *
     * @param deptId
     * @return
     */
    public List<BasePerson> findPersonByDept(Integer deptId) {
       return  basePersonRepo.findChildListByDept(deptId);
    }

    /**
     * 返回是否有下属通讯录
     *
     * @param deptId
     * @return
     */
    public Boolean hasPersonUnderDept(Integer deptId) {
        Boolean hasPerson = false;
        List<BasePerson> basePersonList = findPersonByDept(deptId);
        if (basePersonList != null && !basePersonList.isEmpty()) {
            hasPerson = true;
        }
        return hasPerson;
    }
}
