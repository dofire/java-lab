package org.dofire;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        Benchmarks.measureTime(() -> {

            // Custom virtual thread executor that runs tasks on a single platform thread
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                for (int i = 0; i < 10; i++) {
                    int taskId = i;
                    executor.submit(() -> {
                        System.out.println("Running task " + taskId + " on thread: " + Thread.currentThread());
                        simulateJpaCall(taskId);
                    });
                }
            }

        });
    }

    static void simulateJpaCall(int id) {
        try {
            // Simulate blocking DB call
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1001));
            System.out.println("Finished simulated JPA call for ID: " + id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}