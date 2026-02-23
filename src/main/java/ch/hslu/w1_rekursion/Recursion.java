package ch.hslu.w1_rekursion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Recursion {

    private static final Logger LOG = LoggerFactory.getLogger(Recursion.class);

    public static int counterTask1 = 0;
    public static int counterTask2 = 0;
    public static int counterTask3 = 0;

    public static void task(final int n) throws InterruptedException {
        long startzeit = System.currentTimeMillis();
        task1(); task1(); task1(); task1();
        for (int i = 0; i < n; i++) {
            task2(); task2(); task2();
            for (int j = 0; j < n; j++) {
                task3(); task3();
            }
        }
        long endZeit = System.currentTimeMillis();
        long totZeit = endZeit - startzeit;

        LOG.info(" bei n= {} --- Task1: {} | Task2: {} | Task3: {} | TOTAL: {} | Time: {}",
                n, counterTask1, counterTask2, counterTask3,
                counterTask1+counterTask2+counterTask3,
                totZeit);
    }

    public static void task1() throws InterruptedException {
        counterTask1 ++;
        Thread.sleep(5);
    }
    public static void task2() throws InterruptedException {
        counterTask2 ++;
        Thread.sleep(5);
    }
    public static void task3() throws InterruptedException {
        counterTask3 ++;
        Thread.sleep(5);

    }

    public static void main(String[] args) {
        try {
            task(1);
            task(2);
            task(3);
            task(5);
            // afg 2.3
            task(100);
            task(200);
        }catch (InterruptedException e){
        LOG.error("error", e);
        }
    }
}
