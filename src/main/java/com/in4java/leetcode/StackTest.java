package com.in4java.leetcode;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: yingjf
 * @date: 2024/3/26 19:06
 * @description:
 */
public class StackTest {
    public boolean isPalindrome(String s) {
//        if(s==null||s.length() == 0){
//            return false;
//        }12313
        s = s.replaceAll(" ", "").trim().replaceAll(":", "").replaceAll
                (",", "").replaceAll("\\.", "").toLowerCase();
        String news = new StringBuilder(s).reverse().toString().toLowerCase();
        System.out.println(s+" "+news);

        return s.equals(news);
    }

    @Test
    public void test() {
        System.out.println(isPalindrome("race a car"));
    }

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        int t = 1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if(nums[j]<=0 ){
                    nums[j] = Integer.MAX_VALUE;
                }
                if(nums[j+1]<=0){
                    nums[j+1] = Integer.MAX_VALUE;
                }
                if (nums[j] < nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
            if(nums[n-1-i]==t){
                t++;
            }
            System.out.println(t+"---"+ Arrays.toString(nums));
        }
        if(nums[0]==n&&nums[n-1]==1){
            return n+1;
        }
        return t;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-2,-3,0,0,-2}));
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<nums.length-2; i++){
            if(nums[i]>0) break;
            // -3 -2 -2 0 0
            if(i>=1 && nums[i]==nums[i-1]) continue;
            int left = i+1, right = nums.length-1;
            while(left<right){
                int sum = nums[i]+nums[left]+nums[right];
                if(sum==0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    left++;
                    while (left<nums.length-1&&nums[left]==nums[left-1]) left++;
                    continue;
                }
                if(sum<0){
                    while (left<nums.length-1&&nums[left]==nums[left+1]) left++;
                    left++;
                }else{
                    while (right<nums.length-1&&nums[right]==nums[right-1]) right--;
                    right--;
                }
            }
        }
        return res;
    }

}
