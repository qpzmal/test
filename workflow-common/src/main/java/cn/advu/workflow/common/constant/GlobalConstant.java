package cn.advu.workflow.common.constant;

/**
 * Created by kai on 16/6/14.
 */
public class GlobalConstant {

    /**
     * 缓存有效时间
     */
    public static final class CacheExpire {
        public static final int MINUTE = 60;//1分钟
        public static final int MINUTE_TWO = 120;//2分钟
        public static final int MINUTE_THR = 180; // 3分钟 60*3
        public static final int MINUTE_FIV = 300; // 5分钟 60*5
        public static final int MINUTE_THIRTY = 1800; // 30分钟 60*30
        public static final int HOUR = 3600;//1小时 60*60
        public static final int HOUR_FOUR = 14400;//4小时
        public static final int DAY = 86400;//1天 60*60*24
        public static final int DAY_TWO = 172800;//2天 60*60*24*2
        public static final int WEEK = 604800;//1周 60*60*24*7
        public static final int MONTH = 2592000;//1月 60*60*24*30
        public static final long MONTH_MSEC = 2592000000l;//1月 60*60*24*30*1000
        public static final int YEAR = 31536000;//1年 60*60*24*365
    }

    /**
     * 数据库
     */
    public static final class DB {
        //为表取模
        public static final int TABLE_NUM = 1024;
    }

}
