package cn.advu.workflow.common.configurer;


public class DynmaicConfigurerBean extends CustomizedPropertySupport {
    public DynmaicConfigurerBean() {
        this((String) null, (ConfigChangeListener) null);
    }

    public DynmaicConfigurerBean(String dirPath) {
        this(dirPath, (ConfigChangeListener) null);
    }

    public DynmaicConfigurerBean(ConfigChangeListener configChangeListener) {
        this((String) null, configChangeListener);
    }

    public DynmaicConfigurerBean(String dirPath, ConfigChangeListener configChangeListener) {
        if (configChangeListener != null) {
            DynamicConfigurer.setConfigChangeListener(configChangeListener);
        }

        String monitorDir = DynamicConfigurer.getENV("deploy.dynamic.config.dir", "");
        if (DynamicConfigurer.isEmpty(monitorDir) && dirPath != null) {
            System.setProperty("deploy.dynamic.config.dir", dirPath);
        }

        this.getString("deploy.dynamic.config.dir");
    }
}
