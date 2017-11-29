package com.anujaneja.mt.synchronization1;

import java.util.Scanner;

class Runner extends Thread {

    private volatile boolean isRunning = true;

    public void run() {


        while(isRunning) {

            try {
                System.out.println("Thread is running....");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Thread is finished....");


    }

    public void shutdown() {
        this.isRunning = false;
    }
}

public class Main {

    public static void main(String[] args) {
        Runner t1 = new Runner();
        Runner t2 = new Runner();

        t1.start();
        t2.start();

        System.out.println("Press enter/return key to stop the processing...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        t1.shutdown();
        t2.shutdown();

    }
}
