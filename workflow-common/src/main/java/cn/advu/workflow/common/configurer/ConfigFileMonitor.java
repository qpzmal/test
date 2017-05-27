package cn.advu.workflow.common.configurer;


import cn.advu.workflow.common.configurer.monitor.FileAlterationListenerAdaptor;
import cn.advu.workflow.common.configurer.monitor.FileAlterationMonitor;
import cn.advu.workflow.common.configurer.monitor.FileAlterationObserver;
import cn.advu.workflow.common.configurer.monitor.FileEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.Map.Entry;

final class ConfigFileMonitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileMonitor.class);
    private static final FileAlterationMonitor fileMonitor = new FileAlterationMonitor();

    ConfigFileMonitor() {
    }

    public static void main(String[] args) throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        String path = "";
        Enumeration urls = cl.getResources(path);

        while (urls.hasMoreElements()) {
            URL url = (URL) urls.nextElement();
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            Properties pop = new Properties();
            pop.load(is);
            is.close();
            //System.out.println(pop.keySet());
        }

    }

    public void start() {
        String dynamicDir = DynamicConfigurer.getENV("deploy.dynamic.config.dir", "");
        if (DynamicConfigurer.isEmpty(dynamicDir)) {
            LOGGER.info("[deploy]请通过[-Ddeploy.dynamic.config.dir=xxxpath]设置动态参数所需JVM环境变量,动态属性配置更新不可用!");
        } else {
            try {
                File e = null;
                if (dynamicDir.startsWith("classpath:")) {
                    String fileObserver = this.getClass().getResource("/").getFile();
                    e = new File(fileObserver, dynamicDir.substring(10));
                } else {
                    e = new File(dynamicDir);
                }

                FileAlterationObserver fileObserver1 = new FileAlterationObserver(e, new FileFilter() {
                    public boolean accept(File pathname) {
                        return pathname.getName().endsWith(".properties");
                    }
                });
                fileObserver1.addListener(new FileAlterationListenerAdaptor() {
                    public void onInit(FileAlterationObserver observer) {
                        FileEntry rootEntry = observer.getFileEntry();
                        ArrayList monitorListFile = new ArrayList();
                        FileEntry[] fileEntries;
                        int listenerAdaptor = (fileEntries = rootEntry.getChildren()).length;

                        for (int i = 0; i < listenerAdaptor; ++i) {
                            FileEntry fileList = fileEntries[i];
                            monitorListFile.add(fileList.getFile().getAbsolutePath());
                            ConfigFileMonitor.this.loadProperties(0, fileList.getFile());
                        }

                        String monitorFiles = Arrays.toString(monitorListFile.toArray());
                        if (monitorListFile.size() > 0) {
                            ConfigFileMonitor.LOGGER.info("[deploy]被检测属性文件列表：" + monitorFiles);
                        }

                    }

                    public void onFileCreate(File file) {
                        ConfigFileMonitor.this.loadProperties(1, file);
                    }

                    public void onFileChange(File file) {
                        ConfigFileMonitor.this.loadProperties(2, file);
                    }
                });
                fileMonitor.addObserver(fileObserver1);
                fileMonitor.start();
                LOGGER.info("[deploy]自动部署动态配置检测程序启动成功!");
                Thread.sleep(1000L);
            } catch (Exception ex) {
                LOGGER.warn("[deploy]自动部署动态配置检测程序启动成功!", ex);
            }

        }
    }

    private void loadProperties(final int actionType, final File file) {
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            Properties e = new Properties();
            e.load(in);
            Iterator entryIterator = e.entrySet().iterator();

            while (entryIterator.hasNext()) {
                Entry entry = (Entry) entryIterator.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                DynamicConfigurer.updateDynamicConfig(file, key, value);
            }

        } catch (Exception ex) {
            LOGGER.warn("[deploy]属性文件[" + file.getAbsolutePath() + "]加载失败!", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ;
                }
            }

        }

    }

}
