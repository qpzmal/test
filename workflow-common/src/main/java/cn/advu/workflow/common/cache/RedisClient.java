package cn.advu.workflow.common.cache;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Set;


/**
 * redis 帮助类
 */
public class RedisClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);


    /**
     * redis连接池
     */
    private JedisPool pool;

    private RedisClient(JedisPool jedisPool) {
        this.pool = jedisPool;
    }

    /**
     * redis job key
     */
    static final String REDIS_JOB_KEY = "_redis_job";

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    <T> T get(String key, TypeReference<T> typeReference) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String val = jedis.get(key);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("redis get()...key:" + key +",val:" + val);
            }

            // 没有获取到val,返回空
            if (StringUtils.isEmpty(val)) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            //获取到空对象，返回空的缓存对象
            if (StringUtils.equals(val, "{}")) {

                return (T) mapper.readValue(val, EmptyCacheObject.class);
            }

            return (T) mapper.readValue(val, typeReference);
        } catch (IOException ex) {
            LOGGER.error("get() ERROR!,[key:" + key + "],Exception Message:" + ex.getMessage(), ex);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }

        return null;
    }


    /**
     * 添加缓存数据
     *
     * @param key
     * @param value
     * @param expire
     */
    public <T> void setEx(String key, T value, int expire) {

        Jedis jedis = null;
        try {

            jedis = pool.getResource();
            jedis.setex(key, expire, new ObjectMapper().writeValueAsString(value));

        } catch (IOException ex) {
            LOGGER.error("setEx() ERROR!,[key:" + key + "],[value:" + value + "],Exception Message:" + ex.getMessage(), ex);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 添加缓存数据，无时间限制
     * @param key
     * @param value
     */
    public <T> void set(String key, T value) {

        Jedis jedis = null;
        try {

            jedis = pool.getResource();
            jedis.set(key, new ObjectMapper().writeValueAsString(value));

        } catch (IOException ex) {
            LOGGER.error("set() ERROR!,[key:" + key + "],[value:" + value + "],Exception Message:" + ex.getMessage(), ex);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void set(String key, String value) {

        Jedis jedis = null;
        try {

            jedis = pool.getResource();
            jedis.set(key,value);

        } catch (Exception ex) {
            LOGGER.error("set() ERROR!,[key:" + key + "],[value:" + value + "],Exception Message:" + ex.getMessage(), ex);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> T getAndSet(String key, int expire, TypeReference<T> typeReference,
                           CacheCaller<T> caller) {

        // 返回空值
        if (StringUtils.isEmpty(key)) {
            return null;
        }

        T returnObj = get(key, typeReference);

        // 缓存存在数据，直接返回
        if (null != returnObj) {
            // 如果缓存数据为空对象，返回null
            if (returnObj instanceof EmptyCacheObject) {
                return null;
            }
            return (T) returnObj;
        }
        // 已经有任务在执行 , 最多获取缓存10次，有获取到数据直接返回，都没有获取到数据直接落地到数据库查询
        if (EhcacheHelper.checkkey(REDIS_JOB_KEY + "_" + key)) {
            int c = 10;
            while (c-- > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    LOGGER.error("getAndSet() InterruptedException error:" + e.getMessage());
                }
                returnObj = get(key, typeReference);
                if (null != returnObj) {
                    // 如果缓存数据为空对象，返回null
                    if (returnObj instanceof EmptyCacheObject) {
                        return null;
                    }
                    return (T) returnObj;
                }
            }

            LOGGER.warn("getAndSet has been more than 10 times key is " + key);
            return caller.getData();

        } else { //没有任务在执行,调用回调接口
            EhcacheHelper.addjob(REDIS_JOB_KEY + "_" + key);
            try {
                T o = caller.getData();
                // 数据为空的话往缓存里保存空对象，防止缓存被击穿
                if (null != o) {
                    setEx(key, o, expire);
                    return o;
                } else {
                    setEx(key, EmptyCacheObject.instance, expire);
                    return null;
                }

            } catch (Exception e) {
                LOGGER.error("getAndSet() error:" + e.getMessage(),e);
            } finally {
                EhcacheHelper.removeJob(REDIS_JOB_KEY + "_" + key);
            }
        }
        return null;
    }

    public void destory() {
        this.pool.destroy();
    }


    /**
     * redis 执行方法
     * @param execCaller
     * @param <T>
     * @return
     */
    public <T> T exec(ExecCaller<T> execCaller) {

        Jedis jedis = pool.getResource();

        try {

            return execCaller.exec(jedis);

        } finally {
            jedis.close();
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public  void  removeCache(String key)
    {
        Jedis jedis = null;
        try {

            jedis = pool.getResource();
            jedis.del(key);
        } catch (Exception ex) {
            LOGGER.error("del() ERROR!,[key:" + key + "],[key:" + key + "],Exception Message:" + ex.getMessage(), ex);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 模糊查询
     * @param key
     */
    public Set<String> keys(String key)
    {
        Jedis jedis = null;
        Set<String> keys = null;
        try {

            jedis = pool.getResource();
            keys = jedis.keys(key);
        } catch (Exception ex) {
            LOGGER.error("del() ERROR!,[key:" + key + "],[key:" + key + "],Exception Message:" + ex.getMessage(), ex);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
        return keys;
    }

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();

            return jedis.get(key);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }

    }

    /**
     * 添加缓存数据
     *
     * @param key
     * @param value
     * @param expire
     */
    public void setStrEx(String key, String value, int expire) {


        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.setex(key, expire, value);

        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
