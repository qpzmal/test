package cn.advu.workflow.domain.enums;

/**
 * Created by wangry on 17/7/13.
 */
public enum RoleEnum implements IValueEnum {

    SALER("4", "销售员工"),
    SALERDM("5", "销售主管"),
    SALEGM("6", "销售总经理"),
    FINANCIALGM("7", "财务主管"),
    MEDIAGM("3", "媒介主管"),
    MEDIA("9", "媒介员工");

    private String value;
    private String label;

    private RoleEnum(String value, String label)  {
        this.value = value;
        this.label = label;
    }

    @Override
    public String getValue() {
        return this.value;
    }
    public Integer getIntegerValue() {
        return Integer.valueOf(this.value);
    }
}
