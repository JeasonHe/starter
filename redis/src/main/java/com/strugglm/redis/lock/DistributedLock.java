package com.strugglm.redis.lock;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {
    /**
     * 默认锁，trylock 60s，300秒后释放锁
     * @param key 唯一key
     * @return void
     * @author hejiale
     * @date 2019/12/6 15:32
     */
    void lock(String key);

    /**
     *
     * @param key 唯一key
     * @param tryLockWaitTime 在这个时间内不断的尝试获取锁
     * @param releaseLockTime 释放锁的时间
     * @param timeUnit 时间单位
     * @return void
     * @author hejiale
     * @date 2019/12/6 15:34
     */
    void lock(String key, long tryLockWaitTime, long releaseLockTime, TimeUnit timeUnit);

    /**
     * 释放锁
     *
     * @param key
     * @return void
     * @author hejiale
     * @date 2019/12/6 15:36
     */
    void unLock(String key);

}
