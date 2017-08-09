package cn.advu.workflow.domain.fcf_vu.datareport;

import cn.advu.workflow.domain.base.IDomain;

/**
 * 报表
 */
public class VuDataReport implements IDomain {

    // ==========================以下为[报表公用]用属性
    private String name; // 名称
    private String orderDate; // 日期


    // ==========================以下为[销售报表分析]用属性
    private String orderNum; // 订单总数
    private String taxAmount; // 含税金额
    private String taxFreeAmount; // 不含税金额


    // ==========================以下为[资源采购分析]用属性
    private String cpmTotal; // CPM总数
    private String saleAmount; // [媒体单价]总价
    private String buyAmount; // [媒体采购成本单价]总价


    // ==========================以下为[分公司财务分析]用属性
    private String yswc; // 预算完成


    // ==========================以下为[客户分析]用属性
    private String customId; //
    private String customType; //
    private String mPay; // 毛利贡献率
    private String jPay; // 净利润贡献率
    private String payPercent; // 净利润贡献率


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public void setTaxFreeAmount(String taxFreeAmount) {
        this.taxFreeAmount = taxFreeAmount;
    }

    public String getCpmTotal() {
        return cpmTotal;
    }

    public void setCpmTotal(String cpmTotal) {
        this.cpmTotal = cpmTotal;
    }

    public String getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(String saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getYswc() {
        return yswc;
    }

    public void setYswc(String yswc) {
        this.yswc = yswc;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public String getmPay() {
        return mPay;
    }

    public void setmPay(String mPay) {
        this.mPay = mPay;
    }

    public String getjPay() {
        return jPay;
    }

    public void setjPay(String jPay) {
        this.jPay = jPay;
    }

    public String getPayPercent() {
        return payPercent;
    }

    public void setPayPercent(String payPercent) {
        this.payPercent = payPercent;
    }

    @Override
    public String toString() {
        return "VuDataReport{" +
                "name='" + name + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", taxFreeAmount='" + taxFreeAmount + '\'' +
                ", cpmTotal='" + cpmTotal + '\'' +
                ", saleAmount='" + saleAmount + '\'' +
                ", buyAmount='" + buyAmount + '\'' +
                ", yswc='" + yswc + '\'' +
                ", customId='" + customId + '\'' +
                ", customType='" + customType + '\'' +
                ", mPay='" + mPay + '\'' +
                ", jPay='" + jPay + '\'' +
                ", payPercent='" + payPercent + '\'' +
                '}';
    }
}