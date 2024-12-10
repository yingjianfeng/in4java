package com.in4java.base;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: yingjf
 * @date: 2024/10/23 10:24
 * @description:
 */
public class MyTest {

    public static void main(String[] args) {
        // 假设queries是一个基本数据类型的数组，例如int[]
        int[] queries = {1, 2, 3, 4, 5};

        List<String> list = IntStream.rangeClosed(1, 3)
                .boxed() // 将IntStream转换为Stream<Integer>
                .map(item -> String.valueOf(item))
                .collect(Collectors.toList());
        // 打印转换后的List
        System.out.println(list);
        // m 2 4
        // h 1 3
    }
}
