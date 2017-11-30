package com.anujaneja.mt.countDownLatch_6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Work started "+ Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Work finised "+ Thread.currentThread().getName());
        latch.countDown();
    }
}
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);

        for(int i=0;i<3;i++ ) {
            executorService.submit(new Processor(latch));
        }

        executorService.shutdown();

        System.out.println("All task submitted.");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All task finished.");
    }
}
