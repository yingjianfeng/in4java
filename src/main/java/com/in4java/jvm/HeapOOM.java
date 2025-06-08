package com.in4java.jvm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    //创建一个内部类用于创建对象使用
    static class OOMObject {

    }
    public static void main(String[] args) throws Exception{
        Process process = Runtime.getRuntime().exec("jcmd " + java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0] + " VM.flags");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        System.out.println("JVM 参数：");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        List<OOMObject> list = new ArrayList<OOMObject>();
        //无限创建对象，在堆中
        while (true) {
            list.add(new OOMObject());
        }
    }
}
