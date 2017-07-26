package cn.advu.workflow.domain.enums;

/**
 * Created by wangry on 17/7/13.
 */
public enum LogTypeEnum implements IValueEnum {

    ADD("1"),
    UPDATE("2"),
    DELETE("3");

    private String value;

    private LogTypeEnum(String value)  {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
