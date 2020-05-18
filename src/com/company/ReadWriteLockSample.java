package com.company;


import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockSample implements Sample {
    private static String sharedResource;
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    @Override
    public void runSample() {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(new Consumer(),2,5, TimeUnit.NANOSECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Producer(),2,5, TimeUnit.NANOSECONDS);
//        scheduledExecutorService.shutdown();
    }

    private class Consumer implements Runnable{


        @Override
        public void run() {
            try {
                readWriteLock.readLock().lock();
                System.out.println(sharedResource);

            }catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }

    private class Producer implements Runnable {

        @Override
        public void run() {
            try {
                readWriteLock.writeLock().lock();
                sharedResource  = String.valueOf(Math.random());
            }catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

}
