package com.in4java.base;

import lombok.Data;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yingjf
 * @date: 2023/4/23 11:02
 * @description:
 */
public class In4javaTest {

    @Data
    class A{
        Integer b;
    }

    @Test
    void contextLoads() {
        Object object = null;
        if (object instanceof JSONObject) {

        }
    }
}
