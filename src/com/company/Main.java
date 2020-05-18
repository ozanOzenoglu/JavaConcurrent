package com.company;
import java.util.concurrent.ExecutionException;

public class Main {

    private static Lock lock;

    public static synchronized void outter() throws InterruptedException {
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        System.out.println("outter");
        inner();
        lock.unlock();
    }

    public static synchronized void inner() throws InterruptedException {
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        System.out.println("inner");
        lock.unlock();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new PriorityBlockingQueueSample().runSample();
//        new SynchronousQueueSample().runSample();
//        new DequeuSample().runSample();
//        new ConcurrentMapSample().runSample();
//        new ConcurrentNavigableMapSample().runSample();
//        new CountDownLatchSample().runSample();
//        new CyclicBarierSample().runSample();
//        new ExhangerSample().runSample();
//        new SemaphoreSample().runSample();
//        new ExecutorServiceSample().runSample();
//        new MyCallableFunc().runSample();
//        new MyAsynCallable().runSample();
//        new ScheduledExecutorServiceSample().runSample();
//        new ActionForkAndJoinPoolSample().runSample();
//        new TaskForkAndJoinPoolSample().runSample();
//        new LockSample().runSample();
        new ReadWriteLockSample().runSample();
    }

}