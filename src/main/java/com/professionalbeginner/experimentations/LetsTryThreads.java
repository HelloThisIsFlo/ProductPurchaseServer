package com.professionalbeginner.experimentations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Florian on 10/06/16.
 */
public class LetsTryThreads {

    public static void main(String[] args) {
        System.out.println("Hello world");




        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");



        System.out.println("Ciao world");
    }

}
