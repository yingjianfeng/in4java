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

    public boolean divideArray(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            map.merge(num,1,Integer::sum);
        }

        Collection<Integer> values = map.values();
      return   new HashSet<>(map.values()).size()==1;
    }
}


