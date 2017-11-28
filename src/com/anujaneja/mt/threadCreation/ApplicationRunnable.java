package com.anujaneja.mt.threadCreation;


class WorkerRunnable implements Runnable {

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
public class ApplicationRunnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new WorkerRunnable());
        Thread t2 = new Thread(new WorkerRunnable());

        t1.start();
        t2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
