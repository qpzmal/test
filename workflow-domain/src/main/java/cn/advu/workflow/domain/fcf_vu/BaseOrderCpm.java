package cn.advu.workflow.domain.fcf_vu;

import java.math.BigDecimal;
import java.util.Date;

public class BaseOrderCpm {
    private Integer id;

    private Integer orderId;

    private Boolean orderCpmType;

    private Integer mediaId;

    private BigDecimal mediaPrice;

    private BigDecimal firstPrice;

    private Boolean adTypeId;

    private Integer cpm;

    private Boolean delFlag;

    private Boolean itemStatus;

    private Integer creatorId;

    private Integer updaterId;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Boolean getOrderCpmType() {
        return orderCpmType;
    }

    public void setOrderCpmType(Boolean orderCpmType) {
        this.orderCpmType = orderCpmType;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public BigDecimal getMediaPrice() {
        return mediaPrice;
    }

    public void setMediaPrice(BigDecimal mediaPrice) {
        this.mediaPrice = mediaPrice;
    }

    public BigDecimal getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(BigDecimal firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Boolean getAdTypeId() {
        return adTypeId;
    }

    public void setAdTypeId(Boolean adTypeId) {
        this.adTypeId = adTypeId;
    }

    public Integer getCpm() {
        return cpm;
    }

    public void setCpm(Integer cpm) {
        this.cpm = cpm;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Boolean getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}