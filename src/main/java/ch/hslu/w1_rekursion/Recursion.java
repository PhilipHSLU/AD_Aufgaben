package ch.hslu.w1_rekursion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Recursion {

    private static final Logger LOG = LoggerFactory.getLogger(Recursion.class);

    public static int counterTask1 = 0;
    public static int counterTask2 = 0;
    public static int counterTask3 = 0;

    public static void task(final int n) {
        task1(); task1(); task1(); task1();
        for (int i = 0; i < n; i++) {
            task2(); task2(); task2();
            for (int j = 0; j < n; j++) {
                task3(); task3();
            }
        }
        LOG.info("Task1: {} | Task2: {} | Task3: {} ",
                counterTask1, counterTask2, counterTask3);
    }

    public static void task1(){
        counterTask1 ++;
    }
    public static void task2(){
        counterTask2 ++;

    }
    public static void task3(){
        counterTask3 ++;

    }

    public static void main(String[] args) {
        task(1);
        task(2);
        task(3);
        task(5);
        task(10);
    }
}
