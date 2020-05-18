package com.company;

import java.util.concurrent.Semaphore;

public class SemaphoreSample implements Sample {
    @Override
    public void runSample()  {
        Semaphore semaphore = new Semaphore(1, true);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int acquiredCount = 0;
                    while(true) {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() +  "semaphore is acquired for " + acquiredCount++ + "\n");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int releasedCount = 0;
                while (true) {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "semaphore released " + releasedCount++ + "\n");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
