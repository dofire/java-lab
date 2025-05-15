package org.dofire.thread;

import org.dofire.Benchmarks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPlayground {

    public static void main(String[] args) {
        System.out.println("Test with platform threads and virtual threads");

        Benchmarks.measureTime(() -> {
            try {
                platformThreads();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Benchmarks.measureTime(() -> {
            try {
                virtualThreads();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public static void platformThreads() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5); // 5 platform threads

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Platform thread - Task " + taskId + " running on: " + Thread.currentThread());
                try {
                    Thread.sleep(1000); // Simulate blocking I/O
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void virtualThreads() throws InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); // lightweight virtual threads


        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Virtual thread - Task " + taskId + " running on: " + Thread.currentThread());
                try {
                    Thread.sleep(1000); // JVM handles suspension efficiently
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

}
