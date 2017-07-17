package cn.advu.workflow.domain.enums;

/**
 * Created by wangry on 17/7/13.
 */
public enum ItemStatusEnum implements IValueEnum {

    ACTIVE("0"),
    INACTIVE("1");

    private String value;

    private ItemStatusEnum(String value)  {
        this.value = value;
    }

    @Override
    public String getValue() {
        return String.valueOf(this.value);
    }
}
