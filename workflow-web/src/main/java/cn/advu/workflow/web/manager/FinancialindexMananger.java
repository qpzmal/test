package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import cn.advu.workflow.repo.fcf_vu.BaseFinancialindexRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class FinancialindexMananger {

    @Autowired
    private BaseFinancialindexRepo baseFinancialindexRepo;


    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseFinancialindex baseFinancialindex = baseFinancialindexRepo.findByIdAndName(id, name);
        if (baseFinancialindex != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }


    public Boolean isNumberDuplicated(Integer id, String number) {
        Boolean isNumberDuplicated = false;
        BaseFinancialindex baseFinancialindex = baseFinancialindexRepo.findByIdAndNumber(id, number);
        if (baseFinancialindex != null) {
            isNumberDuplicated = true;
        }
        return isNumberDuplicated;
    }
}
