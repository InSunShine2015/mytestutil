package com.sun.redis.lock;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.springframework.stereotype.Component;

//import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * redis 辅助类，提供查询，以及设置redis缓存功能，
 * 提供批量获取键值功能。
 *
 * 目前不支持 hash set， sorted set, list 等数据操作
 *
 * Created by Haima on 2016/06/29.
 */
//@Component
public class RedisHelper {


//    @Inject
    private RedisWrapper redisWrapper;

    /**
     * 获取缓存对象, 转换成需要的对象实例
     * @param objClass 类型
     * @param cacheKey 缓存键值
     * @param <T> 类型实例
     * @return
     */
    public <T> T getCacheObject(Class<T> objClass, String cacheKey) {
        String value = redisWrapper.get(cacheKey);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return JSON.parseObject(value, objClass);
        } catch (Exception e) {
//            logger.error("Cache value convert error, value[{}],classType[{}].", value, objClass.getSimpleName(), e);
        }
        return null;
    }

    /**
     * 设置缓存对象, 设置过期时间
     * @param cacheKey
     * @param object
     * @param expiredTimeSeconds
     * @return
     */
    public boolean setCacheObject(String cacheKey, Object object, int expiredTimeSeconds) {
        if (object == null) {
            return true;
        }
        try {
            String result = null;

            if (expiredTimeSeconds <= 0) {
                result = redisWrapper.set(cacheKey, JSON.toJSONString(object));
            } else {
                result = redisWrapper.setex(cacheKey, expiredTimeSeconds, JSON.toJSONString(object));
            }
            // result should be "OK"

        } catch (Exception e) {
//            logger.error("Cache value set error, [cacheKey:{}][value:{}].", cacheKey, JSON.toJSONString(object), e);
        }
        return true;
    }

    /**
     * 获取单个缓存值列表
     * @param objClass
     * @param cacheKey
     * @param <T>
     * @return
     */
    public <T> List<T> getCacheListObject(Class<T> objClass, String cacheKey) {
        String value = redisWrapper.get(cacheKey);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return JSON.parseArray(value, objClass);
        } catch (Exception e) {
//            logger.error("Cache value list convert error, [value:{}][classType:{}].", value, objClass.getSimpleName(), e);
        }
        return null;
    }

    /**
     * 获取多个缓存键值, 列表返回
     * @param objClass
     * @param cacheKeys
     * @param <T>
     * @return
     */
    public <T> List<T> multiGetListCacheObject(Class<T> objClass, String[] cacheKeys) {
        if (cacheKeys == null) {
            return new ArrayList<>(0);
        }
        List<String> cacheList = redisWrapper.mget(cacheKeys);

        List<T> resultList = new ArrayList<>(cacheList.size());
        try {
            for (int i = 0; i < cacheList.size(); i++) {
                String value = cacheList.get(i);
                if (!StringUtils.isEmpty(value)) {
                    T t = JSON.parseObject(value, objClass);
                    resultList.add(t);
                }
            }
        } catch (Exception e) {
//            logger.error("Cache value convert error, [classType:{}][values:{}].", objClass.getSimpleName(), JSON.toJSONString(resultList), e);
        }

        return resultList;
    }

    /**
     * 删除缓存值
     * @param cacheKey
     * @return
     */
    public boolean deleteCacheObject(String cacheKey) {
        try {
            Long result = redisWrapper.del(cacheKey);
        } catch (Exception e) {
//            logger.error("Cache value delete error, [cacheKey:{}].", cacheKey, e);
            return false;
        }

        return true;
    }

}
