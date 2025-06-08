package com.in4java.count;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yingjf
 * @date: 2025/1/17 09:41
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        // JSON 文件路径
        String filePath = "/Users/yingjianfeng/Documents/ideacode/In4java/src/main/resources/file.txt";

        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 从文件中读取 JSON 并解析为 JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            // 获取 buckets 节点
            JsonNode bucketsNode = rootNode.path("hits")
                    .path("hits");

            // 提取 key 并存储到 List 中
            List<String> keys = new ArrayList<>();
            for (JsonNode bucketNode : bucketsNode) {
                String key = bucketNode.path("_source").path("id").asText();
                keys.add("'"+key+"'");
            }

            // 输出结果
            System.out.println("Keys: " + keys);
            System.out.println("size: " + keys.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
