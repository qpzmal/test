package cn.advu.workflow.domain.fcf_vu;

import java.math.BigDecimal;

public class BaseOrderCpm {
    private Integer id;

    private Integer orderId;

    private Boolean orderCpmType;

    private Integer mediaId;

    private BigDecimal mediaPrice;

    private BigDecimal firstPrice;

    private Boolean adTypeId;

    private Integer cpm;

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
}