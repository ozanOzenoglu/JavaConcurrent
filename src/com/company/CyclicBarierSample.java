package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarierSample implements Sample {
    @Override
    public void runSample() {
        CyclicBarrier barrier1 = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier inner action, barier reached its border");
            }
        });
//        CyclicBarrier barrier2 = new CyclicBarrier(2 );

        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1);
        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1);

        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();
    }


    class CyclicBarrierRunnable implements Runnable {
        CyclicBarrier barrier1 = null ;

        public CyclicBarrierRunnable(CyclicBarrier barrier1) {
            this.barrier1 = barrier1;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +
                        "waiting at barrier 1");
                barrier1.await();
                System.out.println(Thread.currentThread().getName() +
                        "done");
            } catch (InterruptedException e) {


            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


        }
    }
}
