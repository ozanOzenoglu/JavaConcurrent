package com.company;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockSample implements Sample {
    private static ReentrantLock lock = new ReentrantLock();
    @Override
    public void runSample() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(2);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    LockSample.criticMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5 , TimeUnit.SECONDS);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LockSample.criticMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread 1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LockSample.criticMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"Thread 2").start();
    }

    private static void criticMethod() throws InterruptedException {
        lock.tryLock(1, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName() +  ": I'm in critical section");
        lock.unlock();
    }
}
