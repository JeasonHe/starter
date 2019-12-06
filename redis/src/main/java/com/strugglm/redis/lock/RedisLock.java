package com.strugglm.redis.lock;


import com.strugglm.redis.config.RedisStarter;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @description: 分布式锁
 * @author: hejiale
 * @create: 2019/12/06 11:49
 */
@Data
@Log4j
public class RedisLock implements DistributedLock{
    private RedissonClient redissonClient;

    private long tryLockWaitTime = 60;
    private long releaseLockTime = 300;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public RedisLock(RedisStarter redisStarter) {
        redissonClient = redisStarter.getRedisson();
    }

    public RedisLock(RedissonClient redissonClient, Integer tryLockWaitTime, TimeUnit timeUnit) {
        this.redissonClient = redissonClient;
        this.tryLockWaitTime = tryLockWaitTime;
        this.timeUnit = timeUnit;
    }

    @Override
    public void lock(String key) {
        RLock lock = redissonClient.getLock(key);
        if (!lock.isLocked()) {
            try {
                if (lock.tryLock(tryLockWaitTime, releaseLockTime, timeUnit)) {
                    lock.lock();
                }
            } catch (InterruptedException e) {
                log.error("获取分布式锁发生异常,key:" + key + "，原因:" + e.getMessage());
            }
        }
    }

    @Override
    public void lock(String key, long tryLockWaitTime, long releaseLockTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        if (!lock.isLocked()) {
            try {
                if (lock.tryLock(tryLockWaitTime, releaseLockTime, timeUnit)) {
                    lock.lock();
                }
            } catch (InterruptedException e) {
                log.error("获取分布式锁发生异常,key:" + key + "，原因:" + e.getMessage());
            }
        }
    }

    @Override
    public void unLock(String key) {
        RLock lock = redissonClient.getLock(key);
        if (lock.isLocked()) {
            lock.unlock();
        }
    }

}
