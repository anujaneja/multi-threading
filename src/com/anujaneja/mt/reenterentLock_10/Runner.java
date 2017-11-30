package com.anujaneja.mt.reenterentLock_10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int counter = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public void firstThread() throws InterruptedException {
        lock.lock();
        cond.await();

        try{
            System.out.println("First thread is increment the value");
            increment();
            printCounter();
            cond.signal();
        }finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        try{
            System.out.println("Press enter to continue.");
            new Scanner(System.in).nextLine();
            increment();
            printCounter();
            cond.signal();
        }finally {
            lock.unlock();
        }
    }

    public void increment() {
        counter++;
    }

    public void printCounter() {
        System.out.println("Counter value is: "+counter);
    }

}
