package nsu.fit.oop.heapsortTests;//import org.junit.jupiter.api.Test;

import nsu.fit.oop.heapsort.Heapsort;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @ParameterizedTest
    @MethodSource ("arrayGenerator")
    public void testSortingGenerated(int[] input){
        Integer[] initial = Arrays.stream( input ).boxed().toArray( Integer[]::new );
        Arrays.sort(input);
        Heapsort.heapsort(initial);
        assertEquals(Arrays.toString(input), Arrays.toString(initial));
    }

    private static ArrayList<int[]> arrayGenerator() {
            ArrayList<int[]> testList = new ArrayList<>(25);
            Random r = new Random();
            for (int i=0; i<25; i++){
                int size = r.nextInt(100000);
                int[] test = new int[size];
                for (int j = 0; j<size; j++){
                   test[j] = r.nextInt();
                }
                testList.add(test);
            }
            return testList;
    }


}
