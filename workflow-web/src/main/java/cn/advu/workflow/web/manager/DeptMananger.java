package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import cn.advu.workflow.repo.fcf_vu.BaseDeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class DeptMananger {

    @Autowired
    private BaseDeptRepo baseDeptRepo;



    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseDept baseDept = baseDeptRepo.findByIdAndName(id, name);
        if (baseDept != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
