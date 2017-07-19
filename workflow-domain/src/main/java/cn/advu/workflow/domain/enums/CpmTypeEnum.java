package cn.advu.workflow.domain.enums;

/**
 * Created by wangry on 17/7/13.
 */
public enum CpmTypeEnum implements IValueEnum {

    CUSTOM("1"),
    EXECUTE("2"),
    MONITOR("3");

    private String value;

    private CpmTypeEnum(String value)  {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
