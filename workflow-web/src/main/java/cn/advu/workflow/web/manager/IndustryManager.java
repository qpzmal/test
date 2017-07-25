package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.repo.fcf_vu.BaseIndustryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/17.
 */
@Component
public class IndustryManager {

    @Autowired
    private BaseIndustryRepo baseIndustryRepo;

    public List<BaseIndustry> findAllEnabledIndustryList() {
        return baseIndustryRepo.findEnabledAll();
    }

    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseIndustry baseIndustry = baseIndustryRepo.findByIdAndName(id, name);
        if (baseIndustry != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
