package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import cn.advu.workflow.web.service.base.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangry on 17/7/13.
 */
@Component
public class CustomMananger {

    @Autowired
    BaseCustomRepo baseCustomRepo;

    /**
     * 判定客户名称是否重复
     *
     * @param customeId
     * @param name
     * @return
     */
    public Boolean isNameDuplicated(Integer customeId, String name) {
        Boolean isNameDuplicated = false;
        BaseCustom baseCustom = baseCustomRepo.findByIdAndName(customeId, name);
        if (baseCustom != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
