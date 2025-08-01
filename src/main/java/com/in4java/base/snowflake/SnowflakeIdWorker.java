package com.in4java.base.snowflake;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * Snowflake 算法工具类，用于生成分布式系统中的唯一 ID
 */
public class SnowflakeIdWorker {
    // 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
    private final static long START = 1288834974657L;
    // 机器标识位数
    private final static long WORKER_ID_BITS = 5L;
    // 数据中心标识位数
    private final static long DATACENTER_ID_BITS = 5L;
    // 机器ID最大值
    private final static long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    // 数据中心ID最大值
    private final static long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);
    // 毫秒内自增位
    private final static long SEQUENCE_BITS = 12L;
    // 机器ID偏左移12位
    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;
    // 数据中心ID左移17位
    private final static long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    // 时间毫秒左移22位
    private final static long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
    // 毫秒内自增位掩码
    private final static long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    // 上次生产id时间戳
    private static long lastTimestamp = -1L;
    // 0，并发控制
    private long sequence = 0L;
    // 机器ID
    private final long workerId;
    // 数据标识id部分
    private final long datacenterId;

    private final static SnowflakeIdWorker ID_WORKER = new SnowflakeIdWorker();

    private SnowflakeIdWorker() {
        this.datacenterId = getDatacenterId();
        this.workerId = getMaxWorkerId(datacenterId);
    }

    public static SnowflakeIdWorker getInstance() {
        return ID_WORKER;
    }

    /**
     * 获取下一个ID
     *
     * @return 返回的long基本类型的雪花id
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        return ((timestamp - START) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取 maxWorkerId
     */
    protected static long getMaxWorkerId(long datacenterId) {
        StringBuilder mid = new StringBuilder();
        mid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            mid.append(name.split("@")[0]);
        }
        return (mid.toString().hashCode() & 0xffff) % (MAX_WORKER_ID + 1);
    }

    /**
     * 数据标识id部分
     */
    protected static long getDatacenterId() {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (MAX_DATACENTER_ID + 1);
            }
        } catch (Exception e) {
            System.out.println("getDatacenterId: " + e.getMessage());
        }
        return id;
    }
}