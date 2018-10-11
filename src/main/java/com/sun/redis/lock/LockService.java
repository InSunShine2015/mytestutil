package com.sun.redis.lock;

//import com.borui.health.uc.utils.LoggerUtils;
//import org.slf4j.Logger;
//import org.springframework.stereotype.Service;

//import javax.inject.Inject;

/**
 * 分布式锁处理类，处理分布式锁机制
 *
 * 目前实现使用 redis 做分布式锁
 *
 * Created by Administrator on 2017/3/22.
 */
//@Service
public final class LockService {
//    private static final Logger logger = LoggerUtils.getLogger(BaseDao.class);

//    @Inject
    protected RedisWrapper redisWrapper;

    private static final String REDIS_VALUE_NIL = "nil";

    /**
     * 尝试获取 分布式锁
     *
     * @param uniqueKey
     * @return
     */
    public final boolean tryLock(String uniqueKey) {
        int loopCount = 5;
        for (int i = 1; i <= loopCount; i++) {
        	//尝试获取分布式锁
            boolean isLock = tryRedisLock(uniqueKey);
//            logger.info("try lock status[{}], loop[{}],uniqueKey[{}].", isLock, i, uniqueKey);
            //获取到了锁
            if (isLock) {
                return true;
            }
            //未获取到锁，并且尝试次数用完
            if (i == loopCount) {
//                logger.warn("try lock failed, loop[{}],uniqueKey[{}].", i, uniqueKey);
                return false;
            }
            
            //未获取到锁，尝试次数未用完，当前线程停一下再尝试下一次获取
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
//                logger.error("try lock error, loop[{}].", i, e);
            }
        }
        return false;
    }

    /**
     * redis 分布式锁实现机制参考 redis setnx 命令描述
     *
     * @param uniqueKey
     * @return
     */
    private boolean tryRedisLock(String uniqueKey) {
        String lockKey = uniqueKey;
        try {
        	//采用setnx命令尝试获取锁：0失败  1 成功
            Long value = redisWrapper.setnx(lockKey, lockExpiredTimeValue());
            //获取失败
            if (value == 0) {
            	//取lockKey值
                String lockValue = redisWrapper.get(lockKey);
                if (!REDIS_VALUE_NIL.equals(lockValue)) {
                	//检查key值是否过期
                    long checkTime = Long.parseLong(lockValue) - System.currentTimeMillis();
                    //没有过期，获取锁失败
                    if (checkTime > 0) {
                        return false;
                    }
                }
                //获取到的key值是nil，也就是key已经不存在了，这时有可能多个线程去尝试设置新的key值
                //情形2是上面的key已经检查过期了，需要其他多个线程重新设置新的key值
                String lockedTime = redisWrapper.getSet(lockKey, lockExpiredTimeValue());
                if (!REDIS_VALUE_NIL.equals(lockedTime)) {
                    long checkTime = Long.parseLong(lockedTime) - System.currentTimeMillis();
                    //虽然设置新值成功，但是有可能其他线程在本次线程之前已经设置过值，拿到锁了，所以造成获取到的值未过期
                    if (checkTime > 0) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
//            logger.error("tryLock error, lockKey[{}].", lockKey,  e);
        }
        return true;
    }

    /**
     * 释放 分布式 Redis锁
     *
     * @param uniqueKey
     * @return
     */
    public final boolean unlock(String uniqueKey) {
        String lockKey = uniqueKey;
        redisWrapper.del(lockKey);
        return true;
    }

    private String lockExpiredTimeValue() {
        // 分布式锁，锁10*1000+1毫秒
        return (System.currentTimeMillis() + 10*1000+1) + "";
    }

}
