package com.in4java.redis;

import cn.hutool.core.lang.UUID;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yingjf
 * @date: 2024/8/14 10:28
 * @description:
 */
public class RedisPubSubExample {
    public static void main(String[] args) throws Exception{
        Jedis jedis = new Jedis("192.168.10.5", 6379);
        jedis.auth("mUWPMPpWyv8I069o");
        // 发布消息
        String channel = "testChannel";

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for(int i=0;i<100;i++){
            executorService.submit(()->{
                String message = UUID.fastUUID().toString(true);
                System.out.println("send   "+message);
                jedis.publish(channel, message);
            });
        }
        Thread.sleep(10000);
    }
}
