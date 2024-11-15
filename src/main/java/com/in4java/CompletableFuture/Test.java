package com.in4java.CompletableFuture;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import com.jd.platform.async.callback.IWorker;
import com.jd.platform.async.executor.Async;
import com.jd.platform.async.wrapper.WorkerWrapper;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: yingjf
 * @date: 2024/7/24 18:54
 * @description:
 */
public class Test {

    public static void test1() {
        CompletableFuture<Void> task = CompletableFuture.runAsync(Task::a).thenRunAsync(Task::b).
                thenRunAsync(Task::c);
        task.join();
        System.out.println("执行完啦" + DateTime.now());
    }

    public static void test2() {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(Task::a),
                CompletableFuture.runAsync(Task::b),
                CompletableFuture.runAsync(Task::c)
        ).join();
        System.out.println("执行完啦");
    }

    public static void test3() {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(Task::a),
                CompletableFuture.runAsync(Task::b)
        ).thenRunAsync(Task::c).join();
        System.out.println("执行完啦");
    }

    public static void test4() {
        CompletableFuture.anyOf(
                CompletableFuture.runAsync(Task::a),
                CompletableFuture.runAsync(Task::b)
        ).thenRunAsync(Task::c).join();
        System.out.println("执行完啦");
    }

    public static void test5() {
        CompletableFuture.supplyAsync(() -> {
                    String str = RandomUtil.randomString(10);
                    System.out.println("产生随机字符串 " + str + "  " + DateTime.now());
                    return str;
                }
        ).thenApply(s -> {
                    s = s.toUpperCase();
                    System.out.println("随机字符串转大写" + s + "  " + DateTime.now());
                    return s;
                }
        ).thenAccept(s -> {
            s = s.toLowerCase();
            System.out.println("随机字符串转小写" + s + "  " + DateTime.now());
        }).join();
        System.out.println("执行完啦");
    }

    public static void test6() {

        CompletableFuture.runAsync(Task::a).thenRunAsync(
                () -> {
                    CompletableFuture bc = CompletableFuture.runAsync(Task::b).thenRunAsync(Task::c);
                    CompletableFuture de = CompletableFuture.anyOf(
                            CompletableFuture.runAsync(Task::d),
                            CompletableFuture.runAsync(Task::e)
                    );
                    CompletableFuture.allOf(bc, de).join();
                }).thenRunAsync(Task::f).join();
    }

    public static void test1_jd() throws ExecutionException, InterruptedException {
        ParWorker w1 = new ParWorker();
        ParWorker w2 = new ParWorker();
        ParWorker w3 = new ParWorker();
        WorkerWrapper<String, String> workerWrapper3 = new WorkerWrapper.Builder<String, String>()
                .worker(w3).callback(w3).param("3").build();
        WorkerWrapper<String, String> workerWrapper2 = new WorkerWrapper.Builder<String, String>()
                .worker(w2).callback(w2).param("2").build();
        WorkerWrapper<String, String> workerWrapper1 = new WorkerWrapper.Builder<String, String>()
                .worker(w1).callback(w1).param("1").next(workerWrapper2).next(workerWrapper3).build();
        Async.beginWork(1500, workerWrapper1);
        Async.shutDown();
    }

    public static void test2_jd() throws ExecutionException, InterruptedException {
        ParWorker w1 = new ParWorker();
        ParWorker w2 = new ParWorker();
        ParWorker w3 = new ParWorker();
        WorkerWrapper<String, String> workerWrapper3 = new WorkerWrapper.Builder<String, String>()
                .worker(w3).callback(w3).param("3").build();
        WorkerWrapper<String, String> workerWrapper2 = new WorkerWrapper.Builder<String, String>()
                .worker(w2).callback(w2).param("2").build();
        WorkerWrapper<String, String> workerWrapper1 = new WorkerWrapper.Builder<String, String>()
                .worker(w1).callback(w1).param("1").build();
        Async.beginWork(1500, workerWrapper1, workerWrapper2, workerWrapper3);
        Async.shutDown();
    }

    public static void test3_jd() throws ExecutionException, InterruptedException {
        ParWorker w1 = new ParWorker();
        ParWorker w2 = new ParWorker();
        ParWorker w3 = new ParWorker();
        WorkerWrapper<String, String> workerWrapper3 = new WorkerWrapper.Builder<String, String>()
                .worker(w3).callback(w3).param("3").build();
        WorkerWrapper<String, String> workerWrapper2 = new WorkerWrapper.Builder<String, String>()
                .worker(w2).callback(w2).param("2").next(workerWrapper3).build();
        WorkerWrapper<String, String> workerWrapper1 = new WorkerWrapper.Builder<String, String>()
                .worker(w1).callback(w1).param("1").next(workerWrapper3).build();
        Async.beginWork(1500, workerWrapper1, workerWrapper2);
        Async.shutDown();
    }

    public static void test4_jd() throws ExecutionException, InterruptedException {

    }

    public static void test5_jd() throws ExecutionException, InterruptedException {

        WorkerWrapper<String, String> workerWrapper3 = new WorkerWrapper.Builder<String, String>()
                .worker(new IWorker<String, String>() {
                    @Override
                    public String defaultValue() {
                        return null;
                    }

                    @Override
                    public String action(String object, Map<String, WorkerWrapper> allWrappers) {
                        String s = allWrappers.get("workerWrapper1").getWorkResult().getResult().toString().toLowerCase();
                        System.out.println("随机字符串转小写" + s + "  " + DateTime.now());
                        return s;
                    }
                }).param("3").id("workerWrapper3").build();
        WorkerWrapper<String, String> workerWrapper2 = new WorkerWrapper.Builder<String, String>()
                .worker(new IWorker<String, String>() {
                    @Override
                    public String defaultValue() {
                        return null;
                    }

                    @Override
                    public String action(String object, Map<String, WorkerWrapper> allWrappers) {
                        String s = allWrappers.get("workerWrapper1").getWorkResult().getResult().toString().toUpperCase();
                        System.out.println("随机字符串转大写" + s + "  " + DateTime.now());
                        return s;
                    }
                }).param("2").id("workerWrapper2").next(workerWrapper3).build();
        WorkerWrapper<String, String> workerWrapper1 = new WorkerWrapper.Builder<String, String>()
                .worker(new IWorker() {
                    @Override
                    public Object defaultValue() {
                        return null;
                    }

                    @Override
                    public Object action(Object object, Map allWrappers) {
                        String str = RandomUtil.randomString(10);
                        System.out.println("产生随机字符串 " + str + "  " + DateTime.now());
                        return str;
                    }
                }).param("1").id("workerWrapper1").next(workerWrapper2).build();
        Async.beginWork(15000, workerWrapper1);
        Async.shutDown();
    }

    public static void test_timeout() throws Exception {
        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("异步线程执行开始");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("异步线程执行结束");
        }).get(1l, TimeUnit.SECONDS);
        System.out.println("主线程执行");
    }

    public static void test_thread_pool() throws Exception {
        // 创建一个单线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("hello ");
        });

    }

    public static void test_blockingQueue() throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
//        queue.put("1");
//        queue.put("2");
//        queue.put("3");
//        System.out.println(queue);
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }

    public static void test_blockingQueue_demo() throws Exception {
        // 创建一个容量为10的ArrayBlockingQueue
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
//        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
//        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(10);
        // 创建生产者线程
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i <= 5; i++) {
                    // 将数据放入队列
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName() + "Produced: " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 创建消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i <= 5; i++) {
                    // 从队列中取出数据
                    int num = queue.take();
                    System.out.println(Thread.currentThread().getName() + "Consumed: " + num);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 启动生产者和消费者线程
        producerThread.start();
        consumerThread.start();
        // 等待线程执行完毕
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test_future() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {
                // 执行一些操作
                System.out.println("call 执行啦");
                return "lalala"; // 返回一个值
            }
        });
        System.out.println(future.get());
    }

    public static void test_exception() throws Exception{
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            String str = RandomUtil.randomString(10);
            System.out.println("产生随机字符串 " + str + "  " + DateTime.now());
//            int i = 1 / 0;
            return str;
        }).exceptionally(ex -> {
            System.out.println("异常了");
            return "异常了";
        });
        String res = future.get();
        System.out.println("执行完啦");
        System.out.println("res   "+res);
    }

    public static void test_threadLocal(){

        new Thread(()->{
            ThreadLocal threadLocal = new ThreadLocal();

            threadLocal.set("a");
            Object a = threadLocal.get();
            System.out.println(a);
            threadLocal.remove();
        }).start();


    }

    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test1_jd();
//        test2_jd();
//        test3_jd();
//        test5_jd();
//        test_timeout();
//        test_thread_pool();
//        test_blockingQueue();
//        test_blockingQueue_demo();
//        test_future();
//        test_exception();
        test_threadLocal();
    }

}
