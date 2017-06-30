package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class BaseExecuteOrder extends AbstractEntity {

    private String name;

    private String orderNum;

    private String secOrderNum;

    private Boolean type;

    private Date orderDate;

    private String deliveryAreaIds;

    private String deliveryAreaNames;

    private Integer areaId;

    private Integer personSalesId;

    private BigDecimal amount;

    private BigDecimal publicRebate;

    private BigDecimal privateRebate;

    private BigDecimal taxAmount;

    private Long taxFreeAmount;

    private String monitorIds;

    private String monitorNames;

    private Date startDate;

    private Date endDate;

    private Integer personLeaderId;

    private String ourMonitorName;

    private String reportTypeId;

    private Byte status;

    private Integer personPayeeId;

    private BigDecimal payPercent;

    private Integer userId;

    private Integer accountPeriod;

    private Boolean itemStatus;

    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getSecOrderNum() {
        return secOrderNum;
    }

    public void setSecOrderNum(String secOrderNum) {
        this.secOrderNum = secOrderNum == null ? null : secOrderNum.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAreaIds() {
        return deliveryAreaIds;
    }

    public void setDeliveryAreaIds(String deliveryAreaIds) {
        this.deliveryAreaIds = deliveryAreaIds == null ? null : deliveryAreaIds.trim();
    }

    public String getDeliveryAreaNames() {
        return deliveryAreaNames;
    }

    public void setDeliveryAreaNames(String deliveryAreaNames) {
        this.deliveryAreaNames = deliveryAreaNames == null ? null : deliveryAreaNames.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getPersonSalesId() {
        return personSalesId;
    }

    public void setPersonSalesId(Integer personSalesId) {
        this.personSalesId = personSalesId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPublicRebate() {
        return publicRebate;
    }

    public void setPublicRebate(BigDecimal publicRebate) {
        this.publicRebate = publicRebate;
    }

    public BigDecimal getPrivateRebate() {
        return privateRebate;
    }

    public void setPrivateRebate(BigDecimal privateRebate) {
        this.privateRebate = privateRebate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Long getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public void setTaxFreeAmount(Long taxFreeAmount) {
        this.taxFreeAmount = taxFreeAmount;
    }

    public String getMonitorIds() {
        return monitorIds;
    }

    public void setMonitorIds(String monitorIds) {
        this.monitorIds = monitorIds == null ? null : monitorIds.trim();
    }

    public String getMonitorNames() {
        return monitorNames;
    }

    public void setMonitorNames(String monitorNames) {
        this.monitorNames = monitorNames == null ? null : monitorNames.trim();
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

    public Integer getPersonLeaderId() {
        return personLeaderId;
    }

    public void setPersonLeaderId(Integer personLeaderId) {
        this.personLeaderId = personLeaderId;
    }

    public String getOurMonitorName() {
        return ourMonitorName;
    }

    public void setOurMonitorName(String ourMonitorName) {
        this.ourMonitorName = ourMonitorName == null ? null : ourMonitorName.trim();
    }

    public String getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(String reportTypeId) {
        this.reportTypeId = reportTypeId == null ? null : reportTypeId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPersonPayeeId() {
        return personPayeeId;
    }

    public void setPersonPayeeId(Integer personPayeeId) {
        this.personPayeeId = personPayeeId;
    }

    public BigDecimal getPayPercent() {
        return payPercent;
    }

    public void setPayPercent(BigDecimal payPercent) {
        this.payPercent = payPercent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(Integer accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public Boolean getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}