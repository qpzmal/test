package cn.advu.workflow.domain.fcf_vu.datareport;

import cn.advu.workflow.domain.base.IDomain;

/**
 * 报表
 */
public class VuDataReport implements IDomain {

    // ==========================以下为[报表公用]用属性
    private String name; // 名称
    private String orderDate; // 日期
    private String startDate; // 日期
    private String type;
    //分公司
    private String bj;
    private String gd;
    private String sjz;
    
    //媒介
    private String ds;
    private String yy;
    private String hw;
    private String nr;
    
  //4A
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    
  //直客
    private String z1;
    private String z2;
    private String z3;
    private String z4;
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


    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }

    public String getZ1() {
        return z1;
    }

    public String getZ2() {
        return z2;
    }

    public String getZ3() {
        return z3;
    }

    public String getZ4() {
        return z4;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public void setZ1(String z1) {
        this.z1 = z1;
    }

    public void setZ2(String z2) {
        this.z2 = z2;
    }

    public void setZ3(String z3) {
        this.z3 = z3;
    }

    public void setZ4(String z4) {
        this.z4 = z4;
    }

    public String getDs() {
        return ds;
    }

    public String getYy() {
        return yy;
    }

    public String getHw() {
        return hw;
    }

    public String getNr() {
        return nr;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public void setHw(String hw) {
        this.hw = hw;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getBj() {
        return bj;
    }

    public String getGd() {
        return gd;
    }

    public String getSjz() {
        return sjz;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public void setGd(String gd) {
        this.gd = gd;
    }

    public void setSjz(String sjz) {
        this.sjz = sjz;
    }

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