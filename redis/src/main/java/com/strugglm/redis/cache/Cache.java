package com.strugglm.redis.cache;

import com.strugglm.redis.config.RedisStarter;
import lombok.extern.log4j.Log4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

/**
 * @description: 缓存
 * @author: hejiale
 * @create: 2019/12/06 11:56
 */
@Log4j
public class Cache implements BaseCache {

    private RedissonClient redissonClient;

    public Cache(RedisStarter redisStarter) {
        redissonClient = redisStarter.getRedisson();
    }

    public void setString(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    public String getString(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.isExists() ? bucket.get() : null;
    }


}
