package com.in4java.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * @author: yingjf
 * @date: 2023/4/23 11:02
 * @description:
 */
@Slf4j
public class In4javaTest {

    @Data
    class A {
        Integer b;
    }

    @Test
    void contextLoads() {
        Object object = null;
        if (object instanceof JSONObject) {

        }
        String x = "xx";
        char i = 'i';
        char g = 'g';
        System.out.println(i);
        System.out.println(g);
        System.out.println(i > g);
    }

    public static void main(String[] args) {

        System.out.println(isValid("()"));
    }

    public static boolean isValid(String s) {
        Map<String, String> map = new HashMap<>();
        map.put(")", "(");
        map.put("}", "{");
        map.put("]", "[");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String temp = String.valueOf(s.charAt(i));
            if (!map.containsKey(temp)) {
                stack.push(temp);
            }
            if (stack.empty()) {
                return false;
            }
            if (Objects.equals(stack.peek(), map.get(temp))) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }

}
