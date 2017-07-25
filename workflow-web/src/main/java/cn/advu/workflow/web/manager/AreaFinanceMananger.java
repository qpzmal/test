package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.fcf_vu.BaseAreaFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomFinanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangry on 17/7/13.
 */
@Component
public class AreaFinanceMananger {


    @Autowired
    BaseAreaFinanceRepo baseAreaFinanceRepo;

    /**
     * 判定结算是否重复
     *
     * @param areaId
     * @param startDate
     * @param endDate
     * @return
     */
    public Boolean isDuplicated(Integer areaId, Date startDate, Date endDate) {

        return isDuplicated(null, areaId, startDate, endDate);
    }

    /**
     * 判定结算是否重复
     *
     * @param areaId
     * @param startDate
     * @param endDate
     * @return
     */
    public Boolean isDuplicated(Integer id, Integer areaId, Date startDate, Date endDate) {
        Boolean isDuplicated = false;
        BaseAreaFinance areaFinance = baseAreaFinanceRepo.findByAreaAndDate(id, areaId, startDate, endDate);
        if (areaFinance != null) {
            isDuplicated = true;
        }
        return isDuplicated;
    }

    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseAreaFinance areaFinance = baseAreaFinanceRepo.findByIdAndName(id, name);
        if (areaFinance != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
