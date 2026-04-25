package ch.hslu.w8_Sortieren.afg3and4and5;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateArrays {

     static int[] getShuffledNumbers(int size) {
        List<Integer> numbers = IntStream.range(1, size + 1).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] getAscentingNumbers(int size){
         return IntStream.range(1, size + 1).toArray();
    }

    static int[] getDescendingNumbers(int size){
         return IntStream.range(1, size + 1).map(i -> size - 1 + 1).toArray();
    }

}
