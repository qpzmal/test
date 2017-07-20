package cn.advu.workflow.domain.fcf_vu;

import cn.advu.workflow.domain.base.AbstractOrderEntity;

import java.math.BigDecimal;
import java.util.Date;

public class BaseBuyOrder extends AbstractOrderEntity {

    private String processInstanceId;

    private String orderNum;

    private String secOrderNum;

    private String name;

    private Byte type;

    private Date orderDate;

    private BigDecimal amount;

    private Integer accountPeriod;

    private Date startDate;

    private Date endDate;

    private Integer personId;

    private Byte status;

    private BigDecimal payPercent;

    private Integer userId;

    private Integer areaId;

    private String deliveryAreaIds;

    private String deliveryAreaNames;

    private Integer frameId;

    private String wfStep; // 工作流步骤

    private String flowType; // 页面自定义属性：save保存草稿，start开始流程

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(Integer accountPeriod) {
        this.accountPeriod = accountPeriod;
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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public String getWfStep() {
        return wfStep;
    }

    public void setWfStep(String wfStep) {
        this.wfStep = wfStep;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
}