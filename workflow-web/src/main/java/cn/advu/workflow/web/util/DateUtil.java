package cn.advu.workflow.web.util;

import org.joda.time.DateTime;

/**
 * Created by weiqz on 2017/8/19.
 */
public class DateUtil {
    public static final String DATE_FORMAT_DEFAULT_12H = "yyyy-MM-dd hh:mm:ss"; // 12小时制
    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss"; // 24小时制
    public static final String DATE_FORMAT_NO_SYMBOL_24H = "yyyyMMddHHmmss"; // 日期无符号，24小时

    /**
     * 当前年度第一天
     * @return
     */
    public static String getYearFirstDay() {
        return new DateTime().withDayOfYear(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString(DATE_FORMAT_DEFAULT);
    }

    /**
     * 当前年度最后一天
     * @return
     */
    public static String getYearLastDay() {
        return new DateTime().dayOfYear().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString(DATE_FORMAT_DEFAULT);
    }

    /**
     * 当前年度上半年最后一天
     * @return
     */
    public static String getFirstHalfYearLastDay() {
        return new DateTime().withMonthOfYear(6).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString(DATE_FORMAT_DEFAULT);
    }

    /**
     * 当前年度下半年第一天
     * @return
     */
    public static String getSecondHalfYearFirstDay() {
        return new DateTime().withMonthOfYear(7).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString(DATE_FORMAT_DEFAULT);
    }

    /**
     * 当前年度一季度最后一天
     * @return
     */
    public static String getFirstSeasonLastDay() {
        return new DateTime().withMonthOfYear(3).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString(DATE_FORMAT_DEFAULT);
    }

    /**
     * 当前年二季度第一天
     * @return
     */
    public static String getSecSeasonFirstDay() {
        return new DateTime().withMonthOfYear(4).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString(DATE_FORMAT_DEFAULT);
    }
    /**
     * 当前年三季度最后一天
     * @return
     */
    public static String getThirdSeasonLastDay() {
        return new DateTime().withMonthOfYear(6).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toString(DATE_FORMAT_DEFAULT);
    }

    /**
     * 当前年四季度第一天
     * @return
     */
    public static String getFourthSeasonFirstDay() {
        return new DateTime().withMonthOfYear(10).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toString(DATE_FORMAT_DEFAULT);
    }




    /**
     * 当前时间
     * @return
     */
    public static String getToday() {
        return new DateTime().toString(DATE_FORMAT_DEFAULT);
    }
}
