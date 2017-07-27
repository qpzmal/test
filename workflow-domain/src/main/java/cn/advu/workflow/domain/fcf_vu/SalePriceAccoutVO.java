package cn.advu.workflow.domain.fcf_vu;

import java.math.BigDecimal;

/**
 * Created by wangry on 17/7/14.
 */
public class SalePriceAccoutVO {

    private BigDecimal mediaPrice;
    private BigDecimal publicRebate;
    private BigDecimal purchase;
    private BigDecimal salesIncentiveRate;

    public BigDecimal getMediaPrice() {
        return mediaPrice;
    }

    public void setMediaPrice(BigDecimal mediaPrice) {
        this.mediaPrice = mediaPrice;
    }

    public BigDecimal getPublicRebate() {
        return publicRebate;
    }

    public void setPublicRebate(BigDecimal publicRebate) {
        this.publicRebate = publicRebate;
    }

    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getSalesIncentiveRate() {
        return salesIncentiveRate;
    }

    public void setSalesIncentiveRate(BigDecimal salesIncentiveRate) {
        this.salesIncentiveRate = salesIncentiveRate;
    }
}
