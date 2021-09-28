package nsu.fit.oop.heapsortTests;//import org.junit.jupiter.api.Test;

import nsu.fit.oop.heapsort.Heapsort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {

    /* * * * * * *
     SORTING TESTS
     * * * * * * */

    @ParameterizedTest
    @MethodSource ("arrayGenerator")
    public void testSortingGenerated(int[] input){
        //Elements in int (not Integer) due to generator giving wrong output on Integer[]

        Integer[] initial = Arrays.stream( input ).boxed().toArray( Integer[]::new );
        Arrays.sort(input);
        Heapsort.heapsort(initial);
        assertEquals(Arrays.toString(input), Arrays.toString(initial));
    }

    @ParameterizedTest
    @MethodSource ("generateSimilarElementsArray")
    public void testSortingManyOfSame(int[] input){
        //Elements in int (not Integer) due to generator giving wrong output on Integer[]

        Integer[] initial = Arrays.stream( input ).boxed().toArray( Integer[]::new );
        Arrays.sort(input);
        Heapsort.heapsort(initial);
        assertEquals(Arrays.toString(input), Arrays.toString(initial));
    }

    @Test
    public void testSortingEmpty(){
        Integer[] a = new Integer[0];
        Integer[] b = new Integer[0];
        Heapsort.heapsort(b);
        assertEquals(Arrays.toString(a), Arrays.toString(b));
    }

    @ParameterizedTest
    @NullSource
    public void testSortingNull(Integer[] a){
        IllegalArgumentException e = assertThrows (IllegalArgumentException.class, () -> Heapsort.heapsort(a));
        assertEquals(e.getMessage(), "null array");
    }

    private static ArrayList<int[]> generateSimilarElementsArray(){
        ArrayList<int[]> res = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i<10; i++){
            int size = 100000;
            int[] test = new int[size];
            int value = r.nextInt();
            for (int j = 0; j<size; j++){
                test[j] = value;
            }
            res.add(test);
        }

        return res;
    }

    private static ArrayList<int[]> arrayGenerator() {
            ArrayList<int[]> testList = new ArrayList<>(25);
            Random r = new Random();
            for (int i=0; i<25; i++){
                int size = r.nextInt(100000);
                int[] test = new int[size];
                for (int j = 0; j<size; j++){
                   test[j] = r.nextInt()*(int)Math.pow(-1, j%2);
                }
                testList.add(test);
            }
            return testList;
    }

    /* * * * * *
     PARSER TESTS
     * * * * * */
    @ParameterizedTest
    @MethodSource ("stringsGeneratorNormal")
    public void testParserGenerated(String input){
        Integer[] a;
        a = Heapsort.parseToArray(input);
        input = input.replace("{", "[");
        input = input.replace("}", "]");
        input = input.replaceAll(",", ", ");
        assertEquals(Arrays.toString(a), input);
    }

    @ParameterizedTest
    @ValueSource (strings = {"1,2,3,4,5", "{1,2,3,4,5", "1,2,3,4,5}", "[1,2,3,4,5]", "{1,2,3,4,5},6", "1, 2, sus, pi, cious"})
    public void testParserUnbraced(String string){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->Heapsort.parseToArray(string));
        assertEquals(e.getMessage(), "Bad input - no curly braces on ends");
    }

    @ParameterizedTest
    @ValueSource (strings = {"{1,a,2,4,5}", "{1a1,2,3,4,5}", "{1, 2, 3, 4, 5}", "{1,2,3,4,5,}", "{,1}", "{{1,2,3}}"})
    public void testParserArguments(String string){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->Heapsort.parseToArray(string));
        assertEquals(e.getMessage(), "Bad input - wrong delimiter or value");
    }

    @ParameterizedTest
    @NullSource
    public void testParserNull(String string){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->Heapsort.parseToArray(string));
        assertEquals(e.getMessage(), "Bad input - null/empty string");
    }

    @Test
    public void testParserEmpty(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, ()->Heapsort.parseToArray(""));
        assertEquals(e.getMessage(), "Bad input - null/empty string");
    }

    static ArrayList<String> stringsGeneratorNormal(){
        ArrayList<String> res = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i<25; i++){
            StringBuilder test = new StringBuilder("{");
            int size = r.nextInt(100000);
            for (int j = 0; j<size; j++) {
                test.append(r.nextInt());
                if (j!= size-1) test.append(",");
            }
            test.append("}");
            res.add(test.toString());
        }

        return res;
    }
}
