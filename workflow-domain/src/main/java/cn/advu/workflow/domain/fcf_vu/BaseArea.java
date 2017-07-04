package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

import java.math.BigDecimal;

public class BaseArea extends AbstractEntity {

    private String name;

    private Integer parentId;

    private String code;

    private BigDecimal roomPay;

    private BigDecimal personPay;

    private BigDecimal otherPay;

    private BigDecimal taxAmount;

    private BigDecimal taxFreeAmount;

    private BigDecimal pay2;

    private BigDecimal pay3;

    private BigDecimal pay4;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public void setTaxFreeAmount(BigDecimal taxFreeAmount) {
        this.taxFreeAmount = taxFreeAmount;
    }

    public BigDecimal getPay2() {
        return pay2;
    }

    public void setPay2(BigDecimal pay2) {
        this.pay2 = pay2;
    }

    public BigDecimal getPay3() {
        return pay3;
    }

    public void setPay3(BigDecimal pay3) {
        this.pay3 = pay3;
    }

    public BigDecimal getPay4() {
        return pay4;
    }

    public void setPay4(BigDecimal pay4) {
        this.pay4 = pay4;
    }
}