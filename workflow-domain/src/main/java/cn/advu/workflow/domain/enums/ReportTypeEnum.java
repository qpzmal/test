package cn.advu.workflow.domain.enums;

/**
 * 报表类型
 * Created by wangry on 17/7/13.
 */
public enum ReportTypeEnum implements IValueEnum {

    SALE_HISTORY("1"), // 销售报表--历史订单
    SALE_FEATURE("2"), // 销售报表--未来订单
    BUY_RESOURCE("3"), // 采购报表--资源类别
    BUY_AREA("4"), // 采购报表--厂商
    AREA_BALANCE("5"), // 分公司财务分析--损益分析
    AREA_BUDGET("6"), // 分公司财务分析--预算完成度分析
    CUSTOMER_PROFIT("7"), // 客户分析报表--毛利/净利贡献率
    CUSTOMER_PAY("8"); // 客户分析报表--回款汇总

    private String value;

    private ReportTypeEnum(String value)  {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
