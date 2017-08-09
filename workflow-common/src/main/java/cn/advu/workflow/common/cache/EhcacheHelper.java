package cn.advu.workflow.common.cache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;


/**
 * ehcache 帮助类
 *
 */
public class EhcacheHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EhcacheHelper.class);

    private EhcacheHelper() {
    }


    static {
        ClassLoader standardClassloader = Thread.currentThread().getContextClassLoader();
        URL url = null;
        if (standardClassloader != null) {
            url = standardClassloader.getResource("/ehcache/ehcache.xml");
        }

        if (url == null) {
            url = EhcacheHelper.class.getResource("/ehcache/ehcache.xml");
        }
        cm = CacheManager.newInstance(url);
    }


    private static  CacheManager cm ;
    static private Cache job_cache = cm.getCache(CacheKeyConstant.EHCACHE_JOB_LIST);

    static public void addjob(String k) {
        Element e = new Element(k, k);
        job_cache.put(e);
    }

    static public void removeJob(String k) {
        job_cache.remove(k);
    }

    static public boolean checkkey(String k) {
        return job_cache.isKeyInCache(k);
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @return
     */
    static Object getCacheObject(String cacheName, String key) {
        Element e = cm.getCache(cacheName).get(key);
        if (e != null) {
            return e.getObjectValue();
        }
        return null;
    }

    static public void setCacheObject(String cacheName, String key, Object value) {

        Element e = new Element(key, value);
        cm.getCache(cacheName).put(e);
    }

    static public void removeAll(String cacheName) {
        cm.getCache(cacheName).removeAll();
    }


    static public void removeCache(String cacheName) {
        cm.removeCache(cacheName);
    }

    static public <T> T getCacheAndSet(String cacheName, String key,
                                       CacheCaller<T> caller) {

        // 返回空值
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(cacheName)) {
            return null;
        }

        Object returnObj = EhcacheHelper.getCacheObject(cacheName, key);

        // 缓存存在数据，直接返回
        if (null != returnObj ) {
            // 如果缓存数据为空对象，返回null
            if (returnObj instanceof EmptyCacheObject) {
                return null;
            }
            return (T) returnObj;
        }
        // 已经有任务在执行 , 最多获取缓存10次，有获取到数据直接返回，都没有获取到数据直接落地到数据库查询
        if (EhcacheHelper.checkkey(cacheName+"_"+key)) {
            int c = 10;
            while (c-- > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    LOGGER.error("getCacheObject() InterruptedException error:" + e.getMessage());
                }
                returnObj = getCacheObject(cacheName, key);
                if (null != returnObj ) {
                    // 如果缓存数据为空对象，返回null
                    if (returnObj instanceof EmptyCacheObject) {
                        return null;
                    }
                    return (T) returnObj;
                }
            }

            LOGGER.warn("getCache has been more than 10 times cacheName is " + cacheName+" key is "+key );
            return  caller.getData();

        } else { //没有任务在执行,调用回调接口
            EhcacheHelper.addjob(cacheName+"_"+key);
            try {
                T o = caller.getData();
                // 数据为空的话往缓存里保存空对象，防止缓存被击穿
                if (null != o) {
                    setCacheObject(cacheName, key, o);
                    return o;
                }else{
                    setCacheObject(cacheName, key, EmptyCacheObject.instance);
                    return null;
                }

            } catch (Exception e) {
                LOGGER.error("getCacheObject() error:" + e.getMessage());
            } finally {
                EhcacheHelper.removeJob(cacheName+"_"+key);
            }
        }
        return null;
    }

    /**
     * ehcache 缓存key
     */
    static  final class CacheKeyConstant {

        public static final String EHCACHE_JOB_LIST = "_ehcache_job_list";
    }
}
