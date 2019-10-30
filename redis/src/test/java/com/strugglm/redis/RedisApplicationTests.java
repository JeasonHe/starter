package com.strugglm.redis;

import com.strugglm.redis.utils.RedisUtil;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void contextLoads() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String a = "string";
        List<String> list = Lists.newArrayList();
        list.add("a");
        list.add("b");

        valueOperations.set("a", a);
    }

}
