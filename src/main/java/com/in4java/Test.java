package com.in4java;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;



public class Test {

    public static void main(String[] args) {
        for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.println(b.getName());
        }

    }

    public int minimumBoxes(int[] apple, int[] capacity) {
        int appleSize = 0;
        for(int i:apple){
            appleSize+=i;
        }
        Arrays.sort(capacity);
        int ans = 0;
        for (int i = capacity.length-1; i >=0; i--) {
            appleSize-=i;
            ans++;
            if(appleSize<=0) return ans;
        }
        return ans;
    }
}


