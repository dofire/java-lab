package org.dofire;

public class Benchmarks {

    public static void measureTime(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        long durationMs = (end - start) / 1_000_000;
        System.out.println("Execution time: " + durationMs + " ms");
    }

}
