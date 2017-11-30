package com.anujaneja.mt.multiLocksSyncBlock_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkerMultiLock {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock1) {
            list1.add(new Random().nextInt(100));
        }

    }

    private void stageTwo() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock2) {
            list2.add(new Random().nextInt(100));
        }
    }

    private void doWork() {
        stageOne();
        stageTwo();
    }

    public void process() {

        Long startTime = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++) {
                    doWork();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++) {
                    doWork();
                }

            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Time Taken to execute: " +(System.currentTimeMillis()-startTime));
        System.out.println("List1: "+list1.size()+ " List2: "+list2.size());
    }

}
