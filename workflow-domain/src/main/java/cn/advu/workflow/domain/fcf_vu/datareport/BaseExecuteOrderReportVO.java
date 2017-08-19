package cn.advu.workflow.domain.fcf_vu.datareport;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;

import java.math.BigDecimal;


public class BaseExecuteOrderReportVO extends BaseExecuteOrder {

    private String adTypeName;
    private BigDecimal totalCpmCount;
    private BigDecimal totalCpmCost;
    private String personLeaderName;

    public String getAdTypeName() {
        return adTypeName;
    }

    public void setAdTypeName(String adTypeName) {
        this.adTypeName = adTypeName;
    }

    public BigDecimal getTotalCpmCount() {
        return totalCpmCount;
    }

    public void setTotalCpmCount(BigDecimal totalCpmCount) {
        this.totalCpmCount = totalCpmCount;
    }

    public BigDecimal getTotalCpmCost() {
        return totalCpmCost;
    }

    public void setTotalCpmCost(BigDecimal totalCpmCost) {
        this.totalCpmCost = totalCpmCost;
    }

    public String getPersonLeaderName() {
        return personLeaderName;
    }

    public void setPersonLeaderName(String personLeaderName) {
        this.personLeaderName = personLeaderName;
    }
}