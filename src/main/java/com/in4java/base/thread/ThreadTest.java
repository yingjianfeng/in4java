package com.in4java.base.thread;

public class ThreadTest extends Thread{
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("xxxx");
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
    }
}
