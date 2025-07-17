package com.in4java;

import java.util.*;



public class Test {

    public static void main(String[] args) {
        Test test = new Test();

        System.out.println(test.maximumLength(new int[]{4,51,68}));
    }

    public int maximumLength(int[] nums) {
        if(nums.length<3) return nums.length;
        // ++++
        // ----
        // -+-+
        // +-+-
        int ans = 0;
        int a = 0,b=0;
        int c=0,z = 1;
        int d=0,x = 0;
        for (int i = 0; i < nums.length; i++) {
            /*if(nums[i]%2==0)a++;
            if(nums[i]%2==1)b++;
            if(z==0){
                if(nums[i]%2==1) continue;
                else c++;
                z=1;
            }else{
                if(nums[i]%2==0) continue;
                else c++;
                z=0;
            }*/
            if(x==0){
                if(nums[i]%2==1) continue;
                else d++;
                x=1;
            }else{
                if(nums[i]%2==0) continue;
                else d++;
                x=0;
            }
        }
        System.out.println(a+" "+b+" "+c+" "+d);
        return Math.max(Math.max(a,b),Math.max(c,d));
    }
}


