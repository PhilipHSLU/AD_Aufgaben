package ch.hslu.w1_rekursion;

public class Fibonacci {

    public long fiboRec1(int n) {
        // Rekursionsbasis (Abbruchbedingung)
        if (n <= 1) {
            return n;
        }
        // Rekursionsvorschrift
        return fiboRec1(n - 1) + fiboRec1(n - 2);
    }

// MEMOIZE
    private long[] memo;

    public long fiboRec2(int n) {
        memo = new long[n + 1];
        return memoize(n);
    }

    private long memoize(int n) {
        if (n <= 1) return n;

        // Wenn Wert schon berechnet wurde, direkt zurückgeben
        // new Long[] Array = 0 beim Erstellen
        if (memo[n] != 0) return memo[n];

        // Ansonsten berechnen und speichern
        memo[n] = memoize(n - 1) + memoize(n - 2);
        return memo[n];
    }

// ITER
    public long fiboIter(int n) {
        if (n <= 1) return n;

        long prev = 0;
        long current = 1;

        for (int i = 2; i <= n; i++) {
            long next = prev + current;
            prev = current;
            current = next;
        }
        return current;
    }
}
