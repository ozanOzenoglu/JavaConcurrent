package com.company;

import java.util.concurrent.*;

public class MyCallableFunc implements Callable,Sample {
    @Override
    public Object call() throws Exception {
        Thread.sleep(6000);
        return String.valueOf(System.currentTimeMillis());
    }

    @Override
    public void runSample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("Before callable call");
        Future<String> resultFuture = executorService.submit(new MyCallableFunc());
        System.out.println(resultFuture.get());
        System.out.println("after callable called");
    }
}
