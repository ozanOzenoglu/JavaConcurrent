package com.company;

import java.util.concurrent.*;
import java.util.concurrent.Callable;
public class ScheduledExecutorServiceSample implements Sample {
    @Override
    public void runSample() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
        ScheduledFuture future =  scheduledExecutorService.schedule(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception {
                System.out.println("Scheduled callable is Executed!");
                return "Called";
            }
        }, 5, TimeUnit.SECONDS);
        System.out.println("after scheduled part future is waiting for");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            int counter =0 ;
            @Override
            public void run() {

                System.out.println("Scheduled for " + (counter++));
            }
        },3, 5, TimeUnit.SECONDS); // after 3 seconds start run it for every 5 secs.

        Thread.sleep(20000);
        scheduledExecutorService.shutdown();
    }
}
