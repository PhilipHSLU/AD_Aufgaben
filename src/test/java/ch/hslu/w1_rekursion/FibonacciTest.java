package ch.hslu.w1_rekursion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    Fibonacci fib = new Fibonacci();

    @Test
    void testFibo() {
        assertEquals(0, fib.fiboRec1(0));  // Testfall 1: Basis 0
        assertEquals(1, fib.fiboRec1(1));  // Testfall 2: Basis 1
        assertEquals(55, fib.fiboRec1(10)); // Testfall 3: n=10
    }
}