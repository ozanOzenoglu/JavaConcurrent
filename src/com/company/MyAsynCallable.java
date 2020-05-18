package com.company;

import java.util.concurrent.*;

abstract class MyCallback implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("Callable object call method");
        return null;
    }
}

class MyWorkerClass implements Runnable {
    private Callable callBack;
    public MyWorkerClass(Callable callBack) {
        this.callBack = callBack;
    }

    @Override
    public void run() {
        System.out.println("MyWorker class is running");
        try {
            Thread.sleep(6000);
            this.callBack.call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class MyAsynCallable implements Sample {

    @Override
    public void runSample() {
        ExecutorService myExecuter = Executors.newFixedThreadPool(1);
        MyWorkerClass myWorker = new MyWorkerClass(new MyCallback() {
            @Override
            public Object call() throws Exception {
                System.out.println("My CallBack function is called");
                return null;
            }
        });
        myExecuter.execute(myWorker);
        System.out.println("myExecuter execute method is called");
        myExecuter.shutdown();
        System.out.println("My Executor is shutdowned");
    }
}
