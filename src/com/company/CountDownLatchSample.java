package com.company;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample implements Sample {
    @Override
    public void runSample() {
        CountDownLatch latch = new CountDownLatch(3);
        Waiter waiter = new Waiter(latch);
        Decrementer decrementer= new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(decrementer).start();
    }

    private class Waiter implements Runnable{
        CountDownLatch latch = null;
        Waiter(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            }catch (InterruptedException ex) {

            }
            System.out.println("Waiter is released");
        }
    }

    private class Decrementer implements Runnable{
        CountDownLatch latch;

        public Decrementer(CountDownLatch latch) {
            this.latch = latch;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                this.latch.countDown();
                Thread.sleep(1000);
                this.latch.countDown();
                Thread.sleep(1000);
                this.latch.countDown();
            }catch(InterruptedException ex) {

            }
        }
    }
}
