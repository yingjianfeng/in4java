package com.in4java;

import java.util.*;



public class Test {
    public String truncateSentence(String s, int k) {
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(arr[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
