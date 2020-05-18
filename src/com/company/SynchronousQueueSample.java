package com.company;

import java.util.concurrent.SynchronousQueue;


/**
 * The SynchronousQueue is a queue that can only contain a single element internally.
 * A thread inseting an element into the queue is blocked until another thread takes
 * that element from the queue. Likewise, if a thread tries to take an element and
 * no element is currently present, that thread is blocked until a thread insert
 * an element into the queue.
 * <p>
 * Calling this class a queue is a bit of an overstatement. It's more of a rendesvouz point.
 */
public class SynchronousQueueSample implements Sample {
    @Override
    public void runSample() {
        SynchronousQueue queue = new SynchronousQueue();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

    }

    class Producer implements Runnable {
        SynchronousQueue queue;

        Producer(SynchronousQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                this.queue.put("Object");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        SynchronousQueue queue;

        Consumer(SynchronousQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

