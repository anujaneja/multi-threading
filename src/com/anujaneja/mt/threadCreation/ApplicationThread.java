package com.anujaneja.mt.threadCreation;


class WorkerThread extends Thread {
    public void run() {
        for(int i=0;i<10;i++) {
            System.out.println("Thread: "+Thread.currentThread().getName()+ " i="+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ApplicationThread {
    public static void main(String[] args) {
        WorkerThread t1 = new WorkerThread();
        WorkerThread t2 = new WorkerThread();

        t1.start();
        t2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
