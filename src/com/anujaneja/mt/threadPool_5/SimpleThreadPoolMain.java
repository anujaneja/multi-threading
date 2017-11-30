package com.anujaneja.mt.threadPool_5;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class Process implements Runnable {
    int id;

    public Process(int id) {
        this.id = id;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Process: "+id);

        for(int i=0;i<1E4;i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()+" Process finished "+id);
    }
}

public class SimpleThreadPoolMain {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0;i<2;i++) {
            executorService.submit(new Process(i));
        }

        executorService.shutdown();

        System.out.println("All tasks submitted.");

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished Execution.");


    }
}
