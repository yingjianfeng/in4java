package com.in4java.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yingjf
 * @date: 2024/11/21 10:53
 * @description:
 */
public class DFS {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        dfs(nums,0,new ArrayList<>());
        return result;
    }

    public static void dfs(int[] nums,int start,List<Integer> arr) {
        result.add(new ArrayList<>(arr));
        System.out.println(start+" "+arr);
        for (int i=start;i<nums.length;++i) {
            arr.add(nums[i]);
            dfs(nums,i+1,arr);
            arr.remove(arr.size()-1);
        }
    }
}
