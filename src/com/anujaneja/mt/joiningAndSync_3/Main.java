package com.anujaneja.mt.joiningAndSync_3;

class Worker {

    private int counter=0;

    private synchronized void increment() {
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Thread.sleep(100);
        System.out.println("Counter value: "+counter);
    }
}

public class Main {

    public static void main(String[] args) {
        Worker worker=new Worker();

        try {
            worker.doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
