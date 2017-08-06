package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.enums.FinanceIndexEnum;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import cn.advu.workflow.repo.fcf_vu.BaseFinancialindexRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    public BigDecimal getMarkUpRate() {
        return getValue(FinanceIndexEnum.MARKUP_RATE.getValue());
    }

    public BigDecimal getValueAddedTax() {
        return getValue(FinanceIndexEnum.VALUE_ADDED_TAX.getValue());
    }

    public BigDecimal getAttachTax() {
        return getValue(FinanceIndexEnum.ATTACH_TAX.getValue());
    }
    public BigDecimal getCultureTax() {
        return getValue(FinanceIndexEnum.CULTURE_TAX.getValue());
    }
    public BigDecimal getSaleRate() {
        return getValue(FinanceIndexEnum.SALE_RATE.getValue());
    }
    public BigDecimal getOfferRate() {
        return getValue(FinanceIndexEnum.OFFER_RATE.getValue());
    }
    public BigDecimal getIncomeTax() {
        return getValue(FinanceIndexEnum.INCOME_TAX.getValue());
    }


    private BigDecimal getValue(String code) {
        BaseFinancialindex baseFinancialindex = baseFinancialindexRepo.findByNumber(code);
        BigDecimal markupRate = new BigDecimal(baseFinancialindex.getValue());
        return markupRate;
    }
}
