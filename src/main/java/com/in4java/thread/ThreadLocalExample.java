package com.in4java.thread;

public class ThreadLocalExample {

    //创建一个ThreadLocal变量
    public static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        //设置ThreadLocal变量的值
        localVariable.set("test");

        System.out.println("<UNK>" + localVariable.get());
    }
}
