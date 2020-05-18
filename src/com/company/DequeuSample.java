package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * http://tutorials.jenkov.com/java-util-concurrent/blockingdeque.html
 */
public class DequeuSample implements Sample{
    @Override
    public void runSample()  {
        LinkedBlockingDeque queue = new LinkedBlockingDeque();


        /*we have to use opportunity take last and take first here
        * but we have don't this method blockingqueue implementations like
        * linkedlistqueue or arraylistqueue */
        try {
            queue.put("1");
            queue.put("2");
            queue.put("3");
            queue.put("4");
            queue.put("5");
            System.out.println(queue.pollFirst());
            System.out.println(queue.takeLast());
            System.out.println(queue.takeLast());
            System.out.println(queue.takeLast());
            System.out.println(queue.takeLast());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
