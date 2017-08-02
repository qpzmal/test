package cn.advu.workflow.domain.enums;

/**
 * Created by wangry on 17/7/13.
 */
public enum FinanceIndexEnum implements IValueEnum {

    MARKUP_RATE("C001"),
    VALUE_ADDED_TAX("C002"),
    ATTACH_TAX("C003"),
    CULTURE_TAX("C004"),
    SALE_RATE("C005"),
    OFFER_RATE("C006"),
    INCOME_TAX("C007");

    private String value;

    private FinanceIndexEnum(String value)  {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
