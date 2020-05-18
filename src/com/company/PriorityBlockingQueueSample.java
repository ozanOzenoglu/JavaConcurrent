package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueSample implements Sample{
 /*
 * The PriorityBlockingQueue class implements the BlockingQueue interface. Read the BlockingQueue text for more information about the interface.

The PriorityBlockingQueue is an unbounded concurrent queue. It uses the same ordering rules as the java.util.PriorityQueue class. You cannot insert null into this queue.

All elements inserted into the PriorityBlockingQueue must implement the java.lang.Comparable interface. The elements thus order themselves according to whatever priority you decide in your Comparable implementation.

Notice that the PriorityBlockingQueue does not enforce any specific behaviour for elements that have equal priority (compare() == 0).

Also notice, that in case you obtain an Iterator from a PriorityBlockingQueue, the Iterator does not guarantee to iterate the elements in priority order.

Here is an example of how to use the PriorityBlockingQueue:
 * */
    private class Element implements Comparable {

        int priority;

        public Element(int priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(Object o) {
            if (this.priority < ((Element)o).priority) return 1;
            else if (this.priority > ((Element)o).priority) return -1;
            else return 0;
        }
    }

    public void runSample() {
        BlockingQueue queue = new PriorityBlockingQueue();
        try {
            queue.put(new Element(1));
            queue.put(new Element(12));
            queue.put(new Element(13));
            queue.put(new Element(10));
            queue.put(new Element(3));
            queue.put(new Element(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Element element =(Element) queue.take();
            System.out.println("Element priority is " + element.priority);
            element =(Element) queue.take();
            System.out.println("Element priority is " + element.priority);
            element =(Element) queue.take();
            System.out.println("Element priority is " + element.priority);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
