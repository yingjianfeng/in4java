package com.in4java.base.thread;

public  class RunableTest implements Runnable {
    @Override
    public void run() {
        System.out.println("xxx");
    }
    public static void main(String[] args) {
        RunableTest runableTest = new RunableTest();
        Thread thread = new Thread(runableTest);
        thread.start();
    }
}
