package cn.advu.workflow.common.configurer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomizedPropertySupport {
    public CustomizedPropertySupport() {
    }

    public String getString(String key) {
        return this.getString(key, (String) null);
    }

    public String getString(String key, String defaultValue) {
        return DynamicConfigurer.getString(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return "true".equalsIgnoreCase(this.getString(key, String.valueOf(defaultValue)));
    }

    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return DynamicConfigurer.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return this.getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue) {
        return DynamicConfigurer.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return this.getFloat(key, 0.0F);
    }

    public float getFloat(String key, float defaultValue) {
        return DynamicConfigurer.getFloat(key, defaultValue);
    }

    public double getDouble(String key) {
        return this.getDouble(key, 0.0D);
    }

    public double getDouble(String key, double defaultValue) {
        return DynamicConfigurer.getDouble(key, defaultValue);
    }

    public BigDecimal getBigDecimal(String key) {
        return this.getBigDecimal(key, (BigDecimal) null);
    }

    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        return DynamicConfigurer.getBigDecimal(key, defaultValue);
    }

    public List<String> getList(String key, String split) {
        return DynamicConfigurer.getList(key, split);
    }

    public String[] getArray(String key, String split) {
        return DynamicConfigurer.getArray(key, split);
    }

    public Date getDate(String key) {
        return DynamicConfigurer.getDate(key);
    }

    public Date getDate(String key, String format) {
        return DynamicConfigurer.getDate(key, format);
    }

    public Set<String> getConfigKeys() {
        return DynamicConfigurer.getConfigKeys();
    }

    public Map<String, String> getConfigMap() {
        return DynamicConfigurer.getConfigMap();
    }

    protected void updateConfig(String key, String value) {
        DynamicConfigurer.updateConfig(key, value);
    }
}
