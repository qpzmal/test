package cn.advu.workflow.common.configurer;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class DynamicConfigurer {
    private static final Map<String, String> LOCAL_PROPERTIES = new HashMap();
    private static final Logger LOCAL_LOGGER = Logger.getLogger(DynamicConfigurer.class.getName());
    private static ConfigFileMonitor dynamicConfiguration;
    private static ConfigChangeListener configChangeListener;

    public DynamicConfigurer() {
    }

    public static String getString(String key) {
        if (dynamicConfiguration == null) {
            dynamicConfiguration = new ConfigFileMonitor();
            dynamicConfiguration.start();
        }

        return (String) LOCAL_PROPERTIES.get(key);
    }

    public static String getString(String key, String defaultValue) {
        String value = getString(key);
        if (isEmpty(value)) {
            value = defaultValue;
        }

        return value;
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return "true".equalsIgnoreCase(getString(key, String.valueOf(defaultValue)));
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.valueOf(getString(key, String.valueOf(defaultValue))).intValue();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        try {
            return Long.valueOf(getString(key, String.valueOf(defaultValue))).longValue();
        } catch (Exception ex) {
            return 0L;
        }
    }

    public static float getFloat(String key) {
        return getFloat(key, 0.0F);
    }

    public static float getFloat(String key, float defaultValue) {
        try {
            return Float.valueOf(getString(key, String.valueOf(defaultValue))).floatValue();
        } catch (Exception ex) {
            return 0.0F;
        }
    }

    public static double getDouble(String key) {
        return getDouble(key, 0.0D);
    }

    public static double getDouble(String key, double defaultValue) {
        try {
            return Double.valueOf(getString(key, String.valueOf(defaultValue))).doubleValue();
        } catch (Exception ex) {
            return 0.0D;
        }
    }

    public static BigDecimal getBigDecimal(String key) {
        return getBigDecimal(key, (BigDecimal) null);
    }

    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        BigDecimal bigDecimal = null;

        try {
            bigDecimal = new BigDecimal(getString(key));
        } catch (Exception ex) {
            bigDecimal = defaultValue;
        }

        return bigDecimal;
    }

    public static List<String> getList(String key, String split) {
        String tmp = getString(key, "");
        return !isEmpty(tmp) && !isEmpty(split) ? Arrays.asList(tmp.split(split)) : null;
    }

    public static String[] getArray(String key, String split) {
        String tmp = getString(key, "");
        return !isEmpty(tmp) && !isEmpty(split) ? tmp.split(split) : null;
    }

    public static Date getDate(String key) {
        String text = getString(key);
        if (isEmpty(text)) {
            return null;
        } else {
            String format = "yyyy-MM-dd";
            if (text.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                format = "yyyy-MM-dd HH:mm:ss";
            } else if (text.matches("\\d{4}年\\d{2}月\\d{2}日")) {
                format = "yyyy年MM月dd日";
            } else if (text.matches("\\d{4}\\.\\d{2}\\.\\d{2}")) {
                format = "yyyy.MM.dd";
            }

            return getDate(key, format);
        }
    }

    public static Date getDate(String key, String format) {
        String text = getString(key);
        if (isEmpty(text)) {
            return null;
        } else {
            try {
                SimpleDateFormat e = new SimpleDateFormat(format);
                return e.parse(text);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Set<String> getConfigKeys() {
        return LOCAL_PROPERTIES.keySet();
    }

    public static Map<String, String> getConfigMap() {
        return LOCAL_PROPERTIES;
    }

    static ConfigChangeListener getConfigChangeListener() {
        return configChangeListener;
    }

    public static void setConfigChangeListener(ConfigChangeListener configChangeListener) {
        configChangeListener = configChangeListener;
    }

    protected static void updateConfig(String key, String value) {
        if (LOCAL_PROPERTIES.containsKey(key)) {
            String oldValue = (String) LOCAL_PROPERTIES.get(key);
            if (equals(oldValue, value)) {
                return;
            }

            String extInfo = "由[" + oldValue + "]更新为[" + value + "]";
            LOCAL_LOGGER.info("[deploy]配置有更新.KEY=[" + key + "]的值" + extInfo);
        }

        LOCAL_PROPERTIES.put(key, value);
    }

    static void updateDynamicConfig(File file, String key, String value) {
        updateConfig(key, value);
        if (configChangeListener != null) {
            configChangeListener.onChange(file, key, value);
        }

    }

    static String getENV(String key, String defaultValue) {
        if (isEmpty(key)) {
            return defaultValue;
        } else {
            String envValue = System.getProperty(key);
            if (isEmpty(envValue)) {
                envValue = System.getenv(key);
                if (isEmpty(envValue)) {
                    envValue = System.getenv(key.replace(".", "_"));
                }
            }

            if (isEmpty(envValue)) {
                envValue = defaultValue;
            }

            return envValue;
        }
    }

    static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
}
