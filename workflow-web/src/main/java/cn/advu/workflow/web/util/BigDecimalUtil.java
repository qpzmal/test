package cn.advu.workflow.web.util;



import java.math.BigDecimal;

/**
 * Created by wangry on 17/7/13.
 */
public class BigDecimalUtil {

    public static final BigDecimal HUNDRED = new BigDecimal("100");

    public static BigDecimal getBigDecimalWithDefaultZero(BigDecimal value) {
        return (value == null) ? BigDecimal.ZERO : value;
    }

    public static BigDecimal convertToPercent(BigDecimal value) {
        BigDecimal baseValue = getBigDecimalWithDefaultZero(value);
        return baseValue.multiply(HUNDRED);
    }

}
