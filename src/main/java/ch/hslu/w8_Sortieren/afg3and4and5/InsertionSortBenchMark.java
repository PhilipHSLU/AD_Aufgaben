package ch.hslu.w8_Sortieren.afg3and4and5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertionSortBenchMark {
    private static final Logger LOG = LoggerFactory.getLogger(InsertionSortBenchMark.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] a100k = CreateArrays.getShuffledNumbers(100_000);
        SortierAlgorythmen.insertionSort(a100k);

        long end = System.currentTimeMillis();
        long total = end - start;
        LOG.info("shuffeled time: {}", total);

    }

}
