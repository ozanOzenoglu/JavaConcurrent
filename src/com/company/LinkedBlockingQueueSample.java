package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingQueueSample implements Sample {

    @Override
    public void runSample() {
        BlockingQueue<String> unbounded = new LinkedBlockingDeque<>();
        BlockingQueue<String> bounded = new LinkedBlockingDeque<>(100);
        try {
            bounded.put("Value");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
