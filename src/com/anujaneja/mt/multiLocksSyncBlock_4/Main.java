package com.anujaneja.mt.multiLocksSyncBlock_4;

public class Main {
    public static void main(String[] args) {
        WorkerSynchronized workerSynchronized=new WorkerSynchronized();

        workerSynchronized.process();

        System.out.println("************Worker Multi Lock***********");

        WorkerMultiLock workerMultiLock=new WorkerMultiLock();

        workerMultiLock.process();

    }
}
