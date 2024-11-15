package com.in4java.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author: yingjf
 * @date: 2024/8/14 11:16
 * @description:
 */
public class RedisSub {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.5", 6379);
        String channel = "testChannel";
        jedis.auth("mUWPMPpWyv8I069o");

        // 订阅频道
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("received "+ message);
            }

            // 其他事件处理方法...
        }, channel);



        // 请确保在适当的时候关闭连接
        // jedis.close();
    }
}
