package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorServiceSample implements Sample{
    @Override
    public void runSample() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newScheduledThreadPool(10);
        /*There is no way of obtaining the result of the executed Runnable, if necessary.
        You will have to use a Callable for that (explained in the following sections).*/
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("submit is running");
            }
        });
        try {
            if(future.get() == null) {
                System.out.println("submitted task is finished");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        future = executorService.submit(new Callable() {

            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable running");
                return "Callable result";
            }
        });

        try {
            System.out.println("future.get() == " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Task 1 is running");
                return "Task1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Task 2 is running");
                return "Task2";
            }
        });

        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Task 3 is running");
                Thread.sleep(3000);
                return "Task3";
            }
        });

        try {
            List<Future<String>> result = executorService.invokeAll(callables);
            for(Future<String> ret : result) {
                System.out.println("Rsult of ret is " + ret.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            executorService.invokeAny(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(2,TimeUnit.SECONDS);
            System.out.println("Executor Service is terminated");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
