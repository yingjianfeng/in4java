package com.in4java;

import cn.hutool.core.lang.hash.Hash;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;


public class Test {

    public static void main(String[] args) {

        Collections.synchronizedCollection(new ArrayList<>());
    }

}


