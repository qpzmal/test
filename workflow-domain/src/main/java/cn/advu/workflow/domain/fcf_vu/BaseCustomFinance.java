package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class BaseCustomFinance extends AbstractEntity {

    private String name;

    private Integer customId;

    private Date startDate;

    private Date endDate;

    private BigDecimal roomPay;

    private BigDecimal personPay;

    private BigDecimal otherPay;

    private BigDecimal costBuy;

    private BigDecimal income;

    private BigDecimal tax;

    private BigDecimal salesCommission;

    private BigDecimal pay1;

    private BigDecimal pay3;

    private BigDecimal pay2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRoomPay() {
        return roomPay;
    }

    public void setRoomPay(BigDecimal roomPay) {
        this.roomPay = roomPay;
    }

    public BigDecimal getPersonPay() {
        return personPay;
    }

    public void setPersonPay(BigDecimal personPay) {
        this.personPay = personPay;
    }

    public BigDecimal getOtherPay() {
        return otherPay;
    }

    public void setOtherPay(BigDecimal otherPay) {
        this.otherPay = otherPay;
    }

    public BigDecimal getCostBuy() {
        return costBuy;
    }

    public void setCostBuy(BigDecimal costBuy) {
        this.costBuy = costBuy;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getSalesCommission() {
        return salesCommission;
    }

    public void setSalesCommission(BigDecimal salesCommission) {
        this.salesCommission = salesCommission;
    }

    public BigDecimal getPay1() {
        return pay1;
    }

    public void setPay1(BigDecimal pay1) {
        this.pay1 = pay1;
    }

    public BigDecimal getPay3() {
        return pay3;
    }

    public void setPay3(BigDecimal pay3) {
        this.pay3 = pay3;
    }

    public BigDecimal getPay2() {
        return pay2;
    }

    public void setPay2(BigDecimal pay2) {
        this.pay2 = pay2;
    }

}