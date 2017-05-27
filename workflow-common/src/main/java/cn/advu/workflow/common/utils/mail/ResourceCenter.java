package cn.advu.workflow.common.utils.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by zhanglei on 2016/12/29.
 */
public class ResourceCenter {
    /**
     * 单例实现
     */
    private ResourceCenter() {
    }

    private static class InstanceHolder {
        public static ResourceCenter instance = new ResourceCenter();
    }

    public static ResourceCenter getInstance() {
        return InstanceHolder.instance;
    }

    private ResourceBundle bundle;

    private Map<String,String> proMap = new HashMap<>();



    /**
     * 获取属性
     * @param k
     * @return
     */
    public String getPropertie(String k)
    {
        bundle = ResourceBundle.getBundle("config/app");

        Set<String> keys =  bundle.keySet();
        for(String key : keys)
        {
            proMap.put(key,bundle.getString(key));
        }

        bundle = ResourceBundle.getBundle("config/mail");
        keys =  bundle.keySet();
        for(String key : keys)
        {
            proMap.put(key,bundle.getString(key));
        }
        return proMap.get(k);
    }
}
