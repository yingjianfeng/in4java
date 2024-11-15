package com.in4java.CompletableFuture;

import com.jd.platform.async.callback.ICallback;
import com.jd.platform.async.callback.IWorker;
import com.jd.platform.async.worker.WorkResult;
import com.jd.platform.async.wrapper.WorkerWrapper;

import java.util.Map;

/**
 * @author: yingjf
 * @date: 2024/7/31 18:56
 * @description:
 */
public class ParWorker implements  IWorker<String, String>, ICallback<String, String> {

    @Override
    public void begin() {
        System.out.println("开始啦");
    }

    @Override
    public void result(boolean b, String s, WorkResult<String> workResult) {
        System.out.println("s: "+s);
    }

    @Override
    public String action(String s, Map<String, WorkerWrapper> map) {
        return "";
    }

    @Override
    public String defaultValue() {
        return "";
    }
}
