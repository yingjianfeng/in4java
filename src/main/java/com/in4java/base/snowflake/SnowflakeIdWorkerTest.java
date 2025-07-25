package com.in4java.base.snowflake;

public class SnowflakeIdWorkerTest {
    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = SnowflakeIdWorker.getInstance();
        for (int i = 0; i < 10; i++) {
            System.out.println(idWorker.nextId());
        }
    }
}
