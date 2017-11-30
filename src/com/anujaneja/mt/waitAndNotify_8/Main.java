package com.anujaneja.mt.waitAndNotify_8;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        ProducerConsumer producerConsumer = new ProducerConsumer(arrayList);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){producerConsumer.produce();}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        producerConsumer.consume();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }


}

class ProducerConsumer {

    private ArrayList<Integer> arrayList;
    private final Object lock=new Object();
    private final int MAX_SIZE= 10;

    public ProducerConsumer(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void produce() throws InterruptedException {
//        System.out.println("Producer called.");
        synchronized (lock) {
            Thread.sleep(1);
            if(arrayList.size()==MAX_SIZE) {
                lock.wait();
            }

            Random random = new Random();
            arrayList.add(random.nextInt(100));
            lock.notify();

        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (lock) {
            Thread.sleep(1);
            if(arrayList.size()==0) {
                lock.wait();
            }

            System.out.println("Queue content: "+arrayList+" arrayList size: "+arrayList.size()+" Value found is: "+arrayList.get(0));
            arrayList.remove(0);
            lock.notify();

        }
    }
}
