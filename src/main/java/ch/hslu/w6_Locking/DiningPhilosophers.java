package ch.hslu.w6_Locking;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class DiningPhilosophers {

    static class Fork {
        private final int id;
        private final ReentrantLock lock;

        Fork(int id) { this(id, false); }

        Fork(int id, boolean fair) {
            this.id = id;
            this.lock = new ReentrantLock(fair);
        }

        int id() { return id; }

        void lock() { lock.lock(); }

        void unlock() { lock.unlock(); }

        boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return lock.tryLock(timeout, unit);
        }
    }

    static final int NUM = 5;
    static final int CYCLES = 10;

    // b) Naive: links dann rechts (Deadlock-anfällig)
    static void variantB() {
        System.out.println("b) Naive (links dann rechts)");
        System.out.println("ACHTUNG: Kann deadlocken! Timeout nach 10s.");

        Fork[] forks = new Fork[NUM];
        for (int i = 0; i < NUM; i++) forks[i] = new Fork(i);

        ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();
        long[] waitTimes = new long[NUM];

        for (int i = 0; i < NUM; i++) {
            final int id = i;
            final Fork left = forks[id];
            final Fork right = forks[(id + 1) % NUM];

            es.submit(() -> {
                ThreadLocalRandom rng = ThreadLocalRandom.current();
                for (int c = 0; c < CYCLES; c++) {
                    try { Thread.sleep(rng.nextInt(200, 401)); }
                    catch (InterruptedException e) { return; }

                    long ws = System.currentTimeMillis();
                    left.lock();
                    right.lock();
                    waitTimes[id] += System.currentTimeMillis() - ws;

                    try { Thread.sleep(rng.nextInt(200, 401)); }
                    catch (InterruptedException e) { return; }
                    finally { right.unlock(); left.unlock(); }
                }
            });
        }

        es.shutdown();
        try {
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("    => DEADLOCK erkannt! Programm haengt.");
                es.shutdownNow();
                return;
            }
        } catch (InterruptedException e) { return; }

        printResults(waitTimes);
    }

    // c) Globales Lock
    static void variantC() {
        System.out.println("c) Globales Lock");

        Fork[] forks = new Fork[NUM];
        for (int i = 0; i < NUM; i++) forks[i] = new Fork(i);
        final Object globalLock = new Object();

        long[] waitTimes = new long[NUM];

        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM; i++) {
                final int id = i;
                final Fork left = forks[id];
                final Fork right = forks[(id + 1) % NUM];

                es.submit(() -> {
                    ThreadLocalRandom rng = ThreadLocalRandom.current();
                    for (int c = 0; c < CYCLES; c++) {
                        try { Thread.sleep(rng.nextInt(200, 401)); }
                        catch (InterruptedException e) { return; }

                        long ws = System.currentTimeMillis();
                        synchronized (globalLock) {
                            left.lock();
                            right.lock();
                            waitTimes[id] += System.currentTimeMillis() - ws;

                            try { Thread.sleep(rng.nextInt(200, 401)); }
                            catch (InterruptedException e) { return; }
                            finally { right.unlock(); left.unlock(); }
                        }
                    }
                });
            }
        }

        printResults(waitTimes);
    }

    // d) Ordnungs-Lockstrategie
    static void variantD() {
        System.out.println("d) Ordnungs-Lockstrategie");

        Fork[] forks = new Fork[NUM];
        for (int i = 0; i < NUM; i++) forks[i] = new Fork(i);

        long[] waitTimes = new long[NUM];

        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM; i++) {
                final int id = i;
                final Fork left = forks[id];
                final Fork right = forks[(id + 1) % NUM];

                final Fork first = left.id() < right.id() ? left : right;
                final Fork second = left.id() < right.id() ? right : left;

                es.submit(() -> {
                    ThreadLocalRandom rng = ThreadLocalRandom.current();
                    for (int c = 0; c < CYCLES; c++) {
                        try { Thread.sleep(rng.nextInt(200, 401)); }
                        catch (InterruptedException e) { return; }

                        long ws = System.currentTimeMillis();
                        first.lock();
                        second.lock();
                        waitTimes[id] += System.currentTimeMillis() - ws;

                        try { Thread.sleep(rng.nextInt(200, 401)); }
                        catch (InterruptedException e) { return; }
                        finally { second.unlock(); first.unlock(); }
                    }
                });
            }
        }

        printResults(waitTimes);
    }

    // e) Timeout-Strategie
    static void variantE() {
        System.out.println("e) Timeout-Strategie (50ms)");

        Fork[] forks = new Fork[NUM];
        for (int i = 0; i < NUM; i++) forks[i] = new Fork(i);
        final long TIMEOUT_MS = 50;

        long[] waitTimes = new long[NUM];

        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM; i++) {
                final int id = i;
                final Fork left = forks[id];
                final Fork right = forks[(id + 1) % NUM];

                es.submit(() -> {
                    ThreadLocalRandom rng = ThreadLocalRandom.current();
                    for (int c = 0; c < CYCLES; c++) {
                        try { Thread.sleep(rng.nextInt(200, 401)); }
                        catch (InterruptedException e) { return; }

                        long ws = System.currentTimeMillis();
                        boolean acquired = false;
                        while (!acquired) {
                            left.lock();
                            try {
                                if (right.tryLock(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                                    acquired = true;
                                } else {
                                    left.unlock();
                                }
                            } catch (InterruptedException e) {
                                left.unlock();
                                return;
                            }
                        }
                        waitTimes[id] += System.currentTimeMillis() - ws;

                        try { Thread.sleep(rng.nextInt(200, 401)); }
                        catch (InterruptedException e) { return; }
                        finally { right.unlock(); left.unlock(); }
                    }
                });
            }
        }

        printResults(waitTimes);
    }

    // f) Ordnung mit fairem ReentrantLock
    static void variantF() {
        System.out.println("f) Ordnung + faires Locking");

        Fork[] forks = new Fork[NUM];
        for (int i = 0; i < NUM; i++) forks[i] = new Fork(i, true); // fair!

        long[] waitTimes = new long[NUM];

        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM; i++) {
                final int id = i;
                final Fork left = forks[id];
                final Fork right = forks[(id + 1) % NUM];

                final Fork first = left.id() < right.id() ? left : right;
                final Fork second = left.id() < right.id() ? right : left;

                es.submit(() -> {
                    ThreadLocalRandom rng = ThreadLocalRandom.current();
                    for (int c = 0; c < CYCLES; c++) {
                        try { Thread.sleep(rng.nextInt(200, 401)); }
                        catch (InterruptedException e) { return; }

                        long ws = System.currentTimeMillis();
                        first.lock();
                        second.lock();
                        waitTimes[id] += System.currentTimeMillis() - ws;

                        try { Thread.sleep(rng.nextInt(200, 401)); }
                        catch (InterruptedException e) { return; }
                        finally { second.unlock(); first.unlock(); }
                    }
                });
            }
        }

        printResults(waitTimes);
    }

    static void printResults(long[] waitTimes) {
        long total = 0;
        for (int i = 0; i < NUM; i++) {
            System.out.printf("    Philosopher %d: %5d ms%n", i, waitTimes[i]);
            total += waitTimes[i];
        }
        System.out.printf("    Gesamtwartezeit:  %5d ms%n", total);
        System.out.println();
    }

    public static void main(String[] args) {
        variantB();
        variantC();
        variantD();
        variantE();
        variantF();
    }
}