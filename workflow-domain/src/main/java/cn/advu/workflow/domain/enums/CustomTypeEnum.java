package cn.advu.workflow.domain.enums;

/**
 * Created by wangry on 17/7/13.
 */
public enum CustomTypeEnum implements IValueEnum {

    FA("1"),
    DA("2"),
    MA("0");

    private String value;

    private CustomTypeEnum(String value)  {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
