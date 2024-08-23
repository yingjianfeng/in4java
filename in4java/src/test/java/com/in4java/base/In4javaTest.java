package com.in4java.base;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: yingjf
 * @date: 2023/4/23 11:02
 * @description:
 */
@Slf4j
public class In4javaTest {

    public static void main(String[] args) {
        int[] array = new int[]{0,-1};
        int i = longestConsecutive(array);
        System.out.println(i);

    }

    public static int longestConsecutive(int[] nums) {
        // 将数组转换为 Set，去除重复元素
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            for(int k=i+1;k<Integer.MAX_VALUE;k++){
                if(set.contains(nums[i]+k-i)){
                    max = Math.max(max,k-i);
                    continue;
                }else{
                    max = Math.max(max,k-i);
                    break;
                }
            }
        }

        return max;
    }



}



