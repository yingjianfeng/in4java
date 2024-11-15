package com.in4java.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: yingjf
 * @date: 2024/10/31 16:04
 * @description:
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 默认为最小堆
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(10);
        pq.add(2);

        // 遍历并移除元素
        while (!pq.isEmpty()) {
            System.out.println(pq.poll()); // 输出：1, 2, 3, 5, 10
        }
    }

}
