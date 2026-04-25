package ch.hslu.w8_Sortieren.afg3and4and5;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortierAlgorythmen {

    public static void main(String[] args) {
        int[] randomArray = CreateArrays.getShuffledNumbers(20);
        insertionSortAnimation(randomArray,100);
    }



    /**
     * Sortiert das int-Array aufsteigend.
     * @param a Zu sortierendes Array.
     */
    public static long insertionSortAnimation(int[] a, int delay) {
        long comparisons = 0;
        SortingAnimation.instance().showArray(a, delay);


        final int n = a.length; // Anzahl Zahlen
        for (int i = 1; i < n; i++) {
            int element = a[i]; // Speichere das einzufügende Element

            int j = i; // Bereich 0...i-1 ist bereits sortiert
            while (j > 0 && a[j - 1] > element) {
                comparisons++;
                a[j] = a[j - 1]; // Element nach rechts verschieben
                j--; // weiter nach links laufen
                SortingAnimation.instance().showArray(a, delay, j, i);

            }
            a[j] = element; // Element an gefundener Position einfügen
            SortingAnimation.instance().showArray(a, delay, j);
        }
        return comparisons;
    }

     public static long insertionSort(int[] a) {
        long comparisons = 0;
        SortingAnimation.instance().showArray(a);

        final int n = a.length; // Anzahl Zahlen
        for (int i = 1; i < n; i++) {
            int element = a[i]; // Speichere das einzufügende Element

            int j = i; // Bereich 0...i-1 ist bereits sortiert
            while (j > 0 && a[j - 1] > element) {
                comparisons++;
                a[j] = a[j - 1]; // Element nach rechts verschieben
                j--; // weiter nach links laufen
            }
            a[j] = element; // Element an gefundener Position einfügen

        }
        return comparisons;
    }

}
