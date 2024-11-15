package com.in4java.flink;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.StringDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author: yingjf
 * @date: 2024/7/12 16:21
 * @description:
 */
public class Test {


    public static void main(String[] args) throws Exception {
// 1. 获取执行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1); // 设置并行度

        // 2. 创建MySQL CDC SourceFunction
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("192.168.10.14") // 设置MySQL主机名
                .port(3306) // 设置端口号
                .databaseList("testsync") // 设置数据库名，可以使用正则表达式
                .tableList("table_name") // 设置表名，可以使用正则表达式
                .username("ms2") // 设置用户名
                .password("Ms2.com.cn") // 设置密码
                .deserializer(new StringDebeziumDeserializationSchema()) // 设置反序列化方式
                .startupOptions(StartupOptions.latest()) // 设置启动选项，这里是全量+增量
                .build();

        env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "sourceName").print();
        //TODO 4 执行
//        env.execute().print();
    }
}
