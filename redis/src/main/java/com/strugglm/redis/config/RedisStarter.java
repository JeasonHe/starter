package com.strugglm.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @description: 配置
 * @author: hejiale
 * @create: 2019/12/06 12:01
 */
public class RedisStarter {

    private RedissonClient redisson;

    private RedisStarter() {
    }

    public RedisStarter(String host, Integer port, String password, Integer database) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setPassword(password);

        this.redisson = Redisson.create(config);
    }

    public RedissonClient getRedisson() {
        return this.redisson;
    }
}
