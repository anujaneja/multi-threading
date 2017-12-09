package com.anujaneja.mt.callable;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> future =  executorService.submit(new Runner());

        executorService.shutdown();
        Integer value=null;

        try {
            value = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Value is: "+value);
        System.out.println("Task finished....exiting...");

    }
}

class Runner implements Callable<Integer> {

    public Integer call(){

        System.out.println("System is starting the execution.....");

        Random random=new Random();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return random.nextInt(100);

    }
}
