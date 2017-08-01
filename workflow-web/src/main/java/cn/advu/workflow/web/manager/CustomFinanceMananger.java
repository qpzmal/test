package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.fcf_vu.BaseCustomFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangry on 17/7/13.
 */
@Component
public class CustomFinanceMananger {


    @Autowired
    BaseCustomFinanceRepo baseCustomFinanceRepo;

    /**
     * 判定客户结算是否重复
     *
     * @param customId
     * @param startDate
     * @param endDate
     * @return
     */
    public Boolean isDuplicated(Integer id, Integer customId, Date startDate, Date endDate) {
        Boolean isDuplicated = false;
        BaseCustomFinance customFinance = baseCustomFinanceRepo.findByCustomAndDate(id, customId, startDate, endDate);
        if (customFinance != null) {
            isDuplicated = true;
        }
        return isDuplicated;
    }

    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseCustomFinance baseCustomFinance = baseCustomFinanceRepo.findByIdAndName(id, name);
        if (baseCustomFinance != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
