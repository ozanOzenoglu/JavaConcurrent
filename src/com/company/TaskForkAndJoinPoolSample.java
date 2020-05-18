package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TaskForkAndJoinPoolSample implements Sample {
    @Override
    public void runSample() throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool(4);
        long result = pool.invoke(new MyRecursiveTask(24));
        System.out.println("Total result is " + result);
    }

    private class MyRecursiveTask extends RecursiveTask<Long> {
        private long workLoad = 0;

        public MyRecursiveTask(long workLoad) {
            this.workLoad = workLoad;
        }

        @Override
        protected Long compute() {
            //if work is above threshold, break tasks up into smaller tasks
            if(this.workLoad > 16) {
                System.out.println("Splitting workLoad: " + this.workLoad);
                List<MyRecursiveTask> subTasks = new ArrayList<>();
                subTasks.addAll(createSubtasks());
                for (MyRecursiveTask subtask : subTasks) {
                    subtask.fork();
                }
                long result = 0;
                for(MyRecursiveTask tasks : subTasks) {
                    result += tasks.join();
                }
                return result;
            } else {
                System.out.println("Doing worklad myself " + this.workLoad);
                return  workLoad * 3 ;
            }

        }

        private Collection<? extends MyRecursiveTask> createSubtasks() {
            ArrayList<MyRecursiveTask> subTasks = new ArrayList<>();
            MyRecursiveTask task1 = new MyRecursiveTask(this.workLoad / 2);
            MyRecursiveTask task2 = new MyRecursiveTask(this.workLoad / 2);
            subTasks.add(task1);
            subTasks.add(task2);
            return subTasks;
        }
    }
}
