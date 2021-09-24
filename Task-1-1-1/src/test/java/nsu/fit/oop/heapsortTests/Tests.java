package nsu.fit.oop.heapsortTests;//import org.junit.jupiter.api.Test;

import nsu.fit.oop.heapsort.Heapsort;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @ParameterizedTest
    @ValueSource (strings = {"{1,2,3,4,5}","{1,4,5,2,3}",
            "{1,5,2,4,3}","{1,3,2,5,4}","{5,4,2,3,1}","{5,2,3,4,1}","{5,4,3,2,1}"})
    public void worksFine(String string){
        Integer[] arr = {1,2,3,4,5};
        assertEquals(Arrays.toString(Heapsort.heapsort(string)), Arrays.toString(arr));
    }

    @ParameterizedTest
    @ValueSource (strings = {"1,2,3,4,5", "{1,2,3,4,5", "1,2,3,4,5}", "[1,2,3,4,5]"})
    public void noCurlies(String string){
        assertThrows(IllegalArgumentException.class,() -> Heapsort.heapsort(string));
    }

    @ParameterizedTest
    @ValueSource (strings = {"{1.2.3}", "{1, 2, 3}", "{a,b,c}"})
    public void badInput(String string){
        assertThrows(IllegalArgumentException.class, () -> Heapsort.heapsort(string));
    }
}
