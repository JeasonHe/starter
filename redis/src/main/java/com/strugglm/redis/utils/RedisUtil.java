package com.strugglm.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @description: Redis工具类
 * @author: hejiale
 * @create: 2019/10/17 23:29
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations valueOperations;
    @Autowired
    private ListOperations listOperations;

    public void set(String key, Object value) {
        valueOperations.set(key, value);
    }

    public Object get(String key) {
        return valueOperations.get(key);
    }
}
