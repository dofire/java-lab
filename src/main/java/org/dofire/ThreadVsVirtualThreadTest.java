package org.dofire;

import java.util.List;
import java.util.concurrent.*;

public class ThreadVsVirtualThreadTest {

    private static final int TASK_COUNT = 100_000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Starting test with traditional threads...");
        long startTime = System.nanoTime();
        testWithThreads();
        long endTime = System.nanoTime();
        System.out.println("Traditional threads execution time: " + (endTime - startTime) / 1_000_000 + " ms");

        System.out.println("Starting test with virtual threads...");
        startTime = System.nanoTime();
        testWithVirtualThreads();
        endTime = System.nanoTime();
        System.out.println("Virtual threads execution time: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private static void testWithThreads() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(100); // Fixed thread pool
        execute(executorService);
    }

    private static void testWithVirtualThreads() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor(); // Virtual threads
        execute(executorService);
    }

    private static void execute(ExecutorService executorService) throws InterruptedException, ExecutionException {
        List<Callable<Void>> tasks = List.of(() -> {
            calculatePrimeNumbers(); // Simulate CPU-bound task
            return null;
        });

        List<Future<Void>> futures = executorService.invokeAll(tasks);
        for (Future<Void> future : futures) {
            future.get(); // Wait for completion
        }
        executorService.shutdown();
    }

    private static void calculatePrimeNumbers() {
        // Example of a CPU-bound task: calculate prime numbers
        int limit = 10_000;
        for (int i = 2; i < limit; i++) {
            boolean prime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                // Prime number found (just printing for simplicity)
                // System.out.println(i);
            }
        }
    }
}