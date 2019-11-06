package com.strugglm.core.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @description: okhttp的配置
 * @author: hejiale
 * @create: 2019/10/30 15:47
 */
@Configuration
public class OkHttpConfig {
    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectionPool(pool())
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
    }
}
