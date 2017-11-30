package com.anujaneja.mt.threadPool_5;

import com.anujaneja.mt.threadCreation.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WorkerThreadPool implements Runnable {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public List<Integer> getList1() {
        return list1;
    }

    public List<Integer> getList2() {
        return list2;
    }

    public void run() {
        doWork();
    }

    private void stageOne() {

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(new Random().nextInt(100));
        }

    }

    private void stageTwo() {


        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(new Random().nextInt(100));
        }
    }

    private void doWork() {
        for(int i=0;i<1000;i++) {
            stageOne();
            stageTwo();
        }
    }

}

class WorkerMain {
    public static void main(String[] args) {

        Long startTime = System.currentTimeMillis();


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        WorkerThreadPool workerThreadPool = new WorkerThreadPool();

        for(int i=0;i<2;i++) {
            executorService.submit(workerThreadPool);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Time Taken to execute: " +(System.currentTimeMillis()-startTime));
        System.out.println("List1: "+workerThreadPool.getList1().size()+ " List2: "+workerThreadPool.getList2().size());
    }
}
